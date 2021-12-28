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
 *  The class of fluid that represents Water
 *  Water functions are inherited from its parent class
 *  Water is not dissolevable
 *  
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.11.01
 */
public class Water extends Fluid
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Water object.
     */
    public Water()
    {
        super(Color.cadetBlue, false, 1.0);
    }


    //~ Methods ...............................................................


}
