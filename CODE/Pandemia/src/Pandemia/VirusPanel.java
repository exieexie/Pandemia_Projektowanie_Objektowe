package Pandemia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class VirusPanel extends JPanel implements ActionListener {

    private final Pandemia pandemia;
    private JButton pickVirusButton, colorVirusButton, colorHealthyButton, colorImmuneButton, colorDeadButton;
    private JLabel virusIdLabel;
    private Color virusColor; // Color for the virus
    private Color healthyCellColor, immuneCellColor, deadCellColor; // Universal color for healthy cells
    
    
   // private Color virusColor; // Color for the virus, for now i think in the future the virus class will have it
    
    private JButton colorVirusDisplayButton; // Button to display virus color
    private JButton colorHealthyDisplayButton; // Button to display healthy cell color
    private JButton colorImmuneDisplayButton; // Button to display immune cell color (assuming universal)
    private JButton colorDeadDisplayButton; // Button to display dead cell color (assuming universal)

    public VirusPanel(Pandemia pandemia) {
        this.pandemia = pandemia;
        setLayout(new GridLayout(5, 2));

        pickVirusButton = new JButton("PICK THE VIRUS");
        colorVirusButton = new JButton("COLOUR OF VIRUS");
        colorHealthyButton = new JButton("COLOUR OF HEALTHY");
        colorImmuneButton = new JButton("COLOUR OF IMMUNE");
        colorDeadButton = new JButton("COLOUR OF DEAD");

        virusIdLabel = new JLabel("-"); // Initial value for virus ID

        colorVirusDisplayButton = new JButton(); // Button to display virus color
        colorHealthyDisplayButton = new JButton(); // Button to display healthy cell color
        colorImmuneDisplayButton = new JButton(); // Button to display immune cell color (assuming universal)
        colorDeadDisplayButton = new JButton(); // Button to display dead cell color (assuming universal)

        pickVirusButton.addActionListener(this);
        colorVirusButton.addActionListener(this);
        colorHealthyButton.addActionListener(this);
        colorImmuneButton.addActionListener(this);
        colorDeadButton.addActionListener(this);

        add(pickVirusButton);
        add(virusIdLabel);
        add(colorVirusButton);
        add(colorVirusDisplayButton);
        add(colorHealthyButton);
        add(colorHealthyDisplayButton);
        add(colorImmuneButton);
        add(colorImmuneDisplayButton);
        add(colorDeadButton);
        add(colorDeadDisplayButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == pickVirusButton) 
        {
            // Implement logic to ask for virus ID (e.g., using JOptionPane)
            String virusId = JOptionPane.showInputDialog(this, "Enter Virus ID:");
            virusIdLabel.setText(virusId); // Replace with actual logic
        }
        else if (e.getSource() == colorVirusButton) 
        {
            Color selectedColor = JColorChooser.showDialog(this, "Pick Virus Color", virusColor); // Use existing virusColor if available
            if (selectedColor != null) 
            {
                virusColor = selectedColor;
                colorVirusDisplayButton.setBackground(selectedColor);
            }
        } 
        else if (e.getSource() == colorHealthyButton) 
        {
            Color selectedColor = JColorChooser.showDialog(this, "Pick Healthy Cell Color", getBackground()); // Use existing background color
            if (selectedColor != null) 
            {
                healthyCellColor = selectedColor;
                colorHealthyDisplayButton.setBackground(selectedColor);
            }
        } 
        else if (e.getSource() == colorImmuneButton) 
        {
        	Color selectedColor = JColorChooser.showDialog(this, "Pick Immune Cell Color", getBackground()); // Use existing background color
            if (selectedColor != null) 
            {
                immuneCellColor = selectedColor;
                colorImmuneDisplayButton.setBackground(selectedColor);
            }
        } else if (e.getSource() == colorDeadButton) 
        {
        	Color selectedColor = JColorChooser.showDialog(this, "Pick Dead Cell Color", getBackground()); // Use existing background color
            if (selectedColor != null) 
            {
                deadCellColor = selectedColor;
                colorDeadDisplayButton.setBackground(selectedColor);
            }
        }
    }
}