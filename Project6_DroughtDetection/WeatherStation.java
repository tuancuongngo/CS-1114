// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  This class represents the basic statistics collected 
 *  by one weather observation station.
 *  
 *  It will use arrays to hold rain data for 12 months.
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.12.02
 */
public class WeatherStation
{
    //~ Fields ................................................................
    private String stationID;
    private int[] months;
    private double[] rainfallAmount;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * This constructor initializes the internal data for the weather station.
     * 
     * @param stationID the ID of the weather station.
     */
    public WeatherStation(String stationID)
    {
        this.stationID = stationID;
        months = new int[12];
        rainfallAmount = new double[12];
    }
    
    //~ Methods ...............................................................
    
    /**
     * Getter method to get the station ID
     * 
     * @return the weather station ID for this weather station.
     */
    public String getId()
    {
        return stationID;
    }
    
    /**
     * Record the information from one daily summary line in a data file
     * Adds the rainfall to the month and increases the number
     * of days recorded by 1
     * 
     * @param month the month from 1-12
     * @param rainfall the amount of rainfall
     */
    public void recordDailyRain(int month, double rainfall)
    {
        if (month <= 12 && month > 0 && rainfall >= 0)
        {
            months[month - 1] += 1;
            rainfallAmount[month - 1] += rainfall;
        }
    }
    
    /**
     * Getter method that returns the number of daily rainfall values 
     * that have been recorded for the specified month.
     * Will return zero when no values have been recorded for 
     * the specified month.
     * 
     * @param month the month that the user is looking to get data from
     * @return the number of daily rainfall values for that month
     */
    public int getCountForMonth(int month)
    {
        if (month > 0 && month <= 12)
        {
            return months[month - 1];
        }
        
        return 0;
    }
    
    /**
     * Getter method that returns the average daily rainfall for that 
     * specified month.
     * 
     * This is the total rainfall across all reported 
     * daily values for that month, divided by the 
     * number of daily values that have been recorded for that month.
     * Will return -1 if no rainfall amounts have been 
     * recorded for the specified month.
     * 
     * @param month the specified month to get the average rainfall for
     * @return the average daily rainfall for the specified month
     */
    public double getAvgForMonth(int month)
    {
        // If no rainfall amounts have been recorded
        if (months[month - 1] == 0)
        {
            return -1;
        }
        
        //Add rainfall up and divide by getCountForMonth();
        double rainAverage = rainfallAmount[month - 1] / months[month - 1];
        
        return rainAverage;
    }
    
    
    /**
     * Getter method that returns the number of the month that had the lowest 
     * average rainfall recorded across all months
     * 
     * If multiple months have the same lowest rainfall average, it will return 
     * the earliest one (the lowest month number). 
     * If no rainfall records have been entered for any month, it will 
     * return the earliest month as well (1).
     * 
     * @return the number of the month indicating the month that had the lowest 
     * average rainfall recorded at this station.
     */
    public int getLowestMonth()
    {

        int lowestMonth = 1;
        double lowestRainfall = 10000;
        
        // Iterate through the rainfallAmount array to find the month with
        // the lowest rainfall.
        for (int i = 1; i <= 12; i++)
        {
            //NOTE: if no rainfall data has been entered, this if 
            //statement will not run and lowestMonth = 1 will be 
            //returned
            if (getAvgForMonth(i) > -1 && getAvgForMonth(i) < lowestRainfall)
            {
                lowestRainfall = getAvgForMonth(i);
                lowestMonth = i;
            }
        }
        
        return lowestMonth;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
