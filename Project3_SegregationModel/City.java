import sofia.micro.*;
import sofia.util.Random;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)
//-------------------------------------------------------------------------
/**
 *  Class that creates the City World for this lab.
 *  The world will be created, then random monkeys
 *  and elephants are going to populate it.
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 10.13.2019
 */
public class City extends World
{
    //~ Fields ................................................................

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new City object.
     * Default is going to be a 5x5 world
     * with 30% elephants and 40% monkeys
     */

    public City()
    {
        this(5, 5);
        this.populate(0.3, 0.4, 0.3);
    }

    /**
     * Creates a new City object with user defined 
     * height and width.
     * 
     * @param width shows width of city
     * @param height shows height of city
     */
    public City(int width, int height)
    {
        super(width, height, 24);
    }

    //~ Methods ...............................................................
    /**
     * Method that populates the city with elephants and monkeys.
     * Method also determines the satisfaction threshold
     * 
     * @param percentElephant shows % of elephants in the city
     * @param percentMonkey shows % of monkeys in the city
     * @param satisfaction shows the satisfaction threshold
     */
    public void populate(double percentElephant, 
        double percentMonkey, double satisfaction)
    {
        // Get the dimensions of the world
        // to prevent going out of bounds
        int cityWidth = this.getWidth() - 1;
        int cityHeight = this.getHeight() - 1;

        for (int i = 0; i <= cityWidth; i++)
        {
            for (int j = 0; j <= cityHeight; j++)
            {
                double randNum = Random.generator().nextDouble(0.0, 1.01);
                Agent elephant = new Agent("elephant", satisfaction);
                Agent monkey = new Agent("monkey", satisfaction);

                if (randNum <= percentElephant)
                {
                    this.add(elephant, i, j);
                }

                else if (randNum <= (percentElephant + percentMonkey))
                {
                    this.add(monkey, i, j);
                }  
            }
        }

    }

}
