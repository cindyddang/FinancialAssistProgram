/* November 9, 2019
 * This frame displays table for data in a month
*/
package financialassist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class MonthlyReport extends JFrame
{
    //declare constants
    private final Color FINAL_COLOR = new Color(26, 46, 90);
    private final java.net.URL IMAGE_URL = getClass().getResource("logo.png");
    private final ImageIcon LOGO_IMAGE = new ImageIcon(new ImageIcon(IMAGE_URL).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
    
    //labels
    private JLabel headerLabel;
    private JLabel logoLabel;
    private JLabel sumLabel;
    
    //Declare variable relating to table
    private JTable displayTable;
    private final String[] TABLE_HEADER = {"Order", "Day", "Month", "Amount($)", "Reason", "Payer"};
    private Object[][] spending;
    private JScrollPane historyPane;
    private TableColumn tableColumn;
    private JTableHeader headerTable;
    
    //panels
    private JPanel northPanel;
    private JPanel southPanel;
    
    public MonthlyReport(int month)
    {
        //Constructing frame
        super("Monthly Report");
        this.setBounds(400, 200, 800, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //Connect to database
        String[] columnName = {"OrderNumber", "Day", "Month", "Amount", "Reason", "Payer"};
        Database database = new Database("FinancialAssistant");
        Connection myCon = null;
        myCon = database.getConnection();
        
        ArrayList<ArrayList<String>> spending = database.getDataList(("Spending"),columnName);
        
        for(int k=0; k<spending.size(); k++)
        {
            spending.get(k);
        }
        
        //Only get the chosen month from data list
        OverallSpending calculation = new OverallSpending();
        spending = calculation.onlyMonth(spending, month);
    
        Object[][] dataArray = database.to2dArray(spending);
        
        this.spending = dataArray;
        
        //Constructing table
        displayTable = new JTable(this.spending, TABLE_HEADER);
        displayTable.setGridColor(FINAL_COLOR);
        
        //Constructing header of table
        headerTable = displayTable.getTableHeader();
        headerTable.setBackground(Color.WHITE);
        headerTable.setForeground(FINAL_COLOR);
        headerTable.setFont(new Font("Times New Roman", Font.BOLD,15));
        
        //Formatting columns of table
        tableColumn = displayTable.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(10);
        tableColumn = displayTable.getColumnModel().getColumn(1);
        tableColumn.setPreferredWidth(5);
        tableColumn = displayTable.getColumnModel().getColumn(2);
        tableColumn.setPreferredWidth(20);
        tableColumn = displayTable.getColumnModel().getColumn(4);
        tableColumn.setPreferredWidth(400);
        tableColumn = displayTable.getColumnModel().getColumn(5);
        tableColumn.setPreferredWidth(80);
     
        //display table
        historyPane = new JScrollPane();
        historyPane.getViewport().add(displayTable);
        displayTable.setVisible(true);
        
        //Change month from number into name
        String monthName = calculation.monthName(month);
        //Calculating the sum of the month
        int sum = calculation.sumAmount(dataArray, month);
        
        //Constructing labels
        headerLabel = new JLabel(monthName + "'s report");
        headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        logoLabel = new JLabel(LOGO_IMAGE);
        sumLabel = new JLabel("Total spending of this month is $" + sum);
        sumLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        sumLabel.setForeground(Color.WHITE);
        
        //Constructing panels
        northPanel = new JPanel();
        northPanel.setBackground(FINAL_COLOR);
        northPanel.add(logoLabel);
        northPanel.add(headerLabel);
        southPanel = new JPanel();
        southPanel.setBackground(FINAL_COLOR);
        southPanel.add(sumLabel);
        
        //Adding components into frame
        this.add(northPanel, BorderLayout.NORTH);
        this.add(historyPane, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        
        //Making frame visible
        this.setVisible(true);
    }
    
    //main method to test 
    public static void main(String[] args)
    {
        //passing a value for testing
        MonthlyReport monthReport = new MonthlyReport(1);
    }
}
