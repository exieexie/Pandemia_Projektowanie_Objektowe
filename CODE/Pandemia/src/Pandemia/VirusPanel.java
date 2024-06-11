package Pandemia;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.swing.filechooser.FileNameExtensionFilter;
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
    public static int numberOfPeople;
    public JTextField spreadabilityField, complexityField, lethalityField;
    boolean a;
    
    public Locale currentLocale = new Locale("pl", "PL");
    ResourceBundle messages = ResourceBundle.getBundle("Messages", currentLocale);

    private JButton colorVirusDisplayButton; 
    private JButton colorHealthyDisplayButton; 
    private JButton colorImmuneDisplayButton; 
    private JButton colorDeadDisplayButton; 
    private JButton Save;
    private JButton Open;
    private JButton New;
    private JButton stopSimulationButton;
    private JLabel spreadabilityLabel, complexityLabel, lethalityLabel;
    
    public static ArrayList<Virus> virusesList = new ArrayList<>();
    public static ArrayList<Person> peopleList = new ArrayList<>();
    private Virus selectedVirus;
    CFrame cFrame;
    //private CFrame simulationPanel;
    
    private class ParameterDialog extends JDialog {
        private JButton okButton, cancelButton;
        private boolean okPressed;
        public ParameterDialog(JFrame parent) {
            super(parent, true); // Make dialog modal
            setSize(300, 150);
            setLocationRelativeTo(parent);

            setTitle(currentLocale.equals(new Locale("pl", "PL")) ? "Specyfikacja" : "Specification");

            spreadabilityLabel = new JLabel(messages.getString("spred"));
            complexityLabel = new JLabel(messages.getString("complex"));
            lethalityLabel = new JLabel(messages.getString("leth"));



            // Set the current virus's values into the fields if a virus is selected
            if (selectedVirus != null) {
                spreadabilityField.setText(Double.toString(selectedVirus.getSpreadability()));
                complexityField.setText(Double.toString(selectedVirus.getComplexity()));
                lethalityField.setText(Double.toString(selectedVirus.getLethality()));
            }

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

        public double getSpreadability() {
            try {
                return Double.parseDouble(spreadabilityField.getText());
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        public double getComplexity() {
            try {
                return Double.parseDouble(complexityField.getText());
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        public double getLethality() {
            try {
                return Double.parseDouble(lethalityField.getText());
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
        forList.setLayout(new GridLayout(8, 2));
        

        
        pickVirusButton = new JButton("Wybierz wirusa");
        parametersButton = new JButton("Parametry");
        colorVirusButton = new JButton("Kolor wirusa: ");
        colorHealthyButton = new JButton("Kolor zdrowego: ");
        colorImmuneButton = new JButton("Kolor uodpornionego: ");
        colorDeadButton = new JButton("Kolor zmarłego: ");
        Save = new JButton("Zapisz");
        Open = new JButton("Wczytaj");
        New = new JButton("Wyczyść");
        stopSimulationButton = new JButton("Zatrzymaj symualcje");

        
        
        spreadabilityLabel = new JLabel("Spreadability:");
        complexityLabel = new JLabel("Genetic Complexity:");
        lethalityLabel = new JLabel("Lethality:");
        
        spreadabilityField = new JTextField(10);
        complexityField = new JTextField(10);
        lethalityField = new JTextField(10);

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
        Save.addActionListener(this);
        Open.addActionListener(this);
        New.addActionListener(this);

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
        forList.add(Save);
        forList.add(Open);
        forList.add(New);
        forList.add(stopSimulationButton);
        
        stopSimulationButton.addActionListener(this);
        
        JPanel buttonPanel = new JPanel(); // Panel to hold the buttons
        buttonPanel.setLayout(new GridLayout(1, 2)); // Two buttons in a row

        listButton = new JButton("Viruses list");
        listButton.addActionListener(this);
        buttonPanel.add(listButton); // Add the list button to the button panel

        runSimulationButton = new JButton("Run Simulation");
        runSimulationButton.addActionListener(this);
        buttonPanel.add(runSimulationButton); // Add the run simulation button to the button panel

        // Add the button panel to the bottom of VirusPanel
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.add(parametersButton, BorderLayout.NORTH);
    }
    
    void updateUILanguage(ResourceBundle messages) {
    	Save.setText(messages.getString("Zapisz"));
        Open.setText(messages.getString("Otworz"));
        peopleLabel.setText(messages.getString("NumberLabel"));
        listButton.setText(messages.getString("Lista"));
        runSimulationButton.setText(messages.getString("Run"));
        spreadabilityLabel.setText(messages.getString("spred"));
        complexityLabel.setText(messages.getString("complex"));
        lethalityLabel.setText(messages.getString("leth"));
        New.setText(messages.getString("wyczysc"));
        colorVirusButton.setText(messages.getString("KolorWirusa"));
        colorHealthyButton.setText(messages.getString("KolorZdrowego"));
        colorImmuneButton.setText(messages.getString("KolorUodpornionego"));
        colorDeadButton.setText(messages.getString("KolorZmarlego"));
        parametersButton.setText(messages.getString("Parametry"));
        pickVirusButton.setText(messages.getString("WybierzWirusa"));
        stopSimulationButton.setText(messages.getString("zatrzymajSymulacje"));
    }

    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == pickVirusButton) 
        {
        	String virusId; 
        	if(currentLocale.equals(new Locale("pl", "PL"))) {
        		virusId = JOptionPane.showInputDialog(this, "Wpisz ID wirusa");
   		 	}
        	else {
        		virusId = JOptionPane.showInputDialog(this, "Enter the virus ID");
        	}
        	Virus existingVirus = findVirusById(virusId);
            if (existingVirus != null) {
            	if(currentLocale.equals(new Locale("pl", "PL"))) {
            		JOptionPane.showMessageDialog(this, "Wirus o tym identyfikatorze już istnieje. Proszę podaj inny identyfikator");
       		 	}
            	else {
            		JOptionPane.showMessageDialog(this, "Virus with this ID already exists. Please enter a different ID.");
       		 	}
            } else 
            {
                Virus newVirus = new Virus();
                newVirus.setID(convertToInt(virusId));
                virusesList.add(newVirus);
                selectedVirus = newVirus;
                virusIdLabel.setText(virusId);
            }
        	
        }
        else if (e.getSource() == colorVirusButton) 
        {
        	Color selectedColor;
        	if (currentLocale.equals(new Locale("pl", "PL"))) {
        		selectedColor = JColorChooser.showDialog(this, "Kolor wirusa", Color.RED); // Use existing virusColor if available
            } else {
            	selectedColor = JColorChooser.showDialog(this, "Virus Color", Color.RED); // Use existing virusColor if available
            }
            if (selectedColor != null) 
            {
                virusColor = selectedColor;
                colorVirusDisplayButton.setBackground(selectedColor);
                updateVirusColor(selectedColor); // Update the color of the selected virus
            }
        } 
        else if (e.getSource() == colorHealthyButton) 
        {
        	Color selectedColor;
        	if (currentLocale.equals(new Locale("pl", "PL"))) {
        		selectedColor = JColorChooser.showDialog(this, "Kolor zdrowego", Color.GREEN); // Use existing virusColor if available
            } else {
            	selectedColor = JColorChooser.showDialog(this, "Healthy Color", Color.GREEN); // Use existing virusColor if available
            }
        	if (selectedColor != null) 
            {
                healthyCellColor = selectedColor;
                colorHealthyDisplayButton.setBackground(selectedColor);
            }
        } 
        else if (e.getSource() == colorImmuneButton) 
        {
        	Color selectedColor;
        	if (currentLocale.equals(new Locale("pl", "PL"))) {
        		selectedColor = JColorChooser.showDialog(this, "Kolor uodpornionego", Color.YELLOW); // Use existing virusColor if available
            } else {
            	selectedColor = JColorChooser.showDialog(this, "Immune Color", Color.YELLOW); // Use existing virusColor if available
            }
        	if (selectedColor != null) 
            {
                immuneCellColor = selectedColor;
                colorImmuneDisplayButton.setBackground(selectedColor);
            }
        } else if (e.getSource() == colorDeadButton) 
        {
        	Color selectedColor;
        	if (currentLocale.equals(new Locale("pl", "PL"))) {
        		selectedColor = JColorChooser.showDialog(this, "Kolor zmarłego", Color.BLACK); // Use existing virusColor if available
            } else {
            	selectedColor = JColorChooser.showDialog(this, "Dead Color", Color.BLACK); // Use existing virusColor if available
            }
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
            	if (currentLocale.equals(new Locale("pl", "PL"))) {
            		JOptionPane.showMessageDialog(this, "Wpisz ilość osób");
                } else {
                	JOptionPane.showMessageDialog(this, "Enter the number of people");
                }
                
                return; // Stop further execution if the number is invalid
            }

            // Remove the VirusPanel from the Pandemia frame
            //pandemia.remove(this);

            // Create a new CFrame with the population size
            

            for (Virus virus : virusesList) {
            	virus.scheduleDoctorCreation();
            }
            cFrame = new CFrame(numberOfPeople);

            // Add the CFrame to the Pandemia frame
            pandemia.add(cFrame);
            // Refresh the Pandemia frame
            pandemia.revalidate();
            pandemia.repaint();
        } else if (e.getSource() == stopSimulationButton) {
        	
            cFrame.t.stop();
            System.out.println("STOP");
        }
        else if(e.getSource() == Save){
        	for (Virus virus : virusesList) {

                zapiszDoPliku();  
            }
        } else if(e.getSource() == Open){
        	odczytajZPliku();       
        	
        }else if (e.getSource() == parametersButton) {
            if (selectedVirus != null) {
                ParameterDialog dialog = new ParameterDialog((JFrame) SwingUtilities.getWindowAncestor(this));
                dialog.setVisible(true);

                if (dialog.isOkPressed()) {
                    double spreadability = dialog.getSpreadability();
                    double complexity = dialog.getComplexity();
                    double lethality = dialog.getLethality();

                    // Ustaw parametry dla wybranego wirusa
                    selectedVirus.setSpreadability(spreadability);
                    selectedVirus.setComplexity(complexity);
                    selectedVirus.setLethality(lethality);

                    // Debug output
                    System.out.println("Updated Virus:");
                    System.out.println("ID: " + selectedVirus.getID() + ", Spreadability: " + selectedVirus.getSpreadability() + ", Complexity: " + selectedVirus.getComplexity() + ", Lethality: " + selectedVirus.getLethality());
                }
            } else {
                if (currentLocale.equals(new Locale("pl", "PL"))) {
                    JOptionPane.showMessageDialog(this, "Wybierz najpierw wirusa.");
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a virus first.");
                }
            }
        }
        else if(e.getSource() == New) {
        	clearBoard();
        }
        else if (e.getSource() == listButton) {
        	JFrame listWindow = new JFrame("List of Viruses");
        	if(currentLocale.equals(new Locale("pl", "PL"))) {
        		listWindow = new JFrame("Lista wirusów");
        	}else {
        		listWindow = new JFrame("List of Viruses");
   		 	}
            
            listWindow.setSize(600, 400); // Adjust size as needed

            // Create a JPanel to hold the list elements
            JPanel listPanel = new JPanel();
            listPanel.setLayout(new GridLayout(0, 5)); // Dynamic number of rows based on viruses, 5 columns

            // Header labels for Virus ID, Spreadability, Complexity, Lethality, and Infected Color
            JLabel virusIdLabel;
            if(currentLocale.equals(new Locale("pl", "PL"))) {
            	virusIdLabel = new JLabel("Wirus ID");
        	}else {
        		virusIdLabel = new JLabel("Virus ID");
   		 	}
            JLabel spreadabilityLabel;
            if(currentLocale.equals(new Locale("pl", "PL"))) {
            	spreadabilityLabel = new JLabel("Rozprzestrzenialność");
        	}else {
        		spreadabilityLabel = new JLabel("Spreadability");
   		 	}
            JLabel complexityLabel;
            if(currentLocale.equals(new Locale("pl", "PL"))) {
            	complexityLabel = new JLabel("Złożoność");
        	}else {
        		complexityLabel = new JLabel("Complexity");
   		 	}
            JLabel lethalityLabel;
            if(currentLocale.equals(new Locale("pl", "PL"))) {
            	lethalityLabel = new JLabel("Śmiertelność");
        	}else {
        		lethalityLabel = new JLabel("Lethality");
   		 	}
            JLabel infectedColorLabel;
            if(currentLocale.equals(new Locale("pl", "PL"))) {
            	infectedColorLabel = new JLabel("Kolor zainfekowania");
        	}else {
        		infectedColorLabel = new JLabel("Infected Color");
   		 	}
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

    }
       
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
    private void zapiszDoPliku() {
        try {
            JFileChooser fileChooser = new JFileChooser();
        	if(currentLocale.equals(new Locale("pl", "PL"))) {
                fileChooser.setFileFilter(new FileNameExtensionFilter("Pliki tekstowe (*.txt)", "txt"));
            }
            else {
            	fileChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
            }
            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                if (!fileToSave.getName().endsWith(".txt")) {
                	if (currentLocale.equals(new Locale("pl", "PL"))) {
                		 JOptionPane.showMessageDialog(this, "Nieprawidłowe rozszerzenie pliku. Plik zostanie zapisane jako plik tekstowy (.txt)", "Błąd", JOptionPane.ERROR_MESSAGE);
                    } else {
                    	 JOptionPane.showMessageDialog(this, "Invalid file extension. The file will be saved as a text file (.txt)", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                   
                    fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".txt");
                }
                PrintWriter writer = new PrintWriter(new FileWriter(fileToSave, true));
                for (Virus virus : virusesList) {
                    writer.println(virus.getID());
                    writer.println(virus.getSpreadability());
                    writer.println(virus.getComplexity());
                    writer.println(virus.getLethality());
                }
                writer.close();
            }
        } catch (IOException ex) {
            if(currentLocale.equals(new Locale("pl", "PL"))) {
                JOptionPane.showMessageDialog(this, "Wystąpił błąd zapisu do pliku.", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(this, "An error occurred while writing to the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void odczytajZPliku() {
        try {
        	JFileChooser fileChooser = new JFileChooser();
        	
        	if(currentLocale.equals(new Locale("pl", "PL"))) {
                fileChooser.setFileFilter(new FileNameExtensionFilter("Pliki tekstowe (*.txt)", "txt"));
            }
            else {
            	fileChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
            }
        	
            int result = fileChooser.showOpenDialog(this);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                File fileToRead = fileChooser.getSelectedFile();
                if (!fileToRead.getName().endsWith(".txt")) {
                	if (currentLocale.equals(new Locale("pl", "PL"))) {
                		JOptionPane.showMessageDialog(this, "Nieprawidłowe rozszerzenie pliku. Wybierz plik tekstowy (.txt)", "Błąd", JOptionPane.ERROR_MESSAGE);
                    } else {
                   	 JOptionPane.showMessageDialog(this, "Invalid file extension. Select a text file (.txt).", "Error", JOptionPane.ERROR_MESSAGE);
                   }
                    return;
                }
                BufferedReader reader = new BufferedReader(new FileReader(fileToRead));
	            String line;
	            int lineNumber = 0;
	
	            int id = 0;
	            double spreadability = 0.0;
	            double complexity = 0.0;
	            double lethality = 0.0;
	
	            while ((line = reader.readLine()) != null) {
	                switch (lineNumber % 4) {
	                    case 0:
	                        id = Integer.parseInt(line);
	                        break;
	                    case 1:
	                        spreadability = Double.parseDouble(line);
	                        break;
	                    case 2:
	                        complexity = Double.parseDouble(line); 
	                        break;
	                    case 3:
	                        lethality = Double.parseDouble(line);
	                        
	                        // Tworzenie nowego obiektu Virus
	                        Virus virus = new Virus();
	                        virus.setID(id);
	                        virus.setSpreadability(spreadability);
	                        virus.setComplexity(complexity);
	                        virus.setLethality(lethality);
	
	                        // Dodanie do listy
	                        virusesList.add(virus);
	
	                        // Aktualizacja GUI (opcjonalnie)
	                        if (virusIdLabel != null) {
	                            virusIdLabel.setText(Integer.toString(id));
	                        }
	                        if (spreadabilityField != null) {
	                            spreadabilityField.setText(Double.toString(spreadability));
	                        }
	                        if (complexityField != null) {
	                            complexityField.setText(Double.toString(complexity));
	                        }
	                        if (lethalityField != null) {
	                            lethalityField.setText(Double.toString(lethality));
	                        }
	                        
	                        // Resetowanie wartości
	                        id = 0;
	                        spreadability = 0.0;
	                        complexity = 0.0;
	                        lethality = 0.0;
	                        break;
	                }
	                lineNumber++;
	            }
	
	            reader.close();
	
	            // Wyświetlanie wczytanych wirusów (Debug)
	            System.out.println("Wczytane wirusy:");
	            for (Virus v : virusesList) {
	                System.out.println("ID: " + v.getID() + ", Spreadability: " + v.getSpreadability() +
	                                   ", Complexity: " + v.getComplexity() + ", Lethality: " + v.getLethality());
	            }
	            System.out.println("");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void clearBoard() {
        // Wyczyść pola tekstowe
        peopleTextField.setText("");
        spreadabilityField.setText("");
        complexityField.setText("");
        lethalityField.setText("");
        // Usuń wszystkie wirusy z listy
        virusesList.clear();
        // Zresetuj wyświetlane kolory
        colorVirusDisplayButton.setBackground(null);
        colorHealthyDisplayButton.setBackground(healthyCellColor);
        colorImmuneDisplayButton.setBackground(immuneCellColor);
        colorDeadDisplayButton.setBackground(deadCellColor);
        // Zresetuj etykietę ID wirusa
        virusIdLabel.setText("-");
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
