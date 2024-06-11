package Pandemia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Pandemia extends JFrame implements ActionListener {

    JMenuItem jezykItem, autorzyItem, polskiItem, angielskiItem, runItem, changeBackgroundColorItem;
    VirusPanel virusPanel;
    JPanel pandemiaPanel;
    JMenu SubMenu, Menu;
    JMenuBar menuBar;
    
    
    private CFrame simulationPanel;
    
    
    private int numberOfPeople;
    private Locale currentLocale = new Locale("pl", "PL");

    // Stałe tłumaczeń
    private static final String TITLE_KEY = "SymulacjaPandemii";
    private static final String MENU_KEY = "Menu";
    private static final String LANGUAGE_KEY = "Jezyk";
    private static final String POLISH_KEY = "Polski";
    private static final String ENGLISH_KEY = "Angielski";
    private static final String AUTHORS_KEY = "Autorzy";
    private static final String VIRUS_MENU_KEY = "WybierzWirusa";
    private static final String VIRUS_COLOR_KEY = "KolorWirusa";
    private static final String HEALTHY_COLOR_KEY = "KolorZdrowego";
    private static final String IMMUNE_COLOR_KEY = "KolorUodpornionego";
    private static final String DEAD_COLOR_KEY = "KolorZmarlego";
    private static final String SAVE_KEY = "Zapisz";
    private static final String OPEN_KEY = "Otworz";
    private static final String LIST_KEY = "Lista";
    private static final String RUN_KEY = "Run";
    private static final String NUMBER_LABEL_KEY = "NumberLabel";
    private static final String PARAMETERS_KEY = "Parametry";
    private static final String SPREADABILITY_KEY = "spred";
    private static final String COMPLEXITY_KEY = "complex";
    private static final String LETHALITY_KEY = "leth";
    private static final String CHANGE_BACKGROUND_COLOR_KEY = "ZmienKolorTla";
    private static final String STOP = "zatrzymajSymulacje";



    public Pandemia() {
        setTitle("Symulacja pandemii");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        setSize(1000, 600);

        setLayout(new BorderLayout());

        pandemiaPanel = new JPanel();
        add(pandemiaPanel, BorderLayout.CENTER);


        ResourceBundle messages = ResourceBundle.getBundle("Messages", currentLocale);
        setTitle(messages.getString(TITLE_KEY));

        virusPanel = new VirusPanel(this);
        add(virusPanel, BorderLayout.EAST);

        simulationPanel = new CFrame(numberOfPeople);
        //pandemiaPanel.add(simulationPanel, BorderLayout.CENTER);
        menuBar = new JMenuBar();

        Menu = new JMenu(messages.getString(MENU_KEY));
        Menu.addActionListener(this);

        SubMenu = new JMenu(messages.getString(LANGUAGE_KEY));
        SubMenu.addActionListener(this);

        jezykItem = new JMenuItem(messages.getString(LANGUAGE_KEY));
        jezykItem.addActionListener(this);

        polskiItem = new JMenuItem(messages.getString(POLISH_KEY));
        polskiItem.addActionListener(this);

        angielskiItem = new JMenuItem(messages.getString(ENGLISH_KEY));
        angielskiItem.addActionListener(this);

        changeBackgroundColorItem = new JMenuItem(messages.getString(CHANGE_BACKGROUND_COLOR_KEY));
        changeBackgroundColorItem.addActionListener(this);
        
        autorzyItem = new JMenuItem(messages.getString(AUTHORS_KEY));
        autorzyItem.addActionListener(this);

        
       
        SubMenu.add(polskiItem);
        SubMenu.add(angielskiItem);
        Menu.add(SubMenu);
        //Menu.add(changeBackgroundColorItem);
        Menu.addSeparator();
        Menu.add(autorzyItem);
        menuBar.add(Menu);
        setJMenuBar(menuBar);

        updateUILanguage();
    }

    
    void updateUILanguage() {
        ResourceBundle messages = ResourceBundle.getBundle("Messages", currentLocale);

        setTitle(messages.getString(TITLE_KEY));
        Menu.setText(messages.getString(MENU_KEY));
        SubMenu.setText(messages.getString(LANGUAGE_KEY));
        jezykItem.setText(messages.getString(LANGUAGE_KEY));
        polskiItem.setText(messages.getString(POLISH_KEY));
        angielskiItem.setText(messages.getString(ENGLISH_KEY));
        autorzyItem.setText(messages.getString(AUTHORS_KEY));
        changeBackgroundColorItem.setText(messages.getString(CHANGE_BACKGROUND_COLOR_KEY));
        virusPanel.updateUILanguage(messages);
    }

    
    	
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == autorzyItem) {
        	if(currentLocale.equals(new Locale("pl", "PL"))) {
        		JOptionPane.showMessageDialog(this, "Autorzy:\nMaciej Chaciński \nMichał Kostera", "Autorzy", JOptionPane.INFORMATION_MESSAGE);
        	}else {
        		JOptionPane.showMessageDialog(this, "Authors :\nMaciej Chaciński \nMichał Kostera", "Authors", JOptionPane.INFORMATION_MESSAGE);
   		 	}
            
        } else if (e.getSource() == polskiItem) {
            currentLocale = new Locale("pl", "PL");
            virusPanel.currentLocale = new Locale("pl", "PL");
            updateUILanguage();
        } else if (e.getSource() == angielskiItem) {
            currentLocale = Locale.US;
            virusPanel.currentLocale = Locale.US;
            updateUILanguage();
        } else if (e.getSource() == changeBackgroundColorItem) {
            changeBackgroundColor();
            //System.out.println("aaaaa");
        }else if (e.getSource() instanceof JComponent && ((JComponent) e.getSource()).getParent() == virusPanel) {
            virusPanel.actionPerformed(e);
        }
    }

    private void changeBackgroundColor() {
        if (simulationPanel != null) {
            Color selectedColor = JColorChooser.showDialog(this, "Wybierz kolor tła", simulationPanel.getBackground());
            if (selectedColor != null) {
                simulationPanel.setBackground(selectedColor);
                //System.out.println("bbbbb");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Brak panelu symulacji do zmiany tła.", "Błąd", JOptionPane.ERROR_MESSAGE);
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
