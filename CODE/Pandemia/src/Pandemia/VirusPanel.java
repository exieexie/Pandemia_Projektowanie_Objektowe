package Pandemia;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
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
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;


import javax.swing.*;

class VirusPanel extends JPanel implements ActionListener {

    private final Pandemia pandemia;
    public JButton pickVirusButton, colorVirusButton, colorHealthyButton, colorImmuneButton, colorDeadButton, parametersButton;
    public JLabel peopleLabel;
    private JLabel virusIdLabel;
    private Color virusColor; // Color for the virus
    public static Color healthyCellColor = Color.GREEN, immuneCellColor = Color.CYAN, deadCellColor = Color.BLACK; // Universal color
    private JTextField peopleTextField;
    private JPanel forList;
    public JButton listButton, runSimulationButton;
    public int numberOfPeople;
    
    
    public Locale currentLocale = new Locale("pl", "PL");
    
//    private List<Virus> viruses = new ArrayList<>();
    
   // private Color virusColor; // Color for the virus, for now i think in the future the virus class will have it
    
    private JButton colorVirusDisplayButton; // Button to display virus color
    private JButton colorHealthyDisplayButton; // Button to display healthy cell color
    private JButton colorImmuneDisplayButton; // Button to display immune cell color (assuming universal)
    private JButton colorDeadDisplayButton; // Button to display dead cell color (assuming universal)
    
    
    public static ArrayList<Virus> virusesList = new ArrayList<>();
    public static ArrayList<Person> peopleList = new ArrayList<>();
    private Virus selectedVirus;
    
    
    private class ParameterDialog extends JDialog 
    {
        private JTextField spreadabilityField, complexityField, lethalityField;
        private JLabel spreadabilityLabel, complexityLabel, lethalityLabel;
        private JButton okButton, cancelButton;
        private boolean okPressed;

