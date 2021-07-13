/* November 10, 2019
 * This frame display table of a person's spending
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


public class PersonReport extends JFrame
{
    //declare constants
    private final Color FINAL_COLOR = new Color(26, 46, 90);
    private final java.net.URL IMAGE_URL = getClass().getResource("logo.png");
    private final ImageIcon LOGO_IMAGE = new ImageIcon(new ImageIcon(IMAGE_URL).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
    
    //labels
    private JLabel headerLabel;
    private JLabel logoLabel;
    private JLabel totalLabel;
    
    //Declare variable relating to table
    private JTable displayTable;
    private final String[] TABLE_HEADER = {"Day", "Month", "Amount($)", "Reason"};
    private Object[][] spending;
    private JScrollPane historyPane;
    private JTableHeader headerTable;
    
    //panels
    private JPanel northPanel;
    private JPanel southPanel;
    
    public PersonReport(String name)
    {
        //Constructing frame
        super(" Person Report ");
        this.setBounds(300, 100, 800, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //Connect to databas
        String[] columnName = {"Name", "Day", "Month", "Paid", "Spending", "Reason"};
        Database database = new Database("FinancialAssistant");
        Connection myCon = null;
        myCon = database.getConnection();
        
        //put data into an array
        ArrayList<ArrayList<String>> spendArray = database.getDataList(("People"),columnName);
        for (int k=0; k<spendArray.size(); k++)
        {
            spendArray.get(k);
        }
        
        //sorted data into to get needed info only
        PersonalSpending personCal = new PersonalSpending();
        ArrayList<ArrayList<String>> tempArray = personCal.personSpend(spendArray, name);
        int sumAmount = personCal.sumAmount(spendArray, name);
        Object[][] tempObj = database.to2dArray(tempArray);
        this.spending = tempObj;
        
        //Constructing labels
        headerLabel = new JLabel(name + "'s report");
        headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        logoLabel = new JLabel(LOGO_IMAGE);
        totalLabel = new JLabel("Total spending of this person is $" + sumAmount);
        totalLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        totalLabel.setForeground(Color.WHITE);
        
        //Constructing panels
        northPanel = new JPanel();
        northPanel.add(headerLabel);
        northPanel.add(logoLabel);
        northPanel.setBackground(FINAL_COLOR);
        southPanel = new JPanel();
        southPanel.setBackground(FINAL_COLOR);
        southPanel.add(totalLabel);
        
        //Constructing table
        displayTable = new JTable(spending, TABLE_HEADER);
        displayTable.setGridColor(Color.black);
        
        //Constructing header of table
        headerTable = displayTable.getTableHeader();
        headerTable.setBackground(Color.WHITE);
        headerTable.setForeground(FINAL_COLOR);
        headerTable.setFont(new Font("Times New Roman", Font.BOLD,15));
     
        //display table
        historyPane = new JScrollPane();
        historyPane.getViewport().add(displayTable);
        displayTable.setVisible(true);
        
        //Adding components into frame
        this.add(northPanel, BorderLayout.NORTH);
        this.add(historyPane, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        
        //Making frame visible
        this.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        //passing a random value for testing
        PersonReport perReport = new PersonReport("Danis");
    }
}
