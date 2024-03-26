package Pandemia;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


import javax.swing.*;

class VirusPanel extends JPanel implements ActionListener {

    private final Pandemia pandemia;
    public JButton pickVirusButton, colorVirusButton, colorHealthyButton, colorImmuneButton, colorDeadButton;
    private JLabel virusIdLabel;
    private Color virusColor; // Color for the virus
    private Color healthyCellColor, immuneCellColor, deadCellColor; // Universal color for healthy cells
    
    private JPanel forList;
    public JButton listButton;
    
//    private List<Virus> viruses = new ArrayList<>();
    
   // private Color virusColor; // Color for the virus, for now i think in the future the virus class will have it
    
    private JButton colorVirusDisplayButton; // Button to display virus color
    private JButton colorHealthyDisplayButton; // Button to display healthy cell color
    private JButton colorImmuneDisplayButton; // Button to display immune cell color (assuming universal)
    private JButton colorDeadDisplayButton; // Button to display dead cell color (assuming universal)

    public VirusPanel(Pandemia pandemia) {
        this.pandemia = pandemia;
        
        this.setLayout(new BorderLayout());
        forList = new JPanel();
        forList.setLayout(new GridLayout(5, 2));
        
        pickVirusButton = new JButton("Wybierz wirusa");
        colorVirusButton = new JButton("Kolor wirusa: ");
        colorHealthyButton = new JButton("Kolor zdrowego: ");
        colorImmuneButton = new JButton("Kolor uodpornionego: ");
        colorDeadButton = new JButton("Kolor zmarłego: ");

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

        forList.add(pickVirusButton);
        forList.add(virusIdLabel);
        forList.add(colorVirusButton);
        forList.add(colorVirusDisplayButton);
        forList.add(colorHealthyButton);
        forList.add(colorHealthyDisplayButton);
        forList.add(colorImmuneButton);
        forList.add(colorImmuneDisplayButton);
        forList.add(colorDeadButton);
        forList.add(colorDeadDisplayButton);
        this.add(forList, BorderLayout.CENTER);
        
        listButton = new JButton("Generate a list of all viruses and their colors");
        listButton.addActionListener(this);
        this.add(listButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == pickVirusButton) 
        {
            // Implement logic to ask for virus ID (e.g., using JOptionPane)
            String virusId = JOptionPane.showInputDialog(this, "Enter Virus ID:");
            virusIdLabel.setText(virusId); // Replace with actual logic
            
//            viruses.add(new Virus(virusId, virusColor, healthyCellColor, immuneCellColor, deadCellColor));
//            virusIdLabel.setText(virusId); // Replace with actual logic
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
/*        else if (e.getSource() == listButton) 
        {
            // Create a new JFrame for the list window
            JFrame listWindow = new JFrame("List of Viruses");
            listWindow.setSize(400, 200); // Adjust size as needed

            // Create a JPanel to hold the list elements
            JPanel listPanel = new JPanel();
            listPanel.setLayout(new GridLayout(0, 2)); // Dynamic number of rows based on viruses

            // Header labels for Virus ID and Color
            JLabel virusIdLabel = new JLabel("Virus ID");
            JLabel colorLabel = new JLabel("Color");
            listPanel.add(virusIdLabel);
            listPanel.add(colorLabel);

            // Iterate through viruses and add labels for each
            for (Virus virus : viruses) {
              JLabel idLabel = new JLabel(virus.getId());
              JButton colorButton = new JButton();
              colorButton.setBackground(virus.getColor());
              colorButton.setEnabled(false); // Prevent accidental color changes

              listPanel.add(idLabel);
              listPanel.add(colorButton);
            }

            // Add the list panel to the list window
            listWindow.add(listPanel, BorderLayout.CENTER);

            // Make the window visible
            listWindow.setVisible(true);
          }
*/
    }
}