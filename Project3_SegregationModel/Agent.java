import sofia.micro.*;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

//-------------------------------------------------------------------------
/**
 *  The agent class that will be used to populate the City.
 *  It will also determine the position that satisfies
 *  the satisfaction threshold of each agent on the map.
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 10.13.2019
 */
public class Agent extends Actor
{
    //~ Fields ................................................................
    // Store the kind of animal and its satisfaction
    private String animalKind;
    private double satisfaction;

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new Agent object.
     * Default Agent will be an elephant
     * with satisfaction threshold of 30%
     */
    public Agent()
    {
        this("elephant", 0.3);
    }

    
    /**
     * Method that determines what kind of animal to generate
     * 
     * @param animalName type of animal to create
     * @param satisfactionPercent the animal's satisfaction threshold
     */

    public Agent(String animalName, double satisfactionPercent)
    {
        animalKind = animalName;
        satisfaction = satisfactionPercent;

        this.setImage(animalKind + ".png");
    }

    //~ Methods ...............................................................

    /**
     * A getter method that returns the kind of agent, 
     * as a string (such as "elephant", for example).
     * 
     * @return animalKind the kind of agent
     */
    public String getKind()
    {
        return animalKind;
    }

    
    /**
     * A getter method that returns the agent's satisfaction threshold, 
     * as a double value.
     * 
     * @return satisfaction the satisfaction threshold
     */
    public double getThreshold()
    {
        return this.satisfaction;
    }

    
    /**
     * A boolean method that returns true if 
     * this agent is the same kind of agent as the one provided.
     * 
     * @param name identifier of agent to check
     * @return a true or false statement
     */

    public boolean isSameKindAs(Agent name)
    {
        return animalKind.equals(name.getKind());
    }

    
    /**
     * A boolean method that returns true if this agent 
     * would be satisfied at the specified location, or not.
     * 
     * @param x the Agent's x position
     * @param y the Agent's y position
     * @return a true or false statement
     */

    public boolean isSatisfiedAt(int x, int y)
    {
        // Get height and Width of the world
        // to prevent going out of bounds
        int worldWidth = this.getWorld().getWidth() - 1;
        int worldHeight = this.getWorld().getHeight() - 1;

        // An int to keep track of number of 
        // the same animals that will be in the surroundings
        int sameKind = 0;

        // An int to keep track of number of 
        // total animals that will be found 
        // and checked in the surroundings
        int totalAnimals = 0;

        // Defines where to check in the surroundings
        // Math.max() and Math.min() are used to prevent checking 
        // out of bounds when looking for animals
        int lowX = Math.max(0, x - 1);
        int highX = Math.min(worldWidth, x + 1);
        int lowY = Math.max(0, y - 1);
        int highY = Math.min(worldHeight, y + 1);

        //Check surrounding animals to determine if it is satisfied
        for (int i = lowX; i <= highX; i++)
        {
            for (int j = lowY; j <= highY; j++)
            {
                // Get the Agent at (i, j) position if there is one
                Agent agent = this.getWorld().getOneObjectAt(i, j, Agent.class);

                
                if (agent != null)
                {
                    totalAnimals++;
                    
                    // Same kind of Agent
                    if (this.isSameKindAs(agent))
                    {
                        sameKind++;
                    }
                    
                }


            }
        }
        
        if (totalAnimals - 1 <= 0)
        {
            return false;
        }

        
        // The for loops counted the animal at the position we are testing
        // However, we are not counting that animal 
        // so -1 animal on sameKind and totalAnimals
        sameKind--;
        totalAnimals--;

        // Calculate the satisfaction threshold of animal at position
        // we are testing.
        double satisfied = (double) sameKind / (double) totalAnimals;

        return (satisfied >= this.getThreshold());
    }

    
    /**
     * A boolean method that returns true if this agent 
     * is satisfied at its current location. 
     * 
     * @return a true or false statement
     */

    public boolean isSatisfied()
    {
        return this.isSatisfiedAt(this.getGridX(), this.getGridY());
    }

    
    /**
     * A method that moves the Agent to a new location in the grid 
     * where it will be satisfied, if there is one
     */
    public void relocate()
    {
        // Variables used to make sure Agent does not go out of bounds
        int cityWidth = this.getWorld().getWidth() - 1;
        int cityHeight = this.getWorld().getHeight() - 1;

        for (int i = 0; i <= cityWidth; i++)
        {
            for (int j = 0; j <= cityHeight; j++)
            {
                if (this.getWorld().getOneObjectAt(i, j, Agent.class) == null
                    && this.isSatisfiedAt(i, j))
                {
                    // Set agent at new empty space if it is satisfied
                    this.setGridLocation(i, j);

                }
            }
        }

    }

    
    /**
     * Executes one "turn" for this agent, which
     * means determining if the agent is satisfied, 
     * and relocating if it is not.
     */
    public void act()
    {
        if (!this.isSatisfied())
        {
            this.relocate();
        } 
    }

    

}
