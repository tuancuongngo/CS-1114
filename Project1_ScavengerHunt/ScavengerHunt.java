import sofia.micro.jeroo.*;

//-------------------------------------------------------------------------
/**
 *  Navigates Jeroo around Long Island to collect flowers.
 *  Calls different methods from class FlowerPicker to instruct
 *  Jeroo to pick up all 8 flowers located in different positions
 *  on Long Island.
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.09.03
 */
public class ScavengerHunt extends LongIsland
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new ScavengerHunt object.
     */
    public ScavengerHunt()
    {
        // Nothing to initialize, leaving the world a default size
    }


    //~ Methods ...............................................................
    
    /**
     * Set of instructions Jeroo will use to pick up the flowers.
     */
    
    public void myProgram()
    {
        FlowerPicker bob = new FlowerPicker(2, 2);
        this.add(bob);
        
        bob.navigateIsland(RIGHT, 2);
        bob.navigateIsland(LEFT, 3);
        bob.secureFlower(LEFT, 3);
        bob.secureFlower(RIGHT, 3);
        bob.secureFlower(AHEAD, 4);
        bob.navigateIsland(RIGHT, 8);
        bob.secureFlower(LEFT, 1);
        bob.spinAround();
        bob.secureFlower(AHEAD, 2);
        bob.secureFlower(AHEAD, 3);
        bob.navigateIsland(RIGHT, 1);
        bob.navigateIsland(LEFT, 2);
        bob.secureFlower(LEFT, 1);
        bob.spinAround();
        bob.navigateIsland(AHEAD, 1);
        bob.navigateIsland(RIGHT, 3);
        bob.secureFlower(LEFT, 3);
        
    }
    

}
