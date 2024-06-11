package Pandemia;

import java.awt.Color;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Person {
	
	//location
	int x, y;
	
	//velocity
	int vx, vy;
	
	//status
	int status = 0;
	// 0 =healthy
	// 1 = infected
	// 2 = cured
	// 3 = dead
	
	Virus infectedBy;
	 ArrayList<Integer> recoveredFrom = new ArrayList<>();
	//recoveryTime
	int recoveryTime;

	static int numInfected = 0;
	
	//Constructor for the Person objects
	public Person() {
		Random random = new Random();
		//randomize the position of the Person object to be within the 800x600 frame!
		x = (int)(Math.random()*590+0);
		y = (int)(Math.random()*590+0);
		
		
		if (Math.random() < 0.05) 
		{
            // Get the virusesList using the static method
            ArrayList<Virus> virusesList = VirusPanel.getVirusesList();
            // Check if the virusesList is not empty
            if (!virusesList.isEmpty()) 
            {
                // Get a random virus from the list
                Virus randomVirus = virusesList.get((int) (Math.random() * virusesList.size()));
                // Use the spreadability of the virus to determine infection
                if (Math.random() < randomVirus.getSpreadability()) {
                    status = 1; // Infected
                    numInfected++;
                    infectedBy = randomVirus; // Set infectedBy to the random virus
                    recoveryTime =  random.nextInt((int) (2000+ 600*infectedBy.complexity)); // 
                }
            }
        }
		
		
		//code to make 5% of the Person objects infected 
		//if(Math.random()<.05) 
		//{
		//	status = 1;
		//	numInfected++;
		//}
		
		vx  = (int)(Math.random()*(10+1)+-5);
		vy  = (int)(Math.random()*(10+1)+-5);
		
		//randomize how long it takes for the Person objects to recover!
		//this one is between 5-7 seconds (numbers are in milliseconds)

		
	}
	
	public void collision(Person p2) {
	    // Represent the Person objects as Rectangles for simple collision detection
	    Rectangle per1 = new Rectangle(p2.x, p2.y, 10, 10);
	    Rectangle per2 = new Rectangle(this.x, this.y, 10, 10);

	    // Collision check
	    if (per1.intersects(per2)) {
	    	Random random = new Random();
	        // Infection only happens if one person is infected and the other has never been infected before
	        if (this.status == 1 && p2.status == 0) { // Case person 1 is infected and person 1 is not
	            if (this.infectedBy != null) { // Check if infectedBy is not null
	                if (Math.random() < this.infectedBy.getSpreadability()/100)
	                {
	                	Virus current = this.infectedBy;
	                	if (Math.random() < this.infectedBy.getComplexity()/100 )
	                	{
	                		current = this.infectedBy.mutate();
	                		
	                	}
	                    //this.infectedBy.mutate(this.infectedBy.getComplexity() / 1);
	                    p2.status = 1;
	                    if (current != null)
	                    {
	                    p2.infectedBy = current; // Infect person with the same virus
	                    p2. recoveryTime =  random.nextInt((int) (5000+ 600*current.complexity)); // 
	                    numInfected++; // Add to total count of infected people
	                    }
	                    else if (this.infectedBy != null)
	                    {
	                    	p2.infectedBy = this.infectedBy;
	                    	p2.recoveryTime =  random.nextInt((int) (5000+ 600*this.infectedBy.complexity));
	                    	numInfected++; // Add to total count of infected people
	                    	
	                    }
	                    else
	                    {
	                    	p2.status = 0;
	                    }
	                }
	            }
	        } else if (this.status == 0 && p2.status == 1) { // Case person 2 is infected and person 1 is not
	            if (p2.infectedBy != null) { // Check if infectedBy is not null
	                if (Math.random() < p2.infectedBy.getSpreadability()/100) 
	                {
	                	Virus current = this.infectedBy;
	                	if (Math.random() < p2.infectedBy.getComplexity()/100 )
	                	{
	                		current = p2.infectedBy.mutate();
	                		
	                	}
	                    //p2.infectedBy.mutate(p2.infectedBy.getComplexity() / 1);
	                    this.status = 1;
	                    if (current != null)
	                    {
	                    	
	                    this.infectedBy = current; // Infect person with the same virus
	                    this. recoveryTime =  random.nextInt((int) (5000+ 600*current.complexity)); // 
	                    numInfected++; // Add to total count of infected people
	                    }
	                    else if(p2.infectedBy != null)
	                    {
	                    	this.infectedBy = p2.infectedBy;
	                    	this.recoveryTime = random.nextInt((int)(5000+ 600*p2.infectedBy.complexity) );
	                    	numInfected++; // Add to total count of infected people
	                    	
	                    }
	                    else
	                    {
	                    	this.status = 0;
	                    }
	                }
	            }
	        } else if (this.status == 2 && p2.status == 1) { // Case person 1 is cured and person 2 is infected
	            if (this.infectedBy != null && !this.recoveredFrom.contains(p2.infectedBy.getID())) { // Check if infectedBy is not null
	                if (Math.random() < this.infectedBy.getSpreadability()/100) 
	                {
	                	
	                	Virus current = this.infectedBy;
	                	//p2.infectedBy.mutate(p2.infectedBy.getComplexity() / 1);
	                	if (Math.random() < p2.infectedBy.getComplexity()/100 )
	                	{
	                		current = p2.infectedBy.mutate();
	                		
	                	}
	                	
	                    this.status = 1;
	                    if (current != null)
	                    {
	                    this.infectedBy = current; // Infect person with the same virus
	                    this. recoveryTime =  random.nextInt((int) (5000+ 600*current.complexity)); // 
	                    numInfected++; // Add to total count of infected people
	                    }
	                    else if(p2.infectedBy != null)
	                    {
	                    	this.infectedBy = p2.infectedBy;
	                    	this.recoveryTime = random.nextInt((int)(5000+ 600*p2.infectedBy.complexity) );
	                    	numInfected++; // Add to total count of infected people
	                    	
	                    }
	                    else {
	                    	this.status = 0;
	                    }
	                }
	            }
	        } else if (this.status == 1 && p2.status == 2) { // Case person 1 is infected and person 2 is cured
	            if (p2.infectedBy != null && !p2.recoveredFrom.contains(this.infectedBy.getID())) { // Check if infectedBy is not null
	                if (Math.random() < p2.infectedBy.getSpreadability()/100) {
	                	 //this.infectedBy.mutate(this.infectedBy.getComplexity() / 1);
	                	
	                	Virus current = this.infectedBy;
	                	if (Math.random() < this.infectedBy.getComplexity()/100 )
	                	{
	                		current = this.infectedBy.mutate();
	                		
	                	}
	                	
	                    p2.status = 1;
                  
		                if (current != null)
		                {
		                p2.infectedBy = current; // Infect person with the same virus
		                p2. recoveryTime =  random.nextInt((int) (5000+ 600*current.complexity)); // 
		                numInfected++; // Add to total count of infected people
		                }
	                    else if (this.infectedBy != null)
	                    {
	                    	p2.infectedBy = this.infectedBy;
	                    	p2.recoveryTime =  random.nextInt((int) (5000+ 600*this.infectedBy.complexity));
	                    	numInfected++; // Add to total count of infected people
	                    	
	                    }
	                    else
	                    {
	                    	p2.status = 0;
	                    }
	                }
	                }
	            }
	        }    
	    }




		
	public void paint(Graphics g) {
		
		//set the color of the Person object based on the health status
		switch(status) 
		{
			case 0: //normal
				g.setColor(VirusPanel.healthyCellColor);
				break;
			case 1: // Infected
	            if (infectedBy != null && infectedBy.infectedColor != null) {
	                g.setColor(infectedBy.infectedColor);
	            } else {
	                // Use a default color if infectedBy or its color is null
	                g.setColor(Color.RED);
	            }
	            break;
			case 2: //recovered
				g.setColor(VirusPanel.immuneCellColor);
				break;
			case 3: // Dead
                g.setColor(VirusPanel.deadCellColor);
                break;
				
		}
		
		//If person is infected, they eventually recover so that they don't 
		//infect people forever. 
		if(infectedBy != null) 
		{
			//recoveryTime update
			recoveryTime-=16;
			
			//once the person has been given enough time, they will be considered recovered
			if (recoveryTime <= 0) 
			{
	            // Random chance for person to die
	            if (Math.random() < infectedBy.lethality/100) 
	            { //
	            	die(); // Call the die method
	            } else 
	            {
	                status = 2; // Set status to recovered
	                recoveredFrom.add(infectedBy.getID());
	                numInfected--; // Decrease infected count
	                infectedBy = null;
	            }
	        }
		}
		
		//x and y components are updated based on their velocities
		x += vx;
		y += vy;
		
		//code to have the Person objects bounce off the borders
		if(x < 0 || x >= 680) {
			vx *= -1;
		}
		
		//bounce off the top and bottom
		if(y < 0 || y >= 540) {
			vy *= -1;
		}
		
		//draw the oval representign the Person object
		g.fillOval(x, y, 10, 10);
		
	}
	
	public void die() {
	    status = 3; // Set status to dead
	    numInfected--; // Decrease infected count
	    vx = 0; // Stop movement
	    vy = 0; // Stop movement
	    infectedBy = null; // Set infectedBy to null
	}
}
