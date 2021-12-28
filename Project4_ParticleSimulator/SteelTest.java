import sofia.micro.*;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

// -------------------------------------------------------------------------
/**
 *  Test the Steel() class
 *  Summarize what your test objectives are.
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.11.01
 */
public class SteelTest extends TestCase
{
    //~ Fields ................................................................
    private Steel testSteel;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new SteelTest test object.
     */
    public SteelTest()
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
        testSteel = new Steel();
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    
    /**
     * Test the Steel() constructor
     */
    public void testSteel()
    {
        assertTrue(testSteel.willDissolve());
        assertEquals(7.87, testSteel.getDensity(), 0.001);
    }
    
    /**
     * Test the isFalling() method that 
     * will always return false
     */
    public void testIsFalling()
    {
        assertFalse(testSteel.isFalling());
    }
    
    
    /**
     * Test the dodge() method that 
     * will always return false
     */
    public void testDodge()
    {
        assertFalse(testSteel.dodge());
    }
    
    
}