        public ParameterDialog(JFrame parent) {
            super(parent, "Specify Parameters", true);
            setSize(300, 150);
            setLocationRelativeTo(parent);

            spreadabilityField = new JTextField(10);
            complexityField = new JTextField(10);
            lethalityField = new JTextField(10);

            okButton = new JButton("OK");
            cancelButton = new JButton("Cancel");

            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    okPressed = true;
                    dispose();
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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

        public boolean isOkPressed() {
            return okPressed;
        }

        public int getSpreadability() {
            try {
                return Integer.parseInt(spreadabilityField.getText());
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        public int getComplexity() {
            try {
                return Integer.parseInt(complexityField.getText());
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        public int getLethality() {
            try {
                return Integer.parseInt(lethalityField.getText());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }


    public VirusPanel(Pandemia pandemia) {
        this.pandemia = pandemia;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300, 600));
        //this.setSize(200,600);
        forList = new JPanel();
        forList.setLayout(new GridLayout(6, 2));
        
        pickVirusButton = new JButton("Wybierz wirusa");
        parametersButton = new JButton("Parametry");
        colorVirusButton = new JButton("Kolor wirusa: ");
        colorHealthyButton = new JButton("Kolor zdrowego: ");
        colorImmuneButton = new JButton("Kolor uodpornionego: ");
        colorDeadButton = new JButton("Kolor zmarłego: ");
        

        virusIdLabel = new JLabel("-"); // Initial value for virus ID

        colorVirusDisplayButton = new JButton(); // Button to display virus color
        
        colorHealthyDisplayButton = new JButton(); // Button to display healthy cell color
        colorHealthyDisplayButton.setBackground(healthyCellColor);
        
        colorImmuneDisplayButton = new JButton(); // Button to display immune cell color (assuming universal)
        colorImmuneDisplayButton.setBackground(immuneCellColor);
        
        colorDeadDisplayButton = new JButton(); // Button to display dead cell color (assuming universal)
        colorDeadDisplayButton.setBackground(deadCellColor);
        
        pickVirusButton.addActionListener(this);
        colorVirusButton.addActionListener(this);
        colorHealthyButton.addActionListener(this);
        colorImmuneButton.addActionListener(this);
        colorDeadButton.addActionListener(this);
        parametersButton.addActionListener(this);

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
        
        peopleLabel = new JLabel("Number of People:");
        peopleTextField = new JTextField();
        peopleTextField.addActionListener(this); 
        forList.add(peopleLabel);
        forList.add(peopleTextField);
        
        JPanel buttonPanel = new JPanel(); // Panel to hold the buttons
        buttonPanel.setLayout(new GridLayout(1, 2)); // Two buttons in a row

        listButton = new JButton("Generate a list of all viruses and their colors");
        listButton.addActionListener(this);
        buttonPanel.add(listButton); // Add the list button to the button panel

        runSimulationButton = new JButton("Run Simulation");
        runSimulationButton.addActionListener(this);
        buttonPanel.add(runSimulationButton); // Add the run simulation button to the button panel

        // Add the button panel to the bottom of VirusPanel
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.add(parametersButton, BorderLayout.NORTH);
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == pickVirusButton) 
        {
        	String virusId = JOptionPane.showInputDialog(this, "Enter Virus ID:");
        	Virus existingVirus = findVirusById(virusId);
            if (existingVirus != null) {
                JOptionPane.showMessageDialog(this, "Virus with ID " + virusId + " already exists. Please enter a different ID.");
            } else 
            {
                Virus newVirus = new Virus();
                newVirus.setID(convertToInt(virusId));
                virusesList.add(newVirus);
                selectedVirus = newVirus;
                virusIdLabel.setText(virusId);
            }
        	/*
        	 // Ask user to input virus ID
            String virusId = JOptionPane.showInputDialog(this, "Enter Virus ID:");
            // Create a new Virus instance
            Virus newVirus = new Virus();
            // Parse the ID and set it to the newVirus
            newVirus.setID(convertToInt(virusId));
            // Add the newVirus to the virusesList
            virusesList.add(newVirus);
            virusIdLabel.setText(virusId); // Set the text of virusIdLabel
            
            */
        }
        else if (e.getSource() == colorVirusButton) 
        {
            Color selectedColor = JColorChooser.showDialog(this, "Pick Virus Color", Color.RED); // Use existing virusColor if available
            if (selectedColor != null) 
            {
                virusColor = selectedColor;
                colorVirusDisplayButton.setBackground(selectedColor);
                updateVirusColor(selectedColor); // Update the color of the selected virus
            }
        } 
        else if (e.getSource() == colorHealthyButton) 
        {
            Color selectedColor = JColorChooser.showDialog(this, "Pick Healthy Cell Color", healthyCellColor); // Use existing background color
            if (selectedColor != null) 
            {
                healthyCellColor = selectedColor;
                colorHealthyDisplayButton.setBackground(selectedColor);
            }
        } 
        else if (e.getSource() == colorImmuneButton) 
        {
        	Color selectedColor = JColorChooser.showDialog(this, "Pick Immune Cell Color", immuneCellColor); // Use existing background color
            if (selectedColor != null) 
            {
                immuneCellColor = selectedColor;
                colorImmuneDisplayButton.setBackground(selectedColor);
            }
        } else if (e.getSource() == colorDeadButton) 
        {
        	Color selectedColor = JColorChooser.showDialog(this, "Pick Dead Cell Color", deadCellColor); // Use existing background color
            if (selectedColor != null) 
            {
                deadCellColor = selectedColor;
                colorDeadDisplayButton.setBackground(selectedColor);
            }
        } else if (e.getSource() == runSimulationButton || e.getSource() == peopleTextField) {
            // Get the number of people
            int numberOfPeople;
            try {
                numberOfPeople = Integer.parseInt(peopleTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format. Please enter a valid integer.");
                return; // Stop further execution if the number is invalid
            }

            // Remove the VirusPanel from the Pandemia frame
            pandemia.remove(this);

            // Create a new CFrame with the population size
            CFrame cFrame = new CFrame(numberOfPeople);

            // Add the CFrame to the Pandemia frame
            pandemia.add(cFrame);

            for (Virus virus : virusesList) {
            	virus.scheduleDoctorCreation();
            }
            
            // Refresh the Pandemia frame
            pandemia.revalidate();
            pandemia.repaint();
        }
        
        
        
        
/*        else if (e.getSource() == peopleTextField) 
        {
            try 
            {
                numberOfPeople = Integer.parseInt(peopleTextField.getText());
                pandemia.setNumberOfPeople(numberOfPeople); 
              } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format. Please enter a valid integer.");
              }
        }     */
        
        
        
        
        else if (e.getSource() == parametersButton) 
        {
        	 if (selectedVirus != null) 
        	 {
                 ParameterDialog dialog = new ParameterDialog((JFrame) SwingUtilities.getWindowAncestor(this));
                 dialog.setVisible(true);

                 if (dialog.isOkPressed()) 
                 {
                     int spreadability = dialog.getSpreadability();
                     int complexity = dialog.getComplexity();
                     int lethality = dialog.getLethality();

                     // Set parameters for the selected virus
                     selectedVirus.setSpreadability(spreadability);
                     selectedVirus.setComplexity(complexity);
                     selectedVirus.setLethality(lethality);

                     // For example, you can print the parameters
                     System.out.println("Spreadability: " + selectedVirus.spreadability + "of virus by ID:" + selectedVirus.ID);
                     System.out.println("Genetic Complexity: " + selectedVirus.complexity+ "of virus by ID:" + selectedVirus.ID);
                     System.out.println("Lethality: " + selectedVirus.lethality+ "of virus by ID:" + selectedVirus.ID);
                 }
             } 
        	 else 
        	 {
                 JOptionPane.showMessageDialog(this, "Please select a virus first.");
             }
        	
        	
        	/*
            ParameterDialog dialog = new ParameterDialog((JFrame) SwingUtilities.getWindowAncestor(this));
            ResourceBundle messages = ResourceBundle.getBundle("Messages", currentLocale);
            dialog.spreadabilityLabel.setText(messages.getString("spred") + ": ");
            dialog.complexityLabel.setText(messages.getString("complex") + ": ");
            dialog.lethalityLabel.setText(messages.getString("leth") + ": ");
            
            dialog.setVisible(true);

            if (dialog.isOkPressed()) {
                int spreadability = dialog.getSpreadability();
                int complexity = dialog.getComplexity();
                int lethality = dialog.getLethality();

                // Use the retrieved values as needed
                // For example:
                System.out.println("Spreadability: " + spreadability);
                System.out.println("Genetic Complexity: " + complexity);
                System.out.println("Lethality: " + lethality);
            }
            
            */
        }
        else if (e.getSource() == listButton) 
        {
            // Create a new JFrame for the list window
            JFrame listWindow = new JFrame("List of Viruses");
            listWindow.setSize(600, 400); // Adjust size as needed

            // Create a JPanel to hold the list elements
            JPanel listPanel = new JPanel();
            listPanel.setLayout(new GridLayout(0, 5)); // Dynamic number of rows based on viruses, 5 columns

            // Header labels for Virus ID, Spreadability, Complexity, Lethality, and Infected Color
            JLabel virusIdLabel = new JLabel("Virus ID");
            JLabel spreadabilityLabel = new JLabel("Spreadability");
            JLabel complexityLabel = new JLabel("Complexity");
            JLabel lethalityLabel = new JLabel("Lethality");
            JLabel infectedColorLabel = new JLabel("Infected Color");
            listPanel.add(virusIdLabel);
            listPanel.add(spreadabilityLabel);
            listPanel.add(complexityLabel);
            listPanel.add(lethalityLabel);
            listPanel.add(infectedColorLabel);

            // Iterate through viruses and add labels for each
            for (Virus virus : virusesList) {
                JLabel idLabel = new JLabel(Integer.toString(virus.getID()));
                JLabel spreadabilityValueLabel = new JLabel(Integer.toString((int)virus.getSpreadability()));
                JLabel complexityValueLabel = new JLabel(Integer.toString((int)virus.getComplexity()));
                JLabel lethalityValueLabel = new JLabel(Integer.toString((int)virus.getLethality()));
                JButton infectedColorButton = new JButton();
                infectedColorButton.setBackground(virus.infectedColor);
                infectedColorButton.setEnabled(false); // Prevent accidental color changes

                listPanel.add(idLabel);
                listPanel.add(spreadabilityValueLabel);
                listPanel.add(complexityValueLabel);
                listPanel.add(lethalityValueLabel);
                listPanel.add(infectedColorButton);
            }

            // Add the list panel to the list window
            listWindow.add(listPanel, BorderLayout.CENTER);

            // Make the window visible
            listWindow.setVisible(true);
        }
        
        
        
        
/*        else if (e.getSource() == runSimulationButton) 
        {
            // Get the number of people
            try 
            {
                numberOfPeople = Integer.parseInt(peopleTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format. Please enter a valid integer.");
                return; // Stop further execution if the number is invalid
            }
            
            // Initialize CFrame with the population size
            CFrame frame = new CFrame(numberOfPeople);

            // Remove all components from pandemiaPanel
            pandemia.pandemiaPanel.removeAll();

            // Add the CFrame to pandemiaPanel
            pandemia.pandemiaPanel.add(frame);

            // Validate and repaint pandemiaPanel
            pandemia.pandemiaPanel.revalidate();
            pandemia.pandemiaPanel.repaint();
        } */
        
        
        
        
        

        // Handle other actions in the future
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
    public static int convertToInt(String str) 
    {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // Handle the case when the string is not a valid integer
            System.err.println("Error: Input string is not a valid integer.");
            return 0; // or throw an exception, return a default value, etc.
        }
    }
    public static Virus findVirusById(String id) 
    {
        for (Virus virus : virusesList) {
            if (Integer.toString(virus.getID()).equals(id)) {
                return virus;
            }
        }
        return null; // Not found
    }
    private void updateVirusColor(Color selectedColor) {
        if (selectedVirus != null) {
            selectedVirus.infectedColor = selectedColor;
            colorVirusDisplayButton.setBackground(selectedColor);
        }
    }
    
    public static ArrayList<Virus> getVirusesList() {
        return virusesList;
    }
    public static void addDoctorsForVirus(int virusId) {
        for (int i = 0; i < 10; i++) {
            peopleList.add(new Doctor(virusId));
        }
    }
    
    }
