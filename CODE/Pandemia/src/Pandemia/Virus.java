package Pandemia;

import java.awt.Color;

public class Virus 
{
	public int ID;
	public Color infectedColor;
	public double spreadability;
	public double lethality;
	public double complexity;
	
	public Virus()
	{
		setID(0);
		setSpreadability(0);
		setLethality(0);
		setComplexity(0);
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
	public void mutate(double mutationChance) {
        if (Math.random() < mutationChance) {
            // Mutation attempts
            //int attempts = 0;

            //while (attempts < 3) { // Try up to 3 times
                int mutationDirection = Math.random() < 0.5 ? -1 : 1; // Randomly select mutation direction
                int mutationAmount = (int) (Math.random() * 2) + 1; // Random mutation amount (1 or 2)

                // Mutate ID
                int newId = ID + mutationDirection * mutationAmount;
                Virus newVirus = VirusPanel.findVirusById(Integer.toString(newId)); // Find virus by ID
                if ((newVirus == null)) 
                {
                    ID = newId;
                 // Randomize color
                	infectedColor = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
                    //infectedColor = Color.red;
                	// Adjust parameters
                    double spreadChange = (Math.random() - 0.5) * 0.5 * spreadability; // Up to 50% change in spreadability
                    spreadability += spreadChange;
                    double lethalityChange = (Math.random() - 0.5) * 0.5 * lethality; // Up to 50% change in lethality
                    lethality += lethalityChange;
                    double complexityChange = (Math.random() - 0.5) * 0.5 * complexity; // Up to 50% change in complexity
                    complexity += complexityChange;
                    // Adjust other parameters similarly
                //} 
                //else 
                //{
                //    attempts++;
                //}
            }

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
    }
	
}
