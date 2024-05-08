
/*

moved to VirusPanel as a subclass
hopefully it will be a good idea and simplifies a lot of shit, like setting parameters

package Pandemia;

import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;

class ParameterDialog extends JDialog 
{
    private JTextField spreadabilityField, complexityField, lethalityField;
    public JLabel spreadabilityLabel, complexityLabel, lethalityLabel;
    private JButton okButton, cancelButton;
    private boolean okPressed;

    public ParameterDialog(JFrame parent) 
    {
        super(parent, "Specify Parameters", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        
        spreadabilityField = new JTextField(10);
        complexityField = new JTextField(10);
        lethalityField = new JTextField(10);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        okButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                okPressed = true;
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                okPressed = false;
                dispose();
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 2));
        
        spreadabilityLabel = new JLabel("Spreadability:");
        complexityLabel = new JLabel("Genetic Complexity:");
        lethalityLabel = new JLabel("Lethality:");
        
        panel.add(spreadabilityLabel);
        panel.add(spreadabilityField);
        
        panel.add(complexityLabel);
        panel.add(complexityField);
        
        panel.add(lethalityLabel);
        panel.add(lethalityField);
        
        panel.add(okButton);
        panel.add(cancelButton);

        add(panel);
    }
    
    public boolean isOkPressed() 
    {
        return okPressed;
    }

    public int getSpreadability() 
    {
    	try 
    	{
    	    return Integer.parseInt(spreadabilityField.getText());
    	} 
    	catch (NumberFormatException e) 
    	{
    	    // Handle the exception if the user enters an empty string
    	    return 0; // Or any default value you want to use
    	}
    }

    public int getComplexity() 
    {
    	// Similar logic as getSpreadability()
    	try 
    	{
    	    return Integer.parseInt(complexityField.getText());
    	} 
    	catch (NumberFormatException e) 
    	{
    	    return 0;
    	}
    }

    public int getLethality() 
    {
    	  // Similar logic as getSpreadability()
    	try 
    	{
    	    return Integer.parseInt(lethalityField.getText());
    	} catch (NumberFormatException e) 
    	{
    	    return 0;
    	}
    }
}


*/