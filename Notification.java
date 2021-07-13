/* November 10, 2019
 * This frame notifies user whenever they put anything wrong
 */
package financialassist;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class Notification extends JFrame
{
    //declare labels
    private JLabel errorLabel;
    
    public Notification(String error)
    {
        //Constructing frame
        super("Notification");
        this.setBounds(400, 300, 700, 200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);
        
        //Getting string and put into label
        errorLabel = new JLabel(error, SwingConstants.CENTER);
        //Formatting the labels
        errorLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        //add components into frame and make it visible
        this.add(errorLabel);
        this.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        //passing a string to test 
        Notification errorFrame = new Notification("You have put something wrong");
    }
}
