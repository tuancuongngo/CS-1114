// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

import sofia.micro.*;

// -------------------------------------------------------------------------
/**
 *  Test various situations in the WeatherStation() class
 *  Test all the methods to ensure proper 
 *  functioning of the WeatherStation() class.
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.12.02
 */
public class WeatherStationTest extends TestCase
{
    //~ Fields ................................................................
    private WeatherStation testStation;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WeathertestStation test object.
     */
    public WeatherStationTest()
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
        testStation = new WeatherStation("KE000063612");
    }


    // ----------------------------------------------------------
    /**
     * Test the getId() method
     */
    public void testGetId()
    {
        assertEquals("KE000063612", testStation.getId());
    }
    
    /**
     * Test the recordDailyRain() method, passing in
     * the appropriate month and rainfall amount
     */
    public void testRecordDailyRain1()
    {
        testStation.recordDailyRain(1, 1.0);
        
        //There is only 1 data for january
        assertEquals(1, testStation.getCountForMonth(1));
        assertEquals(1.0, testStation.getAvgForMonth(1), 0.001);
    }
    
    /**
     * Test the recordDailyRain() method, passing in
     * a negative month.
     */
    public void testRecordDailyRain2()
    {
        testStation.recordDailyRain(-1, 1.0);
        
        //Since we added an invalid month, the month with the lowest
        //rainfall will be by default, january, which is 1.
        assertEquals(testStation.getLowestMonth(), 1);
        
    }
    
     /**
     * Test the recordDailyRain() method, passing in
     * a month that is more than 12.
     */
    public void testRecordDailyRain3()
    {
        testStation.recordDailyRain(13, 1.0);
        
        //Since we added an invalid month, the month with the lowest
        //rainfall will be by default, january, which is 1.
        assertEquals(testStation.getLowestMonth(), 1);
        
    }
    
     /**
     * Test the recordDailyRain() method, passing in
     * a negative rainfall amount
     */
    public void testRecordDailyRain4()
    {
        testStation.recordDailyRain(1, -1.0);
        
        //Since we added an invalid rainfall, the month with the lowest
        //rainfall will be by default, january, which is 1.
        assertEquals(testStation.getLowestMonth(), 1);
    }
    
     /**
     * Test the recordDailyRain() method, passing in
     * a negative rainfall amount and an out of bounds
     * month
     */
    public void testRecordDailyRain5()
    {
        testStation.recordDailyRain(13, -1.0);
        
        //Since we added an invalid month and invalid rainfall
        //the month with the lowest rainfall will be by default, 
        //january, which is 1.
        assertEquals(testStation.getLowestMonth(), 1);
    }
    
    
    /**
     * Test the getCountForMonth() method
     * when passing in a appropriate month
     */
    public void testGetCountForMonth1()
    {
        testStation.recordDailyRain(1, 1.0);
        
        assertEquals(testStation.getCountForMonth(1), 1);
    }
    
    
    /**
     * Test the getCountForMonth() method
     * when passing in a month less than 0
     */
    public void testGetCountForMonth2()
    {
        testStation.recordDailyRain(-1, 1.0);
        
        //Since we added an invalid month, the month with the lowest
        //rainfall will be by default, january, which is 1.
        assertEquals(testStation.getLowestMonth(), 1);
        assertEquals(testStation.getCountForMonth(-1), 0);
    }
    
    
    
    /**
     * Test the getCountForMonth() method
     * when passing in a month more than 12
     */
    public void testGetCountForMonth3()
    {
        testStation.recordDailyRain(13, 1.0);
        
        //Since we added an invalid month, the month with the lowest
        //rainfall will be by default, january, which is 1.
        assertEquals(testStation.getLowestMonth(), 1);
        assertEquals(testStation.getCountForMonth(13), 0);
    }
    
    
    
    /**
     * Test the getAvgForMonth() method
     * when there is data for that month
     */
    public void testGetAvgForMonth1()
    {
        testStation.recordDailyRain(1, 1.0);
        
        assertEquals(testStation.getAvgForMonth(1), 1.0, 0.001);
    }
    
     /**
     * Test the getAvgForMonth() method
     * when no data exists for that month
     */
    public void testGetAvgForMonth2()
    {
        assertEquals(testStation.getAvgForMonth(1), -1, 0.001);
    }
    
    
    /**
     * Test the getLowestMonth() method
     * when no data has been entered for that month
     */
    public void testGetLowestMonth1()
    {
        //Since no data for any month is entered,
        //the month with the lowest rainfall will 
        //be by default, january, which is 1.
        assertEquals(testStation.getLowestMonth(), 1);
    }
    
    
    /**
    * Test the getLowestMonth() method 
    * when valid data is entered
    */
    public void testGetLowestMonth2()
    {
        testStation.recordDailyRain(1, 1.0);
        testStation.recordDailyRain(2, 3.0);
        
        assertEquals(testStation.getLowestMonth(), 1);
    }
    
    
    /**
    * Test the getLowestMonth() method 
    * when valid data is entered and there 
    * are a lot of monthly data
    */
    public void testGetLowestMonth3()
    {
        testStation.recordDailyRain(1, 2.0);
        testStation.recordDailyRain(1, 7.0);
        testStation.recordDailyRain(2, 3.0);
        testStation.recordDailyRain(2, 4.0);
        testStation.recordDailyRain(3, 5.0);
        testStation.recordDailyRain(3, 1.0);
        
        assertEquals(testStation.getLowestMonth(), 3);
        assertEquals(testStation.getAvgForMonth(3), 3, 0.001);
    }
    
    
    /**
    * Test the getLowestMonth() method 
    * when valid data is entered for 1 month only
    */
    public void testGetLowestMonth4()
    {
        testStation.recordDailyRain(2, 3.0);
        
        assertEquals(2, testStation.getLowestMonth());
    }
  
    
    
    
    
    
    
    
    
    
    
    

}
