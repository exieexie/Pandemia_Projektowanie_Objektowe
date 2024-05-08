package Pandemia;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.*;

import java.util.Locale;
import java.util.ResourceBundle;

public class Pandemia extends JFrame implements ActionListener{

    JMenuItem newItem, saveItem, openItem, jezykItem, autorzyItem, polskiItem, angielskiItem, runItem;
    VirusPanel virusPanel;
    JPanel pandemiaPanel;
    JMenu SubMenu, Menu;
    JMenuBar menuBar;
    private int numberOfPeople; 
    private Locale currentLocale = new Locale("pl", "PL");
    
    ArrayList<Virus> viruses = new ArrayList<Virus>();
    
    private CFrame simulationPanel;
    
    
    public Pandemia() 
    {
        setTitle("Symulacja pandemii");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        setSize(1000, 600);

        setLayout(new BorderLayout());

        pandemiaPanel = new JPanel();
        add(pandemiaPanel, BorderLayout.CENTER);

        
        ResourceBundle messages = ResourceBundle.getBundle("Messages", currentLocale);
        setTitle(messages.getString("SymulacjaPandemii"));

        
        menuBar = new JMenuBar();
        
        Menu = new JMenu(messages.getString("Menu"));
        //Menu.setText(messages.getString("Menu"));
        Menu.addActionListener(this);

       // RUN = new JMenu((messages.getString("Run")));
        //RUN.setText(messages.getString("Run"));
       // RUN.addActionListener(this);
        

        
        
        SubMenu = new JMenu(messages.getString("Jezyk"));
        //SubMenu.setText(messages.getString("Jezyk"));
        SubMenu.addActionListener(this);
        
        newItem = new JMenuItem((messages.getString("Nowy")));
        //newItem.setText(messages.getString("Nowy"));
        newItem.addActionListener(this);
        
        saveItem = new JMenuItem(messages.getString("Zapisz"));
        //saveItem.setText(messages.getString("Zapisz"));
        saveItem.addActionListener(this);
        
        openItem = new JMenuItem(messages.getString("Otworz"));
        //openItem.setText(messages.getString("Otworz"));
        openItem.addActionListener(this);
        
        jezykItem = new JMenuItem(messages.getString("Jezyk"));
        //jezykItem.setText(messages.getString("Jezyk"));
        jezykItem.addActionListener(this);
        
        polskiItem = new JMenuItem(messages.getString("Polski"));
        //polskiItem.setText(messages.getString("Polski"));
        polskiItem.addActionListener(this);
        
        angielskiItem = new JMenuItem(messages.getString("Angielski"));
        //angielskiItem.setText(messages.getString("Angielski"));
        angielskiItem.addActionListener(this);
        
        autorzyItem = new JMenuItem(messages.getString("Autorzy"));
        //autorzyItem.setText(messages.getString("Autorzy"));
        autorzyItem.addActionListener(this);

        //runItem = new JMenuItem(messages.getString("Run")); // Changed this to JMenuItem
        //runItem.addActionListener(this);
        
        
        Menu.add(newItem);
        Menu.add(saveItem);
        Menu.add(openItem);
        SubMenu.add(polskiItem);
        SubMenu.add(angielskiItem);
        Menu.add(SubMenu);
        Menu.addSeparator();
        Menu.add(autorzyItem);
        menuBar.add(Menu);
        //menuBar.add(RUN);
        //Menu.add(runItem); // Added runItem to the menu bar
        setJMenuBar(menuBar);

        virusPanel = new VirusPanel(this);
        add(virusPanel, BorderLayout.EAST); // Virus panel added to EAST
        updateUILanguage();

        // Removed the original colour buttons from here
    }
    
    void updateUILanguage() {
    	  ResourceBundle messages = ResourceBundle.getBundle("Messages", currentLocale);
    	  setTitle(messages.getString("SymulacjaPandemii"));

    	  // Update menu items
    	  Menu.setText(messages.getString("Menu"));
    	  //RUN.setText(messages.getString("Run"));
    	  //runItem.setText(messages.getString("Run")); // Update runItem text
    	  SubMenu.setText(messages.getString("Jezyk"));
    	  jezykItem.setText(messages.getString("Jezyk"));
    	  newItem.setText(messages.getString("Nowy"));
    	  saveItem.setText(messages.getString("Zapisz"));
    	  openItem.setText(messages.getString("Otworz"));
    	  polskiItem.setText(messages.getString("Polski"));
    	  angielskiItem.setText(messages.getString("Angielski"));
    	  autorzyItem.setText(messages.getString("Autorzy"));

    	  // Update VirusPanel elements (assuming VirusPanel is a member variable)
    	  virusPanel.pickVirusButton.setText(messages.getString("WybierzWirusa"));
    	  virusPanel.colorVirusButton.setText(messages.getString("KolorWirusa") + ": ");
    	  virusPanel.colorHealthyButton.setText(messages.getString("KolorZdrowego") + ": ");
    	  virusPanel.colorImmuneButton.setText(messages.getString("KolorUodpornionego") + ": ");
    	  virusPanel.colorDeadButton.setText(messages.getString("KolorZmarlego") + ": ");
    	  virusPanel.listButton.setText(messages.getString("Lista") + ": ");
    	  virusPanel.peopleLabel.setText(messages.getString("NumberLabel") + ": ");
    	  virusPanel.parametersButton.setText(messages.getString("Parametry") + ": ");
    	  
    	}

    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == autorzyItem) {
            JOptionPane.showMessageDialog(this, "Autorzy:\nMaciej Chaciński \nMichał Kostera", "Autorzy", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getActionCommand().equals("New")) {
            // ... new file handling ...
        } else if (e.getActionCommand().equals("Save")) {
            // ... save file handling ...
        } else if (e.getActionCommand().equals("Open")) {
            // ... open file handling ...
        }
/*        else if (e.getSource() == runItem) { // Check if the source is runItem
            // Get the number of people from the virusPanel
            int numberOfPeople = virusPanel.numberOfPeople;
            
            // Initialize CFrame with the population size
            CFrame frame = new CFrame(numberOfPeople);
            
            // Remove all components from pandemiaPanel
            pandemiaPanel.removeAll();
            
            // Add the CFrame to pandemiaPanel
            pandemiaPanel.add(frame);
            
            // Validate and repaint pandemiaPanel
            pandemiaPanel.revalidate();
            pandemiaPanel.repaint();
        } */
        else if (e.getSource() == polskiItem) {
            currentLocale = new Locale("pl", "PL"); // Set Polish locale
            virusPanel.currentLocale = new Locale("pl", "PL");
            updateUILanguage();
        } 
        else if (e.getSource() == angielskiItem) {
            currentLocale = Locale.US; // Set English locale (US for consistency)
            virusPanel.currentLocale =  Locale.US;
            updateUILanguage();
        } 
        else if (e.getSource() instanceof JComponent && ((JComponent) e.getSource()).getParent() == virusPanel) {
            // Delegate action handling to virusPanel if it's the source
            virusPanel.actionPerformed(e);
        }
    }
    public void setNumberOfPeople(int numberOfPeople) 
    {
        this.numberOfPeople = numberOfPeople;
      }
    public int getNumberOfPeople() 
    {
        return numberOfPeople;
    }
    
    
    
    
	public static void main(String[] args) {
    	Pandemia pandemia = new Pandemia();
        pandemia.setVisible(true);
	}
	

}