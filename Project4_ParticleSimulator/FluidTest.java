import sofia.micro.*;
import sofia.graphics.Color;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

// -------------------------------------------------------------------------
/**
 *  Test the Fluid() class
 *  Test the various situations
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.11.01
 */
public class FluidTest extends TestCase
{
    //~ Fields ................................................................
    private ParticleWorld world;
    private Fluid fluid;
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new FluidTest test object.
     */
    public FluidTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        world = new ParticleWorld(4, 4);
        // Make a green fluid that is dissolveable with 2.0 density
        fluid = new Fluid(Color.green, true, 2.0);
    }


    // ----------------------------------------------------------
    
    /**
     * Test the dodge() method when the object is at the bottom
     * of the map and isn't falling
     */
    public void testDodge1()
    {
        //Add object to the bottom of the map
        world.add(fluid, 0, world.getHeight() - 1);
        
        // Fluids slides to the right
        assertTrue(fluid.dodge());
        assertEquals(1, fluid.getGridX());
    }
    
    /**
     * Test the dodge() method when the object lands on Steel 
     * and can only move left
     */
    public void testDodge2()
    {
        // Fluid on top of the Steel object
        world.add(fluid, 2, 1);
        
        // Add conditions to force Steel to move left only
        // Initializes the Steel objects
        Steel steel1 = new Steel();
        Steel steel2 = new Steel();
        
        world.add(steel1, 1, 2);
        world.add(steel2, 2, 2);
        
        Steel steel4 = new Steel();
        world.add(steel4, 3, 2);
       
        fluid.dodge();
        
        // Fluid moved left
        assertEquals(1, fluid.getGridX());
        assertEquals(1, fluid.getGridY());
        
    }
    
    
    /**
     * Test the dodge() method when the object lands on Steel 
     * and can only move right
     */
    public void testDodge3()
    {
        world.add(fluid, 2, 1);
        
        // Add conditions to force fluid to only move right
        // Initializes the Steel objects
        Steel steel1 = new Steel();
        Steel steel2 = new Steel();
        world.add(steel1, 1, 2);
        world.add(steel2, 2, 2);
        Steel steel3 = new Steel();
        world.add(steel3, 1, 1);
        Steel steel4 = new Steel();
        world.add(steel4, 3, 2);
        
        fluid.dodge();
        
        // Fluid moves to (3, 1)
        assertEquals(3, fluid.getGridX());
        assertEquals(1, fluid.getGridY());
    }
    
    
    /**
     * Test the dodge() method when the object lands on Steel 
     * but cannot move anywhere because it's surrounded by
     * Steel
     */
    public void testDodge4()
    {
        world.add(fluid, 2, 1);
        
        // Add conditions to force surround Fluid with Steels
        // Initializes the Steel objects
        Steel steel1 = new Steel();
        Steel steel2 = new Steel();
        world.add(steel1, 1, 2);
        world.add(steel2, 2, 2);
        Steel steel3 = new Steel();
        world.add(steel3, 1, 1);
        Steel steel4 = new Steel();
        world.add(steel4, 3, 2);
        Steel steel5 = new Steel();
        world.add(steel5, 3, 1);
        
        fluid.dodge();
        
        // Fluid stays in the same place
        assertEquals(2, fluid.getGridX());
        assertEquals(1, fluid.getGridY());
        
    }
    
    /**
     * Test the dodge() method when the object is in
     * FREE FALL
     */
    public void testDodge5()
    {
        // Fluid does not need to dodge in free fall
        world.add(fluid, 2, 1);
        assertFalse(fluid.dodge());
    }
    
    /**
     * Test the dodge() method when particle sinks straight down
     */
    public void testDodge6()
    {
        world.add(fluid, 2, 1);
        
        // Add conditions to make fluid swap
        // places with water below it
        Water water = new Water();
        world.add(water, 2, 2);
        
        Steel steel1 = new Steel();
        world.add(steel1, 3, 2);
        
        fluid.dodge();
        
        // Fluid and water swapped positions
        assertEquals(2, fluid.getGridX());
        assertEquals(2, fluid.getGridY());
        
        assertEquals(2, water.getGridX());
        assertEquals(1, water.getGridY());
    }
    
    
    /**
     * Test the dodge() method when particle sinks straight down
     * and to the left cell.
     */
    public void testDodge7()
    {
        world.add(fluid, 2, 1);
        
        // Add conditions to force Fluid to move down and left
        // Initializes the Steel objects
        Steel steel1 = new Steel();
        world.add(steel1, 2, 2);
       
        fluid.dodge();
        
        assertEquals(1, fluid.getGridX());
        assertEquals(2, fluid.getGridY());
    }
    
    /**
     * Test the dodge() method when particle sinks straight down
     * and to the right cell.
     */
    public void testDodge8()
    {
        world.add(fluid, 2, 1);
        
        // Add conditions to force Fluid to move down and right
        // Initializes the Steel objects
        Steel steel1 = new Steel();
        world.add(steel1, 2, 2);
        Steel steel2 = new Steel();
        world.add(steel2, 1, 2);
        
        fluid.dodge();
        
        assertEquals(3, fluid.getGridX());
        assertEquals(2, fluid.getGridY());
    }
    
}
