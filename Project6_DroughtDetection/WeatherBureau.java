// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

import sofia.micro.*;
import java.util.*;
//-------------------------------------------------------------------------
/**
 *  Represents a weather service that keeps track of all the weather stations. 
 *  
 *  Internally, it uses a map to associate weather station IDs 
 *  with weather station objects
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.12.02
 */
public class WeatherBureau 
{
    //~ Fields ................................................................
    private Map<String, WeatherStation> stationList;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WeatherBureau object.
     * This default constructor initializes the internal data of the class.
     */
    public WeatherBureau()
    {
        stationList = new HashMap<String, WeatherStation>();
    }


    //~ Methods ...............................................................
    /**
     * Takes a single string representing a single one-line daily weather 
     * summary for a day at one weather station and store it in the
     * HashMap.
     * 
     * @param summary daily weather summary for one day at one weather station 
     */
    public void recordDailySummary(String summary)
    {
        //New scanner to read data from the String
        Scanner scan = new Scanner(summary);
        
        
        //Store the name of the station
        String stationName = scan.next();
       
        
        //Skips the unecessary information
        scan.next();
        scan.next();
        scan.next();
        
        
        //Extract the date to get the month
        String date = scan.next();
        //Storing the month, day, and year into an array
        String[] dateArray = date.split("/");
        //Getting the month from the array
        int month = Integer.parseInt(dateArray[0]);
        
        
        
        //Get the amount of rainfall for that month
        double amountRainfall = scan.nextDouble();
        
        //If there is rainfall for that month
        if (amountRainfall != -1)
        {
            //Add the rainfall to the WeatherStation and the corresponding month
            if (stationList.get(stationName) != null)
            {
                WeatherStation currentStation = stationList.get(stationName);
                currentStation.recordDailyRain(month, amountRainfall); 
            }
            
            //Create a new station if there is no existing station
            //and passing in the data
            else
            {
                WeatherStation newStation = new WeatherStation(stationName);
                stationList.put(stationName, newStation);
                
                WeatherStation currentStation = stationList.get(stationName);
                currentStation.recordDailyRain(month, amountRainfall);
            }
        }
        
    }

    
    /**
     * Record all of the daily summaries from the input source.
     * 
     * @param input represents an input data source, such as a file containing 
     * a series of daily summary records for one or more weather stations. 
     */
    public void recordDailySummaries(Scanner input) 
    {
        while (input.hasNextLine())
        {
            recordDailySummary(input.nextLine());
        }
    }
    
    
    /**
     * Getter method that returns a corresponding weather station
     * when given an identifier
     * 
     * @param identifier the name of the weather station
     * 
     * @return Return the weather station object for the 
     * given weather station ID. Or null, if the identifier 
     * doesn't match any weather station 
     */
    public WeatherStation getStation(String identifier)
    {
        return stationList.get(identifier);
    }
    
    
    /**
     * Getter method that returns the weather station that has the 
     * lowest average rainfall for the specified month 
     * 
     * @param month the month to look in to find the station
     * 
     * @return the weather station that has the lowest average rainfall 
     * for the specified month (or null, if the weather bureau hasn't recorded 
     * any rainfall daily summaries for any station in the specified month)
     */
    public WeatherStation lowestStation(int month)
    {
        WeatherStation lowestS = null;
        double lowestAmount = 10000;
        
        //Get the station with the lowest amount
        for (WeatherStation current : stationList.values())
        {   
            //Conditions that finds and store the lowest amount while
            //also making sure the rainfall isn't NULL for that station.
            if (current.getAvgForMonth(month) != -1 &&
                current.getAvgForMonth(month) < lowestAmount)
            {
                lowestS = current;
                lowestAmount = current.getAvgForMonth(month);
            }  
        }
        return lowestS;
    }
    
    /**
     * Getter method that returns the weather station that has 
     * the lowest average rainfall recorded for any month 
     * (or null, if the weather bureau hasn't recorded any 
     * rainfall daily summaries for any station for any month).
     * 
     * @return the weather that has the lowest average rainfall recorded.
     */
    public WeatherStation lowestStation()
    {
        WeatherStation lowestS = null;
        double lowestAmount = 10000;
        
        //Find the station with the lowest rainfall amount for each month 
        //(From january to december so 1 to 12)
        for (int i = 1; i <= 12; i++)
        {
            //Store the lowest station for that month in a variable
            WeatherStation current = lowestStation(i);

            //Make sures the station exist and compare it 
            //with the current lowest average
            if (current != null && current.getAvgForMonth(i) < lowestAmount)
            {
                lowestS = current;
                lowestAmount = current.getAvgForMonth(i);
            }  
            
        }
        
        return lowestS;
    }
}