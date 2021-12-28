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
 * Test various situations in the WeatherBureau() class
 * Test all the methods to ensure proper 
 * functioning of the class.
 *  
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.12.02
 */
public class WeatherBureauTest extends TestCase
{
    //~ Fields ................................................................
    private WeatherBureau testBureau;
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WeatherBureauTest test object.
     */
    public WeatherBureauTest()
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
        testBureau = new WeatherBureau();
    }

    // ----------------------------------------------------------
    /*# Insert your own test methods here */

    /**
     * Test the recordDailySummary method where the rainfall amount
     * is invalid (-1)
     */
    public void testRecordDailySummary1()
    {
        testBureau.recordDailySummary(
            "KE000063612 3.117 35.617 515 2/17/14 -1 82 91 -1");
        
        //Since nothing is recorded because amountRainfall == -1,
        //the station with the lowest rainfall is by default, null.
        assertEquals(null, testBureau.lowestStation());
        assertEquals(null, testBureau.lowestStation(2));
    }
    
    
    /**
     * Test the recordDailySummary method where an existing weather
     * station is modified
     */
    public void testRecordDailySummary2()
    {
        testBureau.recordDailySummary(
            "KE000063612 3.117 35.617 515 2/17/14 0.5 82 91 -1");
        
        //Get the station with the lowest rainfall,
        //which is the one provided in the line above
        WeatherStation lowestS = testBureau.lowestStation();
        String lowestID = lowestS.getId();
        
        //Initial value before the station is modified
        assertEquals("KE000063612", lowestID);
        assertEquals(0.5, lowestS.getAvgForMonth(2), 0.001);
        
        //Modifying the station by adding another record 
        //in the same month
        testBureau.recordDailySummary(
            "KE000063612 3.117 35.617 515 2/11/14 1.5 82 91 -1");
        
        //The new average rainfall amount is now (0.5 + 1.5) / 2
        //which is 1
        lowestS = testBureau.lowestStation();
        assertEquals("KE000063612", lowestID);
        assertEquals(1.0, lowestS.getAvgForMonth(2), 0.001);
    }
    
    
    /**
     * Test the recordDailySummaries() method
     * to find the station with the lowest rainfall
     * amount for different months.
     * 
     */
    public void testRecordDailySummaries()
    {
        //Create a Scanner
        Scanner scan = IOHelper.createScannerForString(
            "KE000063612 3.117 35.617 515 2/17/14 0 82 91 -1\n" +
            "KE000063612 3.117 35.617 515 3/25/14 0.12 76 82 -1\n" +
            "KE000063612 3.117 35.617 515 4/26/14 0.08 80 97 -1\n" +
            "KE000063723 -0.467 39.633 147 5/15/14 1.26 83 -1 71\n" +
            "KE000063723 -0.467 39.633 147 6/16/14 0.5 84 -1 78\n");
        
        //Record the data to the WeatherBureau by passing in the Scanner
        testBureau.recordDailySummaries(scan);
        
        //Get the station with the lowest rainfall
        WeatherStation lowestS = testBureau.lowestStation();
        String lowestID = lowestS.getId();
        
        assertEquals("KE000063612", lowestID);
    }

    

    /**
     * Test the getStation() method, 
     * passing in only 1 station.
     */
    public void testGetStation()
    {
        testBureau.recordDailySummary(
            "KE000063612 3.117 35.617 515 2/17/14 0 82 91 -1");
        
        WeatherStation testS = testBureau.getStation("KE000063612");
        String stationID = testS.getId();
        
        assertEquals("KE000063612", stationID);
        assertEquals(testS, testBureau.lowestStation());
    }
    
   

    /**
     * Test the lowestStation(int month) method
     * for different stations in the same month
     * 
     * In this method, the first station has a 
     * higher average than the second station
     */
    public void testLowestStation1()
    {
        //Create a Scanner
        Scanner scan = IOHelper.createScannerForString(
            "KE000063723 -0.467 39.633 147 2/15/14 1.26 83 -1 71\n" +
            "KE000063723 -0.467 39.633 147 2/16/14 0.5 84 -1 78\n" +
            "KE000063612 3.117 35.617 515 2/17/14 0 82 91 -1\n" +
            "KE000063612 3.117 35.617 515 2/25/14 0.12 76 82 -1\n" +
            "KE000063612 3.117 35.617 515 2/26/14 0.08 80 97 -1\n");

        //Record the data to the WeatherBureau by passing in the Scanner
        testBureau.recordDailySummaries(scan);
        
        //Get the station with the lowest rainfall for that month (2)
        WeatherStation lowestS = testBureau.lowestStation(2);
        String lowestID = lowestS.getId();
        
        assertEquals("KE000063612", lowestID);
        
    }
    
    
    
    /**
     * Test the lowestStation(int month) method
     * for different stations in the same month
     * 
     * In this method, the first station has a 
     * lower average than the second station
     */
    public void testLowestStation2()
    {
        //Create a Scanner
        Scanner scan = IOHelper.createScannerForString(
            "KE000063612 3.117 35.617 515 2/17/14 0 82 91 -1\n" +
            "KE000063612 3.117 35.617 515 2/25/14 0.12 76 82 -1\n" +
            "KE000063612 3.117 35.617 515 2/26/14 0.08 80 97 -1\n" +
            "KE000063723 -0.467 39.633 147 2/15/14 1.26 83 -1 71\n" +
            "KE000063723 -0.467 39.633 147 2/16/14 0.5 84 -1 78\n");
            
        //Record the data to the WeatherBureau by passing in the Scanner
        testBureau.recordDailySummaries(scan);
        
        //Get the station with the lowest rainfall for that month (2)
        WeatherStation lowestS = testBureau.lowestStation(2);
        String lowestID = lowestS.getId();
        
        assertEquals("KE000063612", lowestID);
        
    }
    
    
    /**
     * Test the lowestStation(int month) method
     * for different stations in the same month
     * 
     * In this method, the first station has
     * the same average as the second station
     */
    public void testLowestStation3()
    {
        //Create a Scanner
        Scanner scan = IOHelper.createScannerForString(
            "KE000063723 -0.467 39.633 147 2/16/14 0.1 84 -1 78\n" +
            "KE000063612 3.117 35.617 515 2/17/14 0.1 82 91 -1\n");
            
        //Record the data to the WeatherBureau by passing in the Scanner
        testBureau.recordDailySummaries(scan);
        
        //Get the station with the lowest rainfall for that month (2)
        WeatherStation lowestS = testBureau.lowestStation(2);
        String lowestID = lowestS.getId();
        
        //Since both station have the same rainfall,
        //it will return the first station
        assertEquals("KE000063723", lowestID);
    }
    
        
    /**
     * Test the lowestStation method
     * where no data has been recorded for any month
     */
    public void testLowestStation4()
    {
        assertEquals(null, testBureau.lowestStation());
    }
}
