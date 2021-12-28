import sofia.micro.*;
import sofia.graphics.Color;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

//-------------------------------------------------------------------------
/**
 *  This class creates and defines properties of the steel objects
 *  Steel does not fall, slide or flow therefore
 *  some methods need to be overridden.
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.11.01
 */
public class Steel extends Particle
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Steel object.
     */
    public Steel()
    {
        super(Color.gray, true, 7.87);
    }


    //~ Methods ...............................................................
    /**
     * Override the method in the parent class
     * Steel particles don't fall so this method
     * will always return false
     * 
     * @return it will return false
     */
    public boolean isFalling()
    {
        return false;
    }

    /**
     * Override the method in the parent class
     * Steel particles don't slide of flow so this method
     * will always return false
     * 
     * @return it will return false
     */
    public boolean dodge()
    {
        return false;
    }
    
}
