/* November 11, 2019
 * This frame shows who should pay who for how much*/
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


public class PayingSuggest extends JFrame
{
    //declare constants
    private final Color FINAL_COLOR = new Color(26, 46, 90);
    private final java.net.URL IMAGE_URL = getClass().getResource("logo.png");
    private final ImageIcon LOGO_IMAGE = new ImageIcon(new ImageIcon(IMAGE_URL).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
    
    //declaring labels
    private JLabel headerLabel;
    private JLabel[] suggestLabel;
    private JLabel logoLabel;
    
    //declaring JPanel
    private JPanel northPanel;
    private JPanel centerPanel;
    
    public PayingSuggest()
    {
        //Constructing frame
        super("Paying Suggest");
        this.setBounds(400, 200, 500, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //Constructing label
        headerLabel = new JLabel("PAYING SUGGEST");
        headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        logoLabel = new JLabel(LOGO_IMAGE);
        
        //Connect to database
        String[] columnName = {"OrderNumber", "Name", "Day", "Month", "Paid", "Spending", "Reason",};
        Database database = new Database("FinancialAssistant");
        Connection myCon = null;
        myCon = database.getConnection();
        
        ArrayList<ArrayList<String>> spending = database.getDataList(("People"),columnName);
        
        for(int k=0; k<spending.size(); k++)
        {
            spending.get(k);
        }
        
        ArrayList<String> suggestList = new ArrayList<String>();
        
        PersonalSpending getSuggest = new PersonalSpending();
        suggestList = getSuggest.payingSug(spending);
        
        //Constructing panel
        northPanel = new JPanel();
        northPanel.setBackground(FINAL_COLOR);
        northPanel.add(logoLabel);
        northPanel.add(headerLabel);
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        
        //putting suggestion in
        suggestLabel = new JLabel[suggestList.size()];
        
        for (int i = 0; i<suggestList.size(); i++)
        {
            suggestLabel[i] = new JLabel(suggestList.get(i));
            centerPanel.add(suggestLabel[i]);
        }
        
        //Adding components into frame
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        
        //Making frame visible
        this.setVisible(true);
        
    }
    
    public static void main(String[] args)
    {
        PayingSuggest paySugg = new PayingSuggest();
    }
}
