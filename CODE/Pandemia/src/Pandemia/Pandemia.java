package Pandemia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Pandemia extends JFrame implements ActionListener{

    JMenuItem newItem, saveItem, openItem, jezykItem, autorzyItem, polskiItem, angielskiItem;
    VirusPanel virusPanel;
    JPanel pandemiaPanel;

    public Pandemia() {
        setTitle("Symulacja pandemii");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        setLayout(new BorderLayout());

        pandemiaPanel = new JPanel();
        add(pandemiaPanel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu Menu = new JMenu("Menu");
        JMenu SubMenu = new JMenu("Języki");
        newItem = new JMenuItem("New");
        saveItem = new JMenuItem("Save");
        openItem = new JMenuItem("Open");
        jezykItem = new JMenuItem("Język");
        polskiItem = new JMenuItem("Polski (domyślny)");
        angielskiItem = new JMenuItem("Angielski");
        autorzyItem = new JMenuItem("Autorzy");

        Menu.add(newItem);
        Menu.add(saveItem);
        Menu.add(openItem);
        SubMenu.add(polskiItem);
        SubMenu.add(angielskiItem);
        Menu.add(SubMenu);
        Menu.addSeparator();
        Menu.add(autorzyItem);
        autorzyItem.addActionListener(this);
        menuBar.add(Menu);
        setJMenuBar(menuBar);

        virusPanel = new VirusPanel(this);
        add(virusPanel, BorderLayout.EAST); // Virus panel added to EAST

        // Removed the original colour buttons from here
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
        } else if (e.getActionCommand().equals("Polski")) {
            // ... Polish language handling ...
        } else if (e.getActionCommand().equals("Angielski")) {
            // ... English language handling ...
        } 
        else if (e.getSource() instanceof JComponent &&
                ((JComponent) e.getSource()).getParent() == virusPanel) {
            // Delegate action handling to virusPanel if it's the source
            virusPanel.actionPerformed(e);
        }
    }
	
	public static void main(String[] args) {
    	Pandemia pandemia = new Pandemia();
        pandemia.setVisible(true);
	}
	

}