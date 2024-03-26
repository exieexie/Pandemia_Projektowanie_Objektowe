package Pandemia;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.Locale;
import java.util.ResourceBundle;

public class Pandemia extends JFrame implements ActionListener{

    JMenuItem newItem, saveItem, openItem, jezykItem, autorzyItem, polskiItem, angielskiItem;
    VirusPanel virusPanel;
    JPanel pandemiaPanel;
    JMenu SubMenu, Menu, RUN;
    JMenuBar menuBar;
    private int numberOfPeople; 
    private Locale currentLocale = new Locale("pl", "PL"); // Default to system locale (Polish in this case)
    
    
    public Pandemia() {
        setTitle("Symulacja pandemii");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        setLayout(new BorderLayout());

        pandemiaPanel = new JPanel();
        add(pandemiaPanel, BorderLayout.CENTER);

        
        ResourceBundle messages = ResourceBundle.getBundle("Messages", currentLocale);
        setTitle(messages.getString("SymulacjaPandemii"));

        
        menuBar = new JMenuBar();
        
        Menu = new JMenu("Menu");
        Menu.setText(messages.getString("Menu"));
        Menu.addActionListener(this);

        RUN = new JMenu("Start");
        RUN.setText(messages.getString("Run"));
        RUN.addActionListener(this);
        
        
        SubMenu = new JMenu("Jezyk");
        SubMenu.setText(messages.getString("Jezyk"));
        SubMenu.addActionListener(this);
        
        newItem = new JMenuItem("New");
        newItem.setText(messages.getString("Nowy"));
        newItem.addActionListener(this);
        
        saveItem = new JMenuItem("Save");
        saveItem.setText(messages.getString("Zapisz"));
        saveItem.addActionListener(this);
        
        openItem = new JMenuItem("Open");
        openItem.setText(messages.getString("Otworz"));
        openItem.addActionListener(this);
        
        jezykItem = new JMenuItem("Jezyk");
        jezykItem.setText(messages.getString("Jezyk"));
        jezykItem.addActionListener(this);
        
        polskiItem = new JMenuItem("Polski");
        polskiItem.setText(messages.getString("Polski"));
        polskiItem.addActionListener(this);
        
        angielskiItem = new JMenuItem("Angielski");
        angielskiItem.setText(messages.getString("Angielski"));
        angielskiItem.addActionListener(this);
        
        autorzyItem = new JMenuItem("Autorzy");
        autorzyItem.setText(messages.getString("Autorzy"));
        autorzyItem.addActionListener(this);

        Menu.add(newItem);
        Menu.add(saveItem);
        Menu.add(openItem);
        SubMenu.add(polskiItem);
        SubMenu.add(angielskiItem);
        Menu.add(SubMenu);
        Menu.addSeparator();
        Menu.add(autorzyItem);
        menuBar.add(Menu);
        menuBar.add(RUN);
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
    	  RUN.setText(messages.getString("Run"));
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
        else if(e.getSource() == RUN)
        {
        	//implementation of running the simulation - hide right panel OR make it into a real-time list
        }
        else if (e.getSource() == polskiItem) {
            currentLocale = new Locale("pl", "PL"); // Set Polish locale
            updateUILanguage();
        } 
        else if (e.getSource() == angielskiItem) {
            currentLocale = Locale.US; // Set English locale (US for consistency)
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