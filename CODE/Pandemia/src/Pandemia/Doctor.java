package Pandemia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Doctor extends Person {
    private int virusId; // ID of the virus they are vaccinating against
    
    public Doctor(int virusId) {
    	Random random = new Random();
		//randomize the position of the Person object to be within the 800x600 frame!
		x = (int)(Math.random()*990+0);
		y = (int)(Math.random()*590+0);
		vx  = (int)(Math.random()*(10+1)+-5);
		vy  = (int)(Math.random()*(10+1)+-5);
		
        this.virusId = virusId;
        status = 2; // Immune
    }

    @Override
    public void collision(Person p2) {
        Rectangle per1 = new Rectangle(p2.x, p2.y, 10, 10);
        Rectangle per2 = new Rectangle(this.x, this.y, 10, 10);

        if (per1.intersects(per2)) 
        {
            if (p2.status == 1 && p2.infectedBy.getID() == virusId) 
            { // If the person is infected with the virus
                p2.status = 2; // Make them immune
                p2.recoveredFrom.add(virusId);
                p2.infectedBy = null;
                Person.numInfected--; // Decrease infected count
            }
            else if (p2.status == 0) 
            {
                p2.status = 2; // Make them immune
                p2.recoveredFrom.add(virusId);
            }
            else if(p2.status == 1 && p2.infectedBy.getID() != virusId) 
            { // If the person is infected with the virus
                p2.recoveredFrom.add(virusId);
            }
        }
    }

    @Override
    public void paint(Graphics g) 
    {
        g.setColor(Color.BLUE); // Blue color for doctors
        x += vx;
		y += vy;
		
		//code to have the Person objects bounce off the borders
		if(x < 0 || x >= 990) {
			vx *= -1;
		}
		
		//bounce off the top and bottom
		if(y < 0 || y >= 590) {
			vy *= -1;
		}
		
		//draw the oval representign the Person object
		g.fillOval(x, y, 10, 10);
    }
}
