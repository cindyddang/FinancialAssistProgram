/* November 11, 2019
 * This frame display table of people's spending*/
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


public class PeopleRecord extends JFrame implements ActionListener
{
    //declare constants
    private final Color FINAL_COLOR = new Color(26, 46, 90);
    private final java.net.URL IMAGE_URL = getClass().getResource("logo.png");
    private final ImageIcon LOGO_IMAGE = new ImageIcon(new ImageIcon(IMAGE_URL).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
    
    //labels
    private JLabel headerLabel;
    private JLabel logoLabel;
    
    //Declare variable relating to table
    private final String[] TABLE_HEADER = {"Name", "Spending($)", "Detail"};
    private Object[][] spending;
    
    //JButtons
    
    //panels
    private JPanel northPanel;   
    
    public PeopleRecord()
    {
        //Constructing frame
        super("People Record");
        this.setBounds(300, 100, 800, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //Constructing labels
        headerLabel = new JLabel("PEOPLE'S SPENDING RECORD");
        headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        logoLabel = new JLabel(LOGO_IMAGE);
        
        //Constructing panels
        northPanel = new JPanel();
        northPanel.setBackground(FINAL_COLOR);
        northPanel.add(logoLabel);
        northPanel.add(headerLabel);
        
        //Connect to database
        String[] columnName = {"Name", "Day", "Month", "Paid", "Spending", "Reason"};
        Database database = new Database("FinancialAssistant");
        Connection myCon = null;
        myCon = database.getConnection();
        
        //put data into an array
        ArrayList<ArrayList<String>> tempArray = database.getDataList(("People"),columnName);
        
        PersonalSpending personCal = new PersonalSpending();
        tempArray = personCal.sortName(tempArray);
        Object[][] tempObj = database.to2dArray(tempArray);
        this.spending = tempObj;
        
        //Constructing table
        DefaultTableModel peopleModel = new DefaultTableModel();
        peopleModel.setDataVector(tempObj, TABLE_HEADER);
        
        JTable peopleTable = new JTable(peopleModel);
        
        peopleTable.getColumn("Detail").setCellRenderer(new ButtonRenderer());
        peopleTable.getColumn("Detail").setCellEditor(new ButtonEditor(new JCheckBox(), peopleTable));
        JScrollPane scrollPane = new JScrollPane(peopleTable);
        getContentPane().add(scrollPane);
        
        //Adding components into frame
        this.add(northPanel, BorderLayout.NORTH);
//        this.add(historyPane, BorderLayout.CENTER);
        
        //Making frame visible
        this.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        PeopleRecord peoRec = new PeopleRecord();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if (command.equals("Detail"))
        {
            //Passing a string to test
            //Will be replaces by name when database is accessed
            PersonReport perRec = new PersonReport("My Name");
        }
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
        private JTable peopleTable;

        public ButtonEditor(JCheckBox checkBox, JTable peopleTable)
        {
            super(checkBox);
            this.peopleTable = peopleTable;
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
                int selection = this.peopleTable.getSelectedRow();
                String personSelected = (String) this.peopleTable.getValueAt(selection, 0);
                
                PersonReport reportFrame = new PersonReport(personSelected);
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
