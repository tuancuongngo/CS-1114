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
 *  Subclass of Fluid that represents liquid acid. 
 *  This fluid will dissolve other particles and
 *  weaken their strengths
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.11.01
 */
public class Acid extends Fluid
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Acid object.
     */
    public Acid()
    {
        super(Color.green, false, 1.0);
    }


    //~ Methods ...............................................................
    
    /**
     * This method dissolves another particle 
     * at the specified location, if appropriate. 
     * 
     * @param x the x position to check
     * @param y the y position to check
     */
    public void dissolveIfPossible(int x, int y)
    {
        // Initializes the object to store the adjacent particle
        Particle otherParticle = null;
        
        // Checks if acid is still present
        if (this.getWorld() != null)
        {
            // Check the bounds
            // Check to see if desired place to dissolve is out of bounds
            int width = this.getWorld().getWidth() - 1;
            int height = this.getWorld().getHeight() - 1;
        
            // Get the object if it's inbounds
            if (x <= width && y <= height)
            {
                otherParticle = 
                    this.getWorld().getOneObjectAt(x, y, Particle.class);
                
                // Check if there is object in the position,
                // and whether or not it will dissolve
                if (otherParticle != null && 
                    otherParticle.willDissolve())
                {
                    // If particle can dissolve the particle in the
                    // specified position, both particles' strengths will weaken
                    otherParticle.weaken();
                    this.weaken();
                }
   
            }
        
        }
        
    
    }
    
    
    
    /**
     * This method tells the acid to dissolve adjacent particles
     */
    public void act()
    {
        // Check if particle still exists
        if (this.getWorld() != null)
        {
            int x = this.getGridX();
            int y = this.getGridY();
        
            // dissolves adjacent particles to the left
            this.dissolveIfPossible(x - 1, y);
            // dissolves adjacent particles to the right
            this.dissolveIfPossible(x + 1, y);
            // dissolves adjacent particles above
            this.dissolveIfPossible(x, y - 1);
            // dissolves adjacent particles below
            this.dissolveIfPossible(x, y + 1);
                
            // After dissolving, if the acid is still present,
            // it will either fall or dodge
            if (this.getWorld() != null)
            {
                super.act();
            }
        
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
