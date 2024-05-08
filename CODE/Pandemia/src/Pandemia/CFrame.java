package Pandemia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CFrame extends JPanel implements ActionListener {

    // Store multiple Person objects
    private ArrayList<Person> people = new ArrayList<Person>(); // The moving Person objects (circles)

    private int time = 0; // Track time as the simulation runs
    private int population;

    public Color healthyColor, recoveredColor, deadColor;
    
    // Constructor
    public CFrame(int population) {
    	
    	 setPreferredSize(new Dimension(1000, 600));
        this.population = population; // Set the population size

        // Setup the Person objects in the list
        for (int i = 0; i < population; i++) {
            // Instantiate an Person object and add it to the ArrayList
            // This is the part that actually CREATES objects we can use
            people.add(new Person());
        }

        // Timer for animation
        Timer t = new Timer(16, this); // 16 is the period in ms, so the timer code runs every 16ms.
        t.start(); // Start the timer
    }

    // Override the paintComponent method to draw the simulation
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass method to ensure proper screen refreshing
        time += 16; // Increment time by 16ms

        // Paint the Person objects
        for (Person p : people) {
            p.paint(g); // Call the paint method of each Person object
        }

        // Check for collision by generating unique pairs of people
        for (int i = 0; i < people.size(); i++) {
            for (int j = i + 1; j < people.size(); j++) {
                // For each unique pair invoke the collision detection code
                people.get(i).collision(people.get(j));
            }
        }
    }

    // This method is called every 16ms based on the timer
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Redraw the simulation
    }

    // Getters and setters
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int pop) {
        this.population = pop;
    }
}
