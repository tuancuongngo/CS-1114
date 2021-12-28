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
 *  Subclass of Particle() called Fluid 
 *  that represents liquid particles. 
 *  The fluids are Acid and Water.
 *  
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.11.01
 */
public class Fluid extends Particle
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Fluid object.
     * 
     * @param color the object's color
     * @param willDissolve object's dissolvability
     * @param density the object's density
     */
    public Fluid(Color color, boolean willDissolve, double density)
    {
        super(color, willDissolve, density);
    }


    //~ Methods ...............................................................
    
    /**
     * This method implements the alternative motions of sliding
     * and flowing to another position under some circumstances
     * 
     * @return A boolean value indicating whether or not the particle moved
     */
    public boolean dodge()
    {
        // Particle will only dodge if it is not falling
        if (!isFalling())
        {
            int x = this.getGridX();
            int y = this.getGridY();
            return super.dodge() || 
                swapPlacesIfPossible(x - 1, y) 
                    || swapPlacesIfPossible(x + 1, y);
        }

        return false;
        
    }

}
