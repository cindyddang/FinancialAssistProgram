/* November 6, 2019
 * Display all data from database
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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class SpendingHistory extends JFrame
{
    //declare constants
    private final Color FINAL_COLOR = new Color(26, 46, 90);
    private final java.net.URL IMAGE_URL = getClass().getResource("logo.png");
    private final ImageIcon LOGO_IMAGE = new ImageIcon(new ImageIcon(IMAGE_URL).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
    
    //labels
    private JLabel headerLabel;
    private JLabel logoLabel;
    
    //Declare variable relating to table
    private JTable historyTable;
    private final String[] TABLE_HEADER = {"Order", "Day", "Month", "Amount($)", "Reason", "Payer"};
    private Object[][] spending;
    private JScrollPane historyPane;
    private TableColumn tableColumn;
    private JTableHeader headerTable;
    
    //panels
    private JPanel northPanel;
    
    public SpendingHistory()
    {
        //Constructing frame
        super("Spending History");
        this.setBounds(300, 100, 800, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //Constructing labels
        headerLabel = new JLabel("SPENDING HISTORY");
        headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        logoLabel = new JLabel(LOGO_IMAGE);
        
        //Constructing panels
        northPanel = new JPanel();
        northPanel.add(logoLabel);
        northPanel.add(headerLabel);
        northPanel.setBackground(FINAL_COLOR);
        
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
        
        Object[][] dataArray = database.to2dArray(spending);
        
        this.spending = dataArray;
        
        //Constructing table
        historyTable = new JTable(this.spending, TABLE_HEADER);
        historyTable.setGridColor(Color.black);
        
        //Formatting columns of table
        tableColumn = historyTable.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(10);
        tableColumn = historyTable.getColumnModel().getColumn(1);
        tableColumn.setPreferredWidth(5);
        tableColumn = historyTable.getColumnModel().getColumn(2);
        tableColumn.setPreferredWidth(20);
        tableColumn = historyTable.getColumnModel().getColumn(3);
        tableColumn.setPreferredWidth(100);
        tableColumn = historyTable.getColumnModel().getColumn(4);
        tableColumn.setPreferredWidth(400);
        tableColumn = historyTable.getColumnModel().getColumn(5);
        tableColumn.setPreferredWidth(100);
        
        //Constructing header of table
        headerTable = historyTable.getTableHeader();
        headerTable.setBackground(Color.WHITE);
        headerTable.setForeground(FINAL_COLOR);
        headerTable.setFont(new Font("Times New Roman", Font.PLAIN,13));
     
        historyPane = new JScrollPane();
        historyPane.getViewport().add(historyTable);
        historyTable.setVisible(true);
        
        //Adding components into frame
        this.add(northPanel, BorderLayout.NORTH);
        this.add(historyPane, BorderLayout.CENTER);
        
        //Making frame visible
        this.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        SpendingHistory historyFrame = new SpendingHistory();
    }
    
}
