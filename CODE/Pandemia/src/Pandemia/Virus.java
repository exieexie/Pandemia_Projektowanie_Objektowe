package Pandemia;

import java.awt.Color;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Virus 
{
	public int ID;
	public Color infectedColor;
	public double spreadability;
	public double lethality;
	public double complexity;
	public boolean doctorsAdded;
	
	public Virus()
	{
		setID(0);
		setSpreadability(0);
		setLethality(0);
		setComplexity(0);
		
        doctorsAdded = false; // Initialize to false

	}
	
	public int getID()
	{
		return ID;
	}
	public void setID(int id)
	{
		this.ID = id;
	}
	
	public double getSpreadability()
	{
		return spreadability;
	}
	public void setSpreadability(double spred)
	{
		this.spreadability = spred;
	}
	
	public double getLethality()
	{
		return lethality;
	}
	public void setLethality(double leth)
	{
		this.lethality = leth;
	}
	
	public double getComplexity()
	{
		return complexity;
	}
	public void setComplexity(double comp)
	{
		this.complexity = comp;
	}
	
	
	
	
	//attempting to implement mutations 
	//wish me luck
	public Virus mutate() 
	{
            // Mutation attempts
            //int attempts = 0;

            //while (attempts < 3) { // Try up to 3 times
                int mutationDirection = Math.random() < 0.5 ? -1 : 1; // Randomly select mutation direction
                int mutationAmount = (int) (Math.random() * 2) + 1; // Random mutation amount (1 or 2)

                // Mutate ID
                int newId = ID + mutationDirection * mutationAmount;
                Virus checkVirus = VirusPanel.findVirusById(Integer.toString(newId)); // Find virus by ID
                if ((checkVirus == null)) 
                {
                	Virus newVirus = new Virus();
                    newVirus.ID = newId;
                 // Randomize color
                    Random rand = new Random();
                    
                    
                    newVirus.infectedColor = new Color(rand.nextInt(256), rand.nextInt(256),rand.nextInt(256));
                    //newVirus.infectedColor = Color.MAGENTA;
                    
                    //infectedColor = Color.red;
                	// Adjust parameters
                    double spreadChange = (Math.random() - 0.5) * 0.5 * spreadability; // Up to 50% change in spreadability
                    newVirus.spreadability += this.spreadability;
                    newVirus.spreadability += spreadChange;
                    newVirus.spreadability += 1;
                    double lethalityChange = (Math.random() - 0.5) * 0.5 * lethality; // Up to 50% change in lethality
                    newVirus.lethality += this.lethality;
                    newVirus.lethality += lethalityChange;
                    newVirus.lethality += 1;
                    double complexityChange = (Math.random() - 0.5) * 0.5 * complexity; // Up to 50% change in complexity
                    newVirus.complexity += this.complexity;
                    newVirus.complexity += complexityChange;
                    newVirus.complexity += 1;
                    
                    VirusPanel.virusesList.add(newVirus);
                    newVirus.scheduleDoctorCreation();
                    
                    return newVirus;
                }
                else 
                {
                return this;
                }
                    // Adjust other parameters similarly
                //} 
                //else 
                //{
                //    attempts++;
                //}


            /*
            // If ID mutation was successful, randomize color and adjust parameters
            if (mutated) 
            {
                // Randomize color
            	infectedColor = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));

            	// Adjust parameters
                double spreadChange = (Math.random() - 0.5) * 0.5 * spreadability; // Up to 50% change in spreadability
                spreadability += spreadChange;
                double lethalityChange = (Math.random() - 0.5) * 0.5 * lethality; // Up to 50% change in lethality
                lethality += lethalityChange;
                double complexityChange = (Math.random() - 0.5) * 0.5 * complexity; // Up to 50% change in complexity
                complexity += complexityChange;
                // Adjust other parameters similarly
            }
            */
    }
	public void scheduleDoctorCreation() {
        Timer timer = new Timer();
        System.out.println("doctors scheduled for virus :" + this.ID);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                VirusPanel.addDoctorsForVirus(ID);
            }
        }, (long)(600 *complexity)); // 60 seconds if complexity is 100
	}
	
}
