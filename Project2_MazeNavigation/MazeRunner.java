import sofia.micro.jeroo.*;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)
//-------------------------------------------------------------------------
/**
 *  Type of Jeroo class that picks flowers and removes nets
 *  and finish at the specified position.
 *  This MazeRunner Jeroo will pick all the flowers on the island
 *  and use them to disable all the nets. It will then go to the
 *  position (1,1)
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.09.18
 */
public class MazeRunner extends Jeroo
{
    //~ Fields ................................................................

    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new MazeRunner object, giving it 15 flowers.
     */
    public MazeRunner()
    {
        super(15);
    }


    //~ Methods ...............................................................

    /**
     * method that tells the MazeRUnner to complete the maze
     * by clearing the island and returning to its finish
     * position.
     */
    public void myProgram()
    {
        // Clear island of all nets and flowers
        this.clearIsland();
        
        // Hop to finish position of (1,1)
        this.goToFinish();
    }
    
    
    
    
    /**
     * This method will make a MazeRunner navigate the island
     * by sticking to one side of the wall and picking up
     * flowers of disable any nets that it sees.
     */
    public void clearIsland()
    {      
        int numberOfNets = this.getWorld().getObjects(Net.class).size();
        int numberOfFlowers = this.getWorld().getObjects(Flower.class).size();
        
        while (numberOfNets != 0 || numberOfFlowers != 0)
        {
            // Somtimes the jeroo's initial spawn position contains a flower.
            this.pick();
            
            if (this.isClear(AHEAD))
            {
                this.hop();
            }
            
            if (this.seesNet(AHEAD))
            {
                this.toss();
            }
            
            if (this.seesFlower(AHEAD))
            {
                this.hop();
                this.pick();
            }
            
            // Calls the method to help MazeRunner orientates in the
            // appropriate direction on the island.
            this.findOrientation();
 
            // Updates the number of nets and flowers remaining
            numberOfNets = this.getWorld().getObjects(Net.class).size();
            numberOfFlowers = this.getWorld().getObjects(Flower.class).size();
        }
    }

    
  
    /**
     * This method will make the Jeroo navigate around the island
     * until it reaches its finishing position of (1,1)
     */
    public void goToFinish()
    {        
        while (this.getGridX() != 1 || this.getGridY() != 1)
        { 
            // Calls the method to help MazeRunner orientates in the
            // appropriate direction around the island.
            this.findOrientation();
            
            // After facing the correct direction, MazeRunner will hop 1 space.
            this.hop();
        }
    }
    
    
    
    
    /**
     * Method that will orientate MazeRunner so that it faces
     * the appropriate direction.
     */
    public void findOrientation()
    {
        //Navigates using only left turns
        if (!this.seesWater(LEFT))
        {
            this.turn(LEFT);
        }
            
        //MazeRunner turns around when reaching a dead end.
        if (this.seesWater(AHEAD) && this.seesWater(RIGHT))
        {
            this.turn180();
        }
            
        //Make right turns when necessary
        if (this.seesWater(AHEAD))
        {
            this.turn(RIGHT);
        }
        
    }
    
    
    
    
    /**
     * Make a MazeRunner turn around 180 degrees
     * so that it can get out of dead ends when
     * it is surrounded by water.
     */
    public void turn180()
    {
        this.turn(LEFT);
        this.turn(LEFT);
    }
}
        
       
     
