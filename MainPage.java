/* November 4, 2019
 * This GUI frame displays all function of the program.
*/
package financialassist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainPage extends JFrame implements ActionListener
{
    //declare constants
    private final Color FINAL_COLOR = new Color(26, 46, 90);
    private final java.net.URL IMAGE_URL = getClass().getResource("logo.png");
    private final ImageIcon LOGO_IMAGE = new ImageIcon(new ImageIcon(IMAGE_URL).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
    
    //declare input labels and fields
    private JLabel dayLabel;
    private JTextField dayField;
    private JLabel monthLabel;
    private JTextField monthField;
    private JLabel amountLabel;
    private JTextField amountField;
    private JLabel reasonLabel;
    private JTextField reasonField;
    private JTextField nameField;
    private JLabel orderLabel;
    private JTextField orderField;
    private JTextField limitField;
    
    //Other JLabel
    private JLabel headerLabel;
    private JLabel displayLabel;
    private JLabel logoLabel;
    private JLabel dashLabel;
    private JLabel paidLabel;
    private JLabel responLabel;
    
    //declare box
    private Box dayBox;
    private Box monthBox;
    private Box amountBox;
    private Box reasonBox;
    private Box buttonBox;
    private Box button1Box;
    private Box button2Box;
    
    //declaring buttons
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton addPersonButton;
    private JButton setLimitButton;
    private JButton historyButton;
    private JButton monthRecButton;
    private JButton monthRepButton;
    private JButton peopleRecButton;
    private JButton perReportButton;
    private JButton paySuggButton;
    
    //Checkbox
    private JCheckBox[] nameBox;
    private JCheckBox[] paidBox;
    
    //panels
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel inputPanel;
    private JPanel functionPanel;
    private JPanel additionalPanel;
    private JPanel displayPanel;
    private JPanel checkBoxPanel;
    private JPanel paidBoxPanel;
            
    public MainPage()
    {
        //Constructing frame
        super("Main Menu");
        this.setBounds(250, 300, 800, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //Constructing labels
        headerLabel = new JLabel("Financial Assistant");
        headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        dayLabel = new JLabel("Day");
        monthLabel = new JLabel("Month");
        amountLabel = new JLabel("Amount($)");
        reasonLabel = new JLabel("Spend for");
        orderLabel = new JLabel("Order: ");
        displayLabel = new JLabel("_________________ DISPLAY ___________________________________________________________________________");
        logoLabel = new JLabel(LOGO_IMAGE);
        dashLabel = new JLabel(" | ");
        paidLabel = new JLabel("Who paid this?");
        responLabel = new JLabel("Who's responsible for this payment?");
        
        //Constructing textfields
        dayField = new JTextField(2);
        monthField = new JTextField(2);
        amountField = new JTextField(10);
        reasonField = new JTextField(40);
        orderField = new JTextField(5);
        nameField = new JTextField(20);
        limitField = new JTextField(10);
        
        //Connect to database to get names
        Connection connection = null;
        Database objDb = new Database("FinancialAssistant");
        connection = objDb.getConnection();
        
        String[] columnName = {"Name"};
        ArrayList<ArrayList<String>> people = objDb.getDataList(("Names"),columnName);
        nameBox = new JCheckBox[people.size()];
        paidBox = new JCheckBox[people.size()];
        
        for (int i = 0; i < nameBox.length; i++)
        {
            ArrayList<String> tempArray = people.get(i);
            String tempName = tempArray.get(0);
            nameBox[i] = new JCheckBox(tempName);
            nameBox[i].addActionListener(this);
            paidBox[i] = new JCheckBox(tempName);
            paidBox[i].addActionListener(this);
            
        }
        
        //Adding checkboxes into panel to display
        checkBoxPanel = new JPanel();
        checkBoxPanel.add(responLabel);
        checkBoxPanel.setBackground(Color.WHITE);
        for (int i = 0; i < nameBox.length; i++)
        {
            checkBoxPanel.add(nameBox[i]);
        }
        paidBoxPanel = new JPanel();
        paidBoxPanel.add(paidLabel);
        paidBoxPanel.setBackground(Color.WHITE);
        for (int i = 0; i < paidBox.length; i++)
        {
            paidBoxPanel.add(paidBox[i]);
        }
        
        //Constructing buttons
        insertButton = new JButton("INSERT");
        updateButton = new JButton("UPDATE");
        deleteButton = new JButton("DELETE");
        addPersonButton = new JButton("ADD PERSON");
        setLimitButton = new JButton("SET LIMIT");
        historyButton = new JButton("SPENDING HISTORY");
        monthRecButton = new JButton("MONTHLY SPENDING");
        monthRepButton = new JButton("MONTHLY REPORT");
        peopleRecButton = new JButton("PEOPLE RECORD");
        perReportButton = new JButton("PERSON REPORT");
        paySuggButton = new JButton("PAYING SUGGEST");
        
        //Adding Action Listeners to Buttons
        insertButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        addPersonButton.addActionListener(this);
        setLimitButton.addActionListener(this);
        historyButton.addActionListener(this);
        monthRecButton.addActionListener(this);
        monthRepButton.addActionListener(this);
        peopleRecButton.addActionListener(this);
        perReportButton.addActionListener(this);
        paySuggButton.addActionListener(this);
        
        //Constructing box
        dayBox = Box.createVerticalBox();
        dayBox.add(dayLabel);
        dayBox.add(dayField);
        monthBox = Box.createVerticalBox();
        monthBox.add(monthLabel);
        monthBox.add(monthField);
        amountBox = Box.createVerticalBox();
        amountBox.add(amountLabel);
        amountBox.add(amountField);
        reasonBox = Box.createVerticalBox();
        reasonBox.add(reasonLabel);
        reasonBox.add(reasonField);
        button1Box = Box.createHorizontalBox();
        button1Box.add(historyButton);
        button1Box.add(monthRecButton);
        button1Box.add(monthRepButton);
        button2Box = Box.createHorizontalBox();
        button2Box.add(peopleRecButton);
        button2Box.add(perReportButton);
        button2Box.add(paySuggButton);
        buttonBox = Box.createVerticalBox();
        buttonBox.add(button1Box);
        buttonBox.add(button2Box);
                      
        //Constructing panels
        northPanel = new JPanel();
        northPanel.setBackground(FINAL_COLOR);
        northPanel.add(logoLabel);
        northPanel.add(headerLabel);
        inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.add(dayBox);
        inputPanel.add(monthBox);
        inputPanel.add(amountBox);
        inputPanel.add(reasonBox);
        functionPanel = new JPanel();
        functionPanel.setBackground(Color.WHITE);
        functionPanel.add(insertButton);
        functionPanel.add(dashLabel);
        functionPanel.add(updateButton);
        functionPanel.add(deleteButton);
        functionPanel.add(orderLabel);
        functionPanel.add(orderField);
        additionalPanel = new JPanel();
        additionalPanel.setBackground(Color.WHITE);
        additionalPanel.add(nameField);
        additionalPanel.add(addPersonButton);
        additionalPanel.add(limitField);
        additionalPanel.add(setLimitButton);
        displayPanel = new JPanel();
        displayPanel.setBackground(Color.WHITE);
        displayPanel.add(buttonBox);
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(inputPanel);
        centerPanel.add(paidBoxPanel);
        centerPanel.add(checkBoxPanel);
        centerPanel.add(functionPanel);
        centerPanel.add(additionalPanel);
        centerPanel.add(displayLabel);
        centerPanel.add(displayPanel);
        
        //Adding components into frame
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        
        //Make the frame visible
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
       String command = e.getActionCommand();
       if(command.equals("INSERT"))
       {
           try
           {
               if(Integer.parseInt(monthField.getText()) <=12 && Integer.parseInt(dayField.getText()) <= 31)
               {
                   int dayVar = Integer.parseInt(dayField.getText());
                   int monthVar = Integer.parseInt(monthField.getText());
                   int amountVar = Integer.parseInt(amountField.getText());
                   int order = 1;
                   String nameVar = "";
                   String reasonVar = reasonField.getText();

                   Connection connection = null;
                   Database objDb = new Database("FinancialAssistant");
                   connection = objDb.getConnection();
                   String dbQuery = "INSERT INTO Spending VALUES (?,?,?,?,?,?)";

                   //Checking order from database
                   String[] columnName = {"OrderNumber", "Day", "Month", "Amount", "Reason", "Payer"};
                   ArrayList<ArrayList<String>> spending = objDb.getDataList(("Spending"),columnName);

                   //Getting order and add 1
                   for (int k=0; k<spending.size(); k++)
                   {
                       spending.get(k);
                   }

                   if(spending.size() >= 1)
                   {
                       Object[][] dataArray = objDb.to2dArray(spending);

                       int tempOrder = Integer.parseInt((String) dataArray[dataArray.length - 1][0]);
                       order = tempOrder + 1;
                   }

                   //Check if JCheckbox is Selected
                   for (int i = 0; i < paidBox.length; i++ )
                   {
                       String[] columnPeopl = {"Name"};
                       ArrayList<ArrayList<String>> people = objDb.getDataList(("Names"),columnPeopl);

                       if (paidBox[i].isSelected())
                       {
                           ArrayList<String> tempArray = people.get(i);
                           String tempName = tempArray.get(0);
                           nameVar = nameVar + " " + tempName;
                       }
                   }

                   //Insert data into spending table
                   try
                   {
                       PreparedStatement ps = connection.prepareStatement(dbQuery);
                       ps.setInt(1, order);
                       ps.setInt(2, dayVar);
                       ps.setInt(3, monthVar);
                       ps.setInt(4, amountVar);
                       ps.setString(5, reasonVar);
                       ps.setString(6, nameVar);
                       ps.executeUpdate();
                       
                       String noti = "Data inserted successfully";
                       Notification notiFrame = new Notification(noti);
                   }

                   catch (SQLException se)
                   {
                       System.out.println("Error inserting data");
                       se.printStackTrace(System.err);
                   }

                   //Calculate equal amount
                   int numberPeople = 0;
                   int paidPeople = 0;
                   for (int i = 0; i < nameBox.length; i++)
                   {
                       if (nameBox[i].isSelected())
                       {
                           numberPeople ++;
                       }
                       if (paidBox[i].isSelected())
                       {
                           paidPeople++;
                       }
                   }

                   int responAmount = amountVar/numberPeople;
                   int paidAmount = amountVar/paidPeople;

                   //Insert data into people table
                   for (int i = 0; i< nameBox.length;i++)
                   {

                       String[] columnPeopl = {"Name"};
                       ArrayList<ArrayList<String>> people = objDb.getDataList(("Names"),columnPeopl);

                       String peopleQuery = "INSERT INTO People VALUES (?,?,?,?,?,?,?)";

                       if (nameBox[i].isSelected())
                       {
                           ArrayList<String> tempArray = people.get(i);
                           String tempName = tempArray.get(0);
                           nameVar = tempName;

                           int paidVar = 0;

                           if (paidBox[i].isSelected())
                           {
                               paidVar = paidAmount;
                           }

                           try 
                           {
                               PreparedStatement peoplePS = connection.prepareStatement(peopleQuery);
                               peoplePS.setInt(1, order);
                               peoplePS.setString(2, nameVar);
                               peoplePS.setInt(3, dayVar);
                               peoplePS.setInt(4, monthVar);
                               peoplePS.setInt(5, paidVar);
                               peoplePS.setInt(6, responAmount);
                               peoplePS.setString(7, reasonVar);
                               peoplePS.executeUpdate();
                           } 
                           catch (SQLException ex) 
                           {
                               System.out.println("Error inserting data");
                               ex.printStackTrace(System.err); 
                           }
                       }
                   }
                   
                //checking limit
                boolean exceed;
                ArrayList<ArrayList<String>> newSpending = objDb.getDataList(("Spending"),columnName);
                String[] limitColumn = {"Limit"};
                ArrayList<ArrayList<String>> limitData = objDb.getDataList(("Limit"),limitColumn);
                int limit = Integer.parseInt(limitData.get(0).get(0));
                OverallSpending calExcess = new OverallSpending();
                exceed = calExcess.checkLimit(newSpending, limit);
                
                if (exceed == true)
                {
                    String noti = "Spending of this month has excessed the limit. Limit: " + limit ;
                    Notification notiFrame = new Notification(noti);
                }
               }

               else
               {
                   String noti = "Please put day and month in correct fields";
                   Notification notiFrame = new Notification(noti);
               }
                
           }
           catch (NumberFormatException nfe)
           {
               Notification warningPage = new Notification("Please fill the text fields with correct input");
           }
       }
       if(command.equals("UPDATE"))
       {
           try
           {
               
               Connection connection = null;
               Database objDb = new Database("FinancialAssistant");
               String[] columnPeopl = {"Name"};
               ArrayList<ArrayList<String>> peopleName = objDb.getDataList(("Names"),columnPeopl);
               connection = objDb.getConnection();
               
               int order = Integer.parseInt(orderField.getText());
               String dbQuery = "UPDATE Spending SET ";
               String peopleQuery = "UPDATE People SET ";

               //Look for field to get what user wants to update
               if(!dayField.getText().equals(""))
               {
                   if(Integer.parseInt(dayField.getText()) <= 31)
                   {
                       int dayVar = Integer.parseInt(dayField.getText());
                       dbQuery = dbQuery + "Day = " + dayVar+ "";
                       peopleQuery = peopleQuery + "Day = " + dayVar + "";
                   }
                   else
                   {
                       String noti = "Please put day and month in correct fields";
                       Notification notiFrame = new Notification(noti);
                   }
               }

               if(!monthField.getText().equals(""))
               {
                   if(Integer.parseInt(monthField.getText()) <=12)
                   {
                       if(!dayField.getText().equals(""))
                       {
                           dbQuery = dbQuery + ", ";
                           peopleQuery = peopleQuery + ",";
                       }

                       int monthVar = Integer.parseInt(monthField.getText());
                       dbQuery = dbQuery + "Month = " + monthVar + "";
                       peopleQuery = peopleQuery + "Month = " + monthVar + "";
                   }
                   else
                   {
                       String noti = "Please put day and month in correct fields";
                       Notification notiFrame = new Notification(noti);
                   }
               }

               if(!amountField.getText().equals(""))
               {
                   if(!dayField.getText().equals("") || !dayField.getText().equals(""))
                   {
                       dbQuery = dbQuery + ", ";
                       peopleQuery = peopleQuery + ", ";
                   }
                   
                   //Prepare statement for spending table
                   int amountVar = Integer.parseInt(amountField.getText());
                   dbQuery = dbQuery + "Amount = " + amountVar + "";
                   
                   //Check if people are also updated
                   int numberPeople = 0;
                   for (int i = 0; i < nameBox.length; i++)
                   {
                       if (nameBox[i].isSelected())
                       {
                           numberPeople ++;
                       }
                   }
                   
                   //If there is no update for people, look for number of people in database
                   if(numberPeople == 0)
                   {
                       String[] columnName = {"OrderNumber"};
                       ArrayList<ArrayList<String>> peopleList = objDb.getDataList(("People"),columnName);
                       
                       for(int i = 0; i<peopleList.size(); i++)
                       {
                           if(orderField.getText().equals(peopleList.get(i).get(0)))
                           {
                               numberPeople++;
                           }
                       }
                   }
                   
                   int responAmount = amountVar/numberPeople;
                   
                   //Prepare statement to update people table
                   peopleQuery = peopleQuery + "Spending = " + responAmount + "";
               }

               if(!reasonField.getText().equals(""))
               {
                   if(!dayField.getText().equals("") || !dayField.getText().equals("") || !amountField.getText().equals(""))
                   {
                       dbQuery = dbQuery + ", ";
                       peopleQuery = peopleQuery + ", ";
                   }
                   dbQuery = dbQuery + "Reason = '" + reasonField.getText() + "' ";
                   peopleQuery = peopleQuery + "Reason = '" + reasonField.getText() + " '";
               }
               
               //update payer in spending
//               if(!amountField.getText().equals(""))
//               {
//                   
////                   for (int i = 0; i < nameBox.length; i++)
////                   {
////                       String[] columnName = { "Name"};
////                       ArrayList<ArrayList<String>> nameList = objDb.getDataList(("Names"),columnName);
////                       if(i == 0)
////                       {
////                           //Update spending statement
////                           dbQuery = dbQuery + "Payer = '" + nameList.get(i).get(0) + "' ";
////                       }
////                       if(i > 0)
////                       {
////                           dbQuery = dbQuery + ", " nameList.get(i).get(0) + "' ";
////                       }
////                   }
//               }
               //people table will be updated differently
               boolean onlyPeople = false;
               
               //Add condition for updating
               if(peopleQuery.equals("UPDATE People SET "))
               {
                   onlyPeople = true;
               }
               
               dbQuery = dbQuery + " WHERE OrderNumber = " + order +"";
               peopleQuery = peopleQuery + " WHERE OrderNumber = " + order + "";
               
               //Update
               if(onlyPeople == false)
               {
                   try
                   {
                       PreparedStatement ps = connection.prepareStatement(dbQuery);
                       PreparedStatement peoplePS = connection.prepareStatement(peopleQuery);
                       ps.executeUpdate();
                       peoplePS.executeUpdate();

                       String noti = "Data updated successfully";
                       Notification notiFrame = new Notification(noti);
                    }
                    catch(SQLException se)
                    {
                       System.out.println("Error updating data");
                        se.printStackTrace(System.err);
                    }
               }
               
               //Update paid and spending in people
               //which actually delete and insert again
               boolean peopleUpdate = false;
               
               for(int j = 0; j<nameBox.length; j++)
               {
                   if(nameBox[j].isSelected() || paidBox[j].isSelected())
                   {
                       peopleUpdate = true;
                   }
               }
               
               if(peopleUpdate == true)
               {
                   if(amountField.getText().equals(""))
                   {     
                       try
                       {
                           String insertPeople = "INSERT INTO People VALUES (?,?,?,?,?,?,?)";
                           String peopleQuery1 = "DELETE FROM People WHERE OrderNumber = " + Integer.parseInt(orderField.getText()) + "";
                           
                           try
                           {
                               PreparedStatement ps = connection.prepareStatement(peopleQuery1);
                               ps.executeUpdate();
                           }
                           
                           catch(SQLException se)
                           {
                               System.out.println("Error deleting");
                               se.printStackTrace(System.err);
                           }

                           for (int i = 0; i < nameBox.length; i++)
                           {
                               String[] columnName = { "Name"};
                               ArrayList<ArrayList<String>> nameList = objDb.getDataList(("Names"),columnName);
                               String[] spendingColumn = {"OrderNumber", "Day" , "Month", "Amount", "Reason"};
                               ArrayList<ArrayList<String>> amountList = objDb.getDataList(("Spending"),spendingColumn);
                               

                               int orderVar = Integer.parseInt(amountList.get(Integer.parseInt(orderField.getText())-1).get(0));
                               int dayVar = Integer.parseInt(amountList.get(Integer.parseInt(orderField.getText())-1).get(1));
                               int monthVar = Integer.parseInt(amountList.get(Integer.parseInt(orderField.getText())-1).get(2));
                               int amountVar = Integer.parseInt(amountList.get(Integer.parseInt(orderField.getText())-1).get(3));
                               String reasonVar = amountList.get(Integer.parseInt(orderField.getText())-1).get(4);


                               int numberPeople = 0;
                               int paidPeople = 0;
                               for (int j = 0; j < nameBox.length; j++)
                               {
                                   if (nameBox[j].isSelected())
                                   {
                                       numberPeople ++;
                                   }
                                   if (paidBox[j].isSelected())
                                   {
                                       paidPeople ++;
                                   }
                               }

                               int responAmount = amountVar/numberPeople;
                               int paidAmount = amountVar/paidPeople;
                               
                               if (nameBox[i].isSelected())
                               {

                                   ArrayList<String> tempArray = nameList.get(i);
                                   String nameVar = tempArray.get(0);
                                   String payerUpdate = "UPDATE Spending SET Payer = '";
                                   boolean paid = false;

                                   int paidVar = 0;

                                   if (paidBox[i].isSelected())
                                   {
                                       paid = true;
                                       payerUpdate = payerUpdate + nameVar + "' WHERE OrderNumber = "+ Integer.parseInt(orderField.getText());
                                       paidVar = paidAmount;
                                   }

                                   try 
                                   {
                                       if(paid == true)
                                       {
                                           PreparedStatement payerStatement = connection.prepareStatement(payerUpdate);
                                           payerStatement.executeUpdate();
                                       }

                                       PreparedStatement peoplePS = connection.prepareStatement(insertPeople);
                                       peoplePS.setInt(1, orderVar);
                                       peoplePS.setString(2, nameVar);
                                       peoplePS.setInt(3, dayVar);
                                       peoplePS.setInt(4, monthVar);
                                       peoplePS.setInt(5, paidVar);
                                       peoplePS.setInt(6, responAmount);
                                       peoplePS.setString(7, reasonVar);
                                       peoplePS.executeUpdate();
                                   } 

                                   catch (SQLException ex) 
                                   {
                                       System.out.println("Error inserting people table data");
                                       ex.printStackTrace(System.err); 
                                   }
                               }
                               
                           }
                       }
                       catch(ArithmeticException ae)
                       {
                           String noti = "Please select paid AND responsible people.";
                           Notification notiFrame = new Notification(noti);
                       }
                   }
               }
           }
           catch (NumberFormatException nfe)
           {
               System.out.println("Error inserting people table data");
               nfe.printStackTrace(System.err); 
               Notification notiFrame = new Notification("Enter spending order you want to update based on spending history");
           }
           
       }
       if(command.equals("DELETE"))
       {
           try
           {
               int order = Integer.parseInt(orderField.getText());
               Connection connection = null;
               Database objDb = new Database("FinancialAssistant");
               
               connection = objDb.getConnection();
               String dbQuery = "DELETE FROM Spending WHERE OrderNumber = " + order + "";
               String peopleQuery = "DELETE FROM People WHERE OrderNumber = " + order + "";
               
               try
               {
                   PreparedStatement ps = connection.prepareStatement(dbQuery);
                   PreparedStatement peoplePS = connection.prepareStatement(peopleQuery);
                   ps.executeUpdate();
                   peoplePS.executeUpdate();
                   
                   
                   String noti = "Data deleted successfully";
                   Notification notiFrame = new Notification(noti);
               }
               
               catch (SQLException se)
               {
                   System.out.println("Error deleting data");
                   se.printStackTrace(System.err);
               }
           }
           catch (NumberFormatException nfe)
           {
               Notification warningPage = new Notification("Please put number into order field");
           }
       }
       if(command.equals("ADD PERSON"))
       {  
            String name = nameField.getText();
            
            Connection connection = null;
            Database objDb = new Database("FinancialAssistant");
            connection = objDb.getConnection();
            String dbQuery = "INSERT INTO Names VALUES (?)";
            
           
            try
            {
                PreparedStatement ps = connection.prepareStatement(dbQuery);
                ps.setString(1, name);
                ps.executeUpdate();
            } 

            catch(SQLException se)
            {
                System.out.println("Error inserting data");
                se.printStackTrace(System.err);
            }
            
            //refresh the program with new names
            this.dispose();
            MainPage newMain = new MainPage();
       }
       if (command.equals("SET LIMIT"))
       {
           try
           {
               int limit = Integer.parseInt(limitField.getText());

               Connection connection = null;
               Database objDb = new Database("FinancialAssistant");
               connection = objDb.getConnection();
               String dbQuery = "INSERT INTO Limit VALUES (?)";

               try
               {
                   PreparedStatement ps = connection.prepareStatement(dbQuery);
                   ps.setInt(1, limit);
                   ps.executeUpdate();
                   String noti = "New limit is set";
                   Notification notiFrame = new Notification(noti); 
               }

               catch(SQLException se)
               {
                   System.out.println("Error inserting data");
                   se.printStackTrace(System.err);
               }
           }
           catch (NumberFormatException nfe)
           {
               Notification warningFrame = new Notification("Please  only put number into "
                       + "limit field.");
           }
       }
       if (command.equals("MONTHLY SPENDING"))
       {
           MonthlySpending monthRecFrame = new MonthlySpending();
       }
       if (command.equals("MONTHLY REPORT"))
       {
           //If textfield is empty, display report for the latest month
           if (monthField.getText().trim().isEmpty())
           {
               String[] columnName = {"OrderNumber", "Day", "Month", "Amount", "Reason", "Payer"};
               Database database = new Database("FinancialAssistant");
               Connection myCon = null;
               myCon = database.getConnection();
               
               ArrayList<ArrayList<String>> spending = database.getDataList(("Spending"),columnName);

               for(int k=0; k<spending.size(); k++)
               {
                   spending.get(k);
               }
               
               int monthVar = Integer.parseInt((String) spending.get(spending.size()-1).get(2));
               MonthlyReport monthRepFrame = new MonthlyReport(monthVar);
               String noti = "Month text field if empty so system automatically display newest input month.";
               Notification notiFrame = new Notification(noti);
           }
           
           //Passing the month value
           else
           {
               try
               {
                   int month = Integer.parseInt(monthField.getText());
                   MonthlyReport monthRepFrame = new MonthlyReport(month);
               }
               catch(NumberFormatException nfe)
               {
                   String noti = "Please only put month number into month field";
                   Notification notiFrame = new Notification(noti);
               }
           }
       }
       if (command.equals("PEOPLE RECORD"))
       {
           PeopleRecord peoRecFrame = new PeopleRecord();
           String noti = "Only display record for people who spend money in the latest month";
           Notification notiFrame = new Notification(noti);
       }
       if (command.equals("PERSON REPORT"))
       {
           //Passing value from text field
           if(nameField.getText().trim().isEmpty())
           {
               String tempName = "";
               
               for(int i = 0; i < paidBox.length; i++)
               {
                   if(paidBox[i].isSelected() || nameBox[i].isSelected())
                   {
                       Connection connection = null;
                       Database objDb = new Database("FinancialAssistant");
                       connection = objDb.getConnection();
                       
                       String[] columnPeopl = {"Name"};
                       
                       ArrayList<ArrayList<String>> people = objDb.getDataList(("Names"),columnPeopl);
                   
                       ArrayList<String> tempArray = people.get(i);
                       tempName = tempArray.get(0);
                   }
               }
               try
               {
                   PersonReport perReport = new PersonReport(tempName);
                   String noti = "Display report for last selected check box name";
                   Notification notiFrame = new Notification(noti);
               }
               
               catch(IndexOutOfBoundsException iob)
               {
                   String noti = "Put name in name field or choose name checkbox to display";
                   Notification notiFrame = new Notification(noti);
               }
           }
           
           else
           {
               //Getting name from name field
               String nameVar = nameField.getText();
               PersonReport perReport = new PersonReport(nameVar);
           }
       }
       if (command.equals("PAYING SUGGEST"))
       {
           PayingSuggest payingSugg = new PayingSuggest();
       }
       if (command.equals("SPENDING HISTORY"))
       {
           SpendingHistory spendingHis = new SpendingHistory();
       }
    }
    
    public static void main(String[] args)
    {
        MainPage mainPage = new MainPage();
    }
}