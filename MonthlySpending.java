/* November 8, 2019
 * This frame displays table for total spending from every month
*/
package financialassist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;


public class MonthlySpending extends JFrame
{
    //declare constants
    private final Color FINAL_COLOR = new Color(26, 46, 90);
    private final java.net.URL IMAGE_URL = getClass().getResource("logo.png");
    private final ImageIcon LOGO_IMAGE = new ImageIcon(new ImageIcon(IMAGE_URL).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
    
    //labels
    private JLabel headerLabel;
    private JLabel logoLabel;
    
    //Declare variable relating to table
    private JTable displayTable;
    private final String[] TABLE_HEADER = {"Month", "Amount($)", "Detail"};
    private Object[][] spending;
    private JScrollPane historyPane;
    private JTableHeader headerTable;
    
    //panels
    private JPanel northPanel;   
    private JPanel southPanel;
    
    public MonthlySpending()
    {
        //Constructing frame
        super("Monthly Spending");
        this.setBounds(300, 100, 800, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //Constructing labels
        headerLabel = new JLabel("MONTHLY SPENDING");
        headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        logoLabel = new JLabel(LOGO_IMAGE);
        
        //Constructing panels
        northPanel = new JPanel();
        northPanel.setBackground(FINAL_COLOR);
        northPanel.add(logoLabel);
        northPanel.add(headerLabel);
        southPanel = new JPanel();
        southPanel.setBackground(FINAL_COLOR);
        
        //Connect to database
        String[] columnName = {"OrderNumber", "Day", "Month", "Amount", "Reason", "Payer"};
        Database database = new Database("FinancialAssistant");
        Connection myCon = null;
        myCon = database.getConnection();
        
        //put data into an array
        ArrayList<ArrayList<String>> tempArray = database.getDataList(("Spending"),columnName);
        
        for(int k=0; k<tempArray.size(); k++)
        {
            tempArray.get(k);
        }
        
        Object[][] tempObject = database.to2dArray(tempArray);
        OverallSpending spendCal = new OverallSpending();
        tempArray = spendCal.sortMonth(tempArray, tempObject);
        Object[][] dataArray = database.to2dArray(tempArray);
//        this.spending = dataArray;
        
        //Table with buttons
        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(dataArray, TABLE_HEADER);
        
        //Constructing table
        JTable spendingTable = new JTable(dm);

        spendingTable.getColumn("Detail").setCellRenderer(new ButtonRenderer());
        spendingTable.getColumn("Detail").setCellEditor(new ButtonEditor(new JCheckBox(), spendingTable));
        JScrollPane scroll = new JScrollPane(spendingTable);
        getContentPane().add(scroll);
       
        //Adding components into frame
        this.add(northPanel, BorderLayout.NORTH);
//        this.add(historyPane, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        
        //Making frame visible
        this.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        MonthlySpending monthSpend = new MonthlySpending();
    }
    
    class ButtonRenderer extends JButton implements TableCellRenderer
    {
        public ButtonRenderer()
        {
            setOpaque(true);
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (isSelected)
            {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            }
            else
            {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
    
    class ButtonEditor extends DefaultCellEditor
    {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private JTable spendingTablel;

        public ButtonEditor(JCheckBox checkBox, JTable spendingTable)
        {
            super(checkBox);
            this.spendingTablel = spendingTable;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    fireEditingStopped();
                }
            });
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
        {
            if (isSelected)
            {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            }
            else
            {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }
        
        public Object getCellEditorValue()
        {
            if (isPushed)
            {
                //Get selected row
                int selection = this.spendingTablel.getSelectedRow();
                String monthSelected = (String) this.spendingTablel.getValueAt(selection, 0);
                
                //Change month into integer
                OverallSpending changeMonth = new OverallSpending();
                int monthNumber = changeMonth.monthNumber(monthSelected);
                MonthlyReport reportFrame = new MonthlyReport(monthNumber);
            }
        
        isPushed = false;
        return new String(label);
        }
        
        public boolean stopCellEditing()
        {
            isPushed = false;
            return super.stopCellEditing();
        }
        
        protected void fireEditingStopped()
        {
            super.fireEditingStopped();
        }
    }
}
