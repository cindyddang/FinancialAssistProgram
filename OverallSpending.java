/* December 31, 2019
 * A class which has methods calculating spending accordings to 
monthly spending. */
package financialassist;

import java.util.ArrayList;

public class OverallSpending 
{
    //Method to calculate the sum of spending of a month
    public int sumAmount(Object[][] array, int month)
    {
        int sum = 0;
        int newestData = array.length;
        
        for(int i = 0; i<newestData; i++)
        {
            int tempMonth = Integer.parseInt((String) array[i][2]);
            if (tempMonth == month)
            {
                sum = sum + Integer.parseInt((String) array[i][3]);
            }
        }
        
        return sum;
    }
    
    //Method to sort out only one target month
    public ArrayList<ArrayList<String>> onlyMonth(ArrayList<ArrayList<String>> fullArray, int month)
    {
        ArrayList<ArrayList<String>> monthArray = new ArrayList<ArrayList<String>>();
        int newestData = fullArray.size();
        
        for (int i = 0; i<newestData; i++)
        {
            int tempMonth = Integer.parseInt(fullArray.get(i).get(2));
            if (tempMonth == month)
            {
                ArrayList<String> temp = fullArray.get(i);
                monthArray.add(temp);
            }
        }
        return monthArray;
    }
    
    //Method to sort days-spending into months-spending
    public ArrayList<ArrayList<String>> sortMonth(ArrayList<ArrayList<String>> spending, Object[][] dataArray)
    {
        ArrayList<ArrayList<String>> sortedList = new ArrayList<ArrayList<String>>();
        
        for (int i = 0; i<spending.size(); i++)
        {
            OverallSpending calculation = new OverallSpending();
            
            if(i == spending.size()-1)
            {
                //Get month
                int tempMonth = Integer.parseInt(spending.get(i).get(2));
                String monthName = calculation.monthName(tempMonth);
                
                //Get amount
                int tempSum = calculation.sumAmount(dataArray, tempMonth);
                
                //Add to sorted list
                ArrayList<String> temp = new ArrayList();
                temp.add(monthName);
                temp.add(Integer.toString(tempSum));
                temp.add("Detail of this month"); //This is for buttons
                sortedList.add(temp);
            }
            
            else if (i < spending.size()-1)
            {
            //Get month
            int tempMonth = Integer.parseInt(spending.get(i).get(2));
            int nextMonth = Integer.parseInt(spending.get(i+1).get(2));
            
                if (tempMonth != nextMonth)
                {
                    String monthName = calculation.monthName(tempMonth);

                    //Get amount
                    int tempSum = calculation.sumAmount(dataArray, tempMonth);
                    
                    //Add to sorted list
                    ArrayList<String> temp = new ArrayList();
                    temp.add(monthName);
                    temp.add(Integer.toString(tempSum));
                    temp.add("Detail of this month"); //This is for buttons
                    sortedList.add(temp);

                }
            }
        }
        
        return sortedList;
    }
    
    //Method to change from month number to month name
    public String monthName(int monthNumber)
    {
        String monthName = null;
        
        if(monthNumber == 1)
        {
            monthName = "January";
        }
        if(monthNumber == 2)
        {
            monthName = "February";
        }
        if(monthNumber == 3)
        {
            monthName = "March";
        }
        if(monthNumber == 4)
        {
            monthName = "Abril";
        }
        if(monthNumber == 5)
        {
            monthName = "May";
        }
        if(monthNumber == 6)
        {
            monthName = "June";
        }
        if(monthNumber == 7)
        {
            monthName = "July";
        }
        if(monthNumber == 8)
        {
            monthName = "August";
        }
        if(monthNumber == 9)
        {
            monthName = "September";
        }
        if(monthNumber == 10)
        {
            monthName = "October";
        }
        if(monthNumber == 11)
        {
            monthName = "November";
        }
        if(monthNumber == 12)
        {
            monthName = "December";
        }
                
        return monthName;
    }
    
    //Method to change from month name to month number
    public int monthNumber(String monthName)
    {
        int monthNumber = 0;
        if(monthName.equals("January"))
        {
            monthNumber = 1;
        }
        if(monthName.equals("February"))
        {
            monthNumber = 2;
        }
        if(monthName.equals("March"))
        {
            monthNumber = 3;
        }
        if(monthName.equals("Abril"))
        {
            monthNumber = 4;
        }
        if(monthName.equals("May"))
        {
            monthNumber = 5;
        }
        if(monthName.equals("June"))
        {
            monthNumber = 6;
        }
        if(monthName.equals("July"))
        {
            monthNumber = 7;
        }
        if(monthName.equals("August"))
        {
            monthNumber = 8;
        }
        if(monthName.equals("September"))
        {
            monthNumber = 9;
        }
        if(monthName.equals("October"))
        {
            monthNumber = 10;
        }
        if(monthName.equals("November"))
        {
            monthNumber = 11;
        }
        if(monthName.equals("December"))
        {
            monthNumber = 12;
        }
        return monthNumber;
    }
    
    public boolean checkLimit(ArrayList<ArrayList<String>> spending, int limit)
    {
        boolean excess = false;
        int latestMonth =  Integer.parseInt(spending.get(spending.size()-1).get(2));
        
        //change into 2dArray to use method
        Database myDb = new Database();
        Object[][] spendingList = myDb.to2dArray(spending);
        
        int sumAmount = sumAmount(spendingList, latestMonth);
        
        if (sumAmount > limit)
        {
            excess = true;
        }
        
        return excess;
    }
}
