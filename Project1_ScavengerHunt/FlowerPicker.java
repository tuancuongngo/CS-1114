import sofia.micro.jeroo.*;

//-------------------------------------------------------------------------
/**
 *  Represents a special type of Jeroo that picks up winsum flowers.
 *  Jeroo will be able to turn in specified directions 
 *  and hop around the island to pick up flowers.
 *  
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.09.03
 */
public class FlowerPicker extends Jeroo
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    
    /**
     * Creates a new FlowerPicker Jeroo facing East with no flowers.
     * 
     * @param x         The x-coordinate of the Jeroo's location.
     * @param y         The y-coordinate of the Jeroo's location.
     */
    public FlowerPicker(int x, int y)
    {
       super(x, y);
    }


    //~ Methods ...............................................................

    /**
     * Turn in a specified direction and hop a number of spaces ahead.
     * 
     * @precondition All the spaces the FlowerPicker will hop into are clear.
     * @param turnTo Determine direction the FlowerPicker is turning to.
     * @param numberOfHops Determine number of hops the FlowerPicker will take.
     */
    public void navigateIsland(RelativeDirection turnTo, int numberOfHops)
    {
        this.turn(turnTo);
        this.hop(numberOfHops);
    }
    
    
    /**
     * Turn in a specified direction, hop a number of spaces ahead then 
     * pick up a flower.
     * 
     * @precondition All the spaces the FlowerPicker will hop into are clear.
     * @precondition The final space that the FlowerPicker will hop into 
     * contains a flower.
     * @param turnTo Determine direction the FlowerPicker is turning to.
     * @param numberOfHops Determine number of hops the FlowerPicker will take 
     * to pick the flower.
     */
    public void secureFlower(RelativeDirection turnTo, int numberOfHops)
    {
        this.navigateIsland(turnTo, numberOfHops);
        this.pick();
    }
    
    
    /**
     * FlowerPicker turn around 180 degrees.
     */
    public void spinAround()
    {
        this.turn(LEFT);
        this.turn(LEFT);
    }
    
    
    
    

}
