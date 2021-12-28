// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

import sofia.micro.*;
import java.util.*;
import student.IOHelper;
import sofia.graphics.*; 

//-------------------------------------------------------------------------
/**
 *  The purpose of this class to parse weather data and then 
 *  display onto screen the weather station with the lowest rainfall
 *  
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.12.02
 */
public class RainfallAnalyzer extends World
{
    //~ Fields ................................................................
    private WeatherBureau newBureau;
    private WeatherStation lowestS;
    private TextShape text;
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * The default constructor creates a Scanner that reads from 
     * a website and calls the first constructor to perform the 
     * remainder of the initialization
     */
    public RainfallAnalyzer()
    {
        this(IOHelper.createScannerForURL(
            "http://courses.cs.vt.edu/~cs1114/Kenya-2014-2016.txt"));
    }
    
    /**
     * Initializes the world and find the weather station 
     * with the lowest average monthly rainfall. a
     * This WeatherStation is then added and displayed
     * in the world
     * 
     * @param input the Scanner object containing the data to 
     * analyze.
     */
    public RainfallAnalyzer(Scanner input)
    {
        //Initialize world
        super(8, 5, 72);
        
        //Variables to store data to display
        newBureau = new WeatherBureau();
        newBureau.recordDailySummaries(input);
        lowestS = newBureau.lowestStation();
        
        //If no station exists, it wlll display "No Data"
        if (lowestS == null)
        {
            text = new TextShape("no data");
            
            //Format the text and display it to 
            //the world
            text.setTypeSize(40);
            text.setColor(Color.black);
            
            this.add(text, 3, 2);
        }
             
        //Display the station with the lowest monthly rainfall
        else
        {
            String stationID = lowestS.getId();
            int lowestMonth = lowestS.getLowestMonth();
            double averageRainfall = lowestS.getAvgForMonth(lowestMonth);
            
            //Format the text and display it to 
            //the world
            String toDisplay = String.format(
                "%s: %d: %.2f", stationID, lowestMonth, averageRainfall);
            
            text = new TextShape(toDisplay);
            text.setTypeSize(40);
            text.setColor(Color.black);
            
            this.add(text, 3, 2);
        }

    }


    //~ Methods ...............................................................
    
    /**
     * This getter returns the weather bureau created inside this object 
     * to hold all the weather data.
     * 
     * @return the weather bureau that holds all the weather data.
     */
    public WeatherBureau getBureau()
    {
        return newBureau;
    }
    
    
    /**
     * This getter returns the text shape created in the constructor to 
     * display the weather station information for the station with 
     * the lowest average rainfall.
     * 
     * @return the textShape object that has information for the station 
     * with the lowest average rainfall.
     */
    public TextShape getText()
    {
        return text;
    }
    
    
    /**
     * This getter returns the weather station with the lowest rainfall that
     * is currently displayed 
     * 
     * @return the weatherStation calculated in the constructor.
     */
    public WeatherStation getStation()
    {
        return lowestS;   
    }
}
