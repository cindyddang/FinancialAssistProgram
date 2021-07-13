/* January 02, 2020
 * A class which has methods calculating spending according to 
personal spending.*/
package financialassist;

import java.util.ArrayList;

public class PersonalSpending 
{
    //Method calculating the total spending amount of a person
    public int sumAmount(ArrayList<ArrayList<String>> list, String name)
    {
        int sum = 0;
        int newestData = list.size();
        
        for(int i = 0; i<newestData; i++)
        {
            ArrayList<String> tempList = list.get(i);
            String tempName = (String)tempList.get(0);
            if (tempName.equals(name))
            {
                sum = sum + Integer.parseInt((String) tempList.get(4));
            }
        }
        
        return sum;
    }
    
    //Method to sort out spending of one person only
    public ArrayList<ArrayList<String>> personSpend(ArrayList<ArrayList<String>> spendingList, String name)
    {
        ArrayList<ArrayList<String>> sortedList = new ArrayList<ArrayList<String>>();
        
        for(int i = 0; i<spendingList.size(); i++)
        {
            String tempName = spendingList.get(i).get(0);
            
            if(name.equals(tempName))
            {
                ArrayList<String> tempArray = new ArrayList();
                String dayVar = spendingList.get(i).get(1);
                String monthVar = spendingList.get(i).get(2);
                String amount = spendingList.get(i).get(4);
                String reasonVar = spendingList.get(i).get(5);
                tempArray.add(dayVar);
                tempArray.add(monthVar); 
                tempArray.add(amount);
                tempArray.add(reasonVar);
                sortedList.add(tempArray);
            }
        }
        
        return sortedList;
    }
    
    //Method to calculate the amount spending of each person
    public ArrayList<ArrayList<String>> sortName(ArrayList<ArrayList<String>> spendingList)
    {
        ArrayList<ArrayList<String>> sortedList = new ArrayList<ArrayList<String>>();
        
        for(int i = 0; i< spendingList.size(); i++)
        {
            
            int tempMonth = Integer.parseInt((String) spendingList.get(i).get(2));
            int latestMonth = Integer.parseInt((String) spendingList.get(spendingList.size()-1).get(2));
            
            //Only do the latest month
            if(tempMonth == latestMonth)
            {
                String tempName = spendingList.get(i).get(0);
                
                if(sortedList.size() == 0)
                {
                    int tempAmount = sumAmount(spendingList, tempName);
                    ArrayList<String> temp = new ArrayList();
                    temp.add(tempName);
                    temp.add(Integer.toString(tempAmount));
                    temp.add("Detail spending of this person"); //For buttons
                    sortedList.add(temp);
                }
                
                //Check if that person is already calculated or not
                for (int j = 0; j<sortedList.size(); j++)
                {
                    boolean existed = false;
                    
                    //check all the list first then see if it's existed or not.
                    for (int k = 0; k<sortedList.size(); k++)
                    {
                        String oldName = sortedList.get(k).get(0);
                        if (tempName.equals(oldName))
                        {
                            existed = true;
                        }
                    }
                    
                    if(existed == false)
                    {
                        //if not yet, do calculation and add into sorted list.
                        int tempAmount = sumAmount(spendingList, tempName);
                        ArrayList<String> temp = new ArrayList();
                        temp.add(tempName);
                        temp.add(Integer.toString(tempAmount));
                        temp.add("Detail spending of this person"); //For buttons
                        sortedList.add(temp);
                    }
                }
            }
        }
        
        return sortedList;
    }
    
    public ArrayList<String> payingSug(ArrayList<ArrayList<String>> spendingList)
    {
        ArrayList<String> concluList = new ArrayList<>();
        
        for(int i = 0; i<spendingList.size(); i++)
        {
            ArrayList<ArrayList<String>> tempList = new ArrayList<ArrayList<String>>();
            ArrayList<String> payers = new ArrayList();
            ArrayList<String> debts = new ArrayList();
            
            int debtAmount = 0;
            int pass = 0;
            
            //group orders into groups
            for (int j = 0; j<spendingList.size(); j++)
            {
                String order = spendingList.get(i).get(0);
                String nextOrder = spendingList.get(j).get(0);
                
                if(order.equals(nextOrder))
                {
                    tempList.add(spendingList.get(j));
                    pass = j;
                }
            }  
            
            //Find out who is the payer
            for (int j = 0; j<tempList.size(); j++)
            {
                int paidAmount = Integer.parseInt(tempList.get(j).get(4));
                int spendAmount = Integer.parseInt(tempList.get(j).get(5));
                
                if(paidAmount > spendAmount)
                {
                    //payer
                    payers.add(tempList.get(j).get(1));
                    debtAmount = debtAmount + (paidAmount - spendAmount);
                }
                
                if(spendAmount > paidAmount)
                {
                    //debt
                    debts.add(tempList.get(j).get(1));
                }
            }
            
            //Making conclusion
            if(debtAmount != 0)
            {
                String conclusion = "";

                for (int j = 0; j<debts.size(); j++)
                {
                    conclusion = conclusion + debts.get(j) + " ";
                }

                if(debts.size() == 1)
                {
                    conclusion = conclusion + "has to pay ";
                }
                else
                {
                    conclusion = conclusion + "have to pay ";
                }

                for (int j = 0; j<payers.size(); j++)
                {
                    conclusion = conclusion + payers.get(j) + " ";
                }

                String reason = spendingList.get(i).get(6);
                String day = spendingList.get(i).get(2);
                String month = spendingList .get(i).get(3); 
                conclusion = conclusion + "back " + debtAmount + 
                        "$ for " + reason + " on " + month + "/" + day ;

                //add conclusion into array
                concluList.add(conclusion);
            }
           
            //Go through the old parts
            i = pass;
        }
        
        return concluList;
    }
}
