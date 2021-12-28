// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

import sofia.micro.*;
import java.util.Scanner;
import student.IOHelper;

// -------------------------------------------------------------------------
/**
 *  Test the functions of the RainfallAnalyzer() class
 *  Use all different scenarios to ensure proper
 *  functioning of the test
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.12.02
 */
public class RainfallAnalyzerTest extends TestCase
{
    //~ Fields ................................................................
    private RainfallAnalyzer rain;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new RainfallAnalyzerTest test object.
     */
    public RainfallAnalyzerTest()
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
        rain = new RainfallAnalyzer();
    }


    // ----------------------------------------------------------
    
    /**
     * Test the constructor where there
     * is valid data entered
     */
    public void testRainfallAnalyzer1()
    {
        //Get the data of the lowest station that will be displayed
        WeatherStation currentS = rain.getStation();
        String stationID = currentS.getId();
  
        assertEquals("KE000063820", stationID);
        assertEquals(1, currentS.getLowestMonth());
        assertEquals(0.00, currentS.getAvgForMonth(1), 0.001); 
    }
    
    /**
     * Test the constructor where 
     * no data is entered
     */
    public void testRainfallAnalyzer2()
    {
        //Create a Scanner that scans no data and passing it
        //to the constructor
        Scanner scan = IOHelper.createScannerForString("");
        rain = new RainfallAnalyzer(scan);
        
        WeatherBureau currentB = rain.getBureau();
        //Get the data of the lowest station that will be displayed
        WeatherStation currentS = currentB.lowestStation();
  
        assertEquals(null, currentS);
        assertEquals(rain.getText().getText(), "no data"); 
    }
}
