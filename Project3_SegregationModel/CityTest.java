import sofia.micro.*;
import sofia.util.Random;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

// -------------------------------------------------------------------------
/**
 *  Test class for the City class.
 *  This contains tests for the City()
 *  and populate() methods.
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 10.13.2019
 */
public class CityTest extends TestCase
{
    //~ Fields ................................................................
    private City testCity;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new CityTest test object.
     */
    public CityTest()
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
        // There is no code for SetUp()
    }

    // ----------------------------------------------------------
    /*# Insert your own test methods here */

    /**
     * Test the default City() constructor
     */
    public void testCity()
    {   
        testCity = new City();

        // A City() constructor will create a 5x5 city
        assertEquals(5, testCity.getWidth());
        assertEquals(5, testCity.getHeight());
    }

    /**
     * Test the City(int width, int height) constructor
     */
    public void testCity1()
    {
        testCity = new City(10, 10);
        
        assertEquals(10, testCity.getWidth());
        assertEquals(10, testCity.getHeight());
    }
    
    /**
     * Test the populate() method using the Random
     * Number generator
     */
    public void testPopulate()
    {
        testCity = new City(2, 2);

        // Use the function of Random Generator to set:
        // An Elephant at (0, 0)
        // A Monkey at (0, 1)
        // A empty space at (1, 0)
        // A Elephant at (1, 1)
        Random.generator().setNextDoubles(0.2, 0.4, 0.9, 0.2);

        // Populate city with desired Agents
        // Logic: 
        // if double <= 0.3 then spawn Elephant
        // if 0.3 < double <= 0.7 then spawn Monkey
        // if double > 0.7 then leave space EMPTY
        testCity.populate(0.3, 0.4, 0.3);

        // There should be:
        // An Elephant at (0, 0)
        // A Monkey at (0, 1)
        // A empty space at (1, 0)
        // A Elephant at (1, 1)
        assertEquals("elephant", 
            testCity.getOneObjectAt(0, 0, Agent.class).getKind());
            
        assertEquals("monkey", 
            testCity.getOneObjectAt(0, 1, Agent.class).getKind());
            
        assertEquals(null, testCity.getOneObjectAt(1, 0, Agent.class));
        
        assertEquals("elephant", 
            testCity.getOneObjectAt(1, 1, Agent.class).getKind());

    }
    
    /**
     * Test the populate() method when it is
     * told to spawn 0 moneys and elephants
     */
    public void testPopulate1()
    {
        testCity = new City(2, 2);

        // Use the function of Random Generator to set:
        // A empty space at (0, 0)
        // A empty space at (0, 1)
        // A empty space at (1, 0)
        // A empty space at (1, 1)
        Random.generator().setNextDoubles(0.9, 0.9, 0.9, 0.9);
        
        testCity.populate(0.0, 0.0, 0.0);
        
        assertEquals(null, testCity.getOneObjectAt(0, 0, Agent.class));
        assertEquals(null, testCity.getOneObjectAt(0, 1, Agent.class));
        assertEquals(null, testCity.getOneObjectAt(1, 0, Agent.class));
        assertEquals(null, testCity.getOneObjectAt(1, 1, Agent.class));
        
    }
}
