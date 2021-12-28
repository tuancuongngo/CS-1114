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
 *  This class represents the common properties 
 *  and behaviors that all particles share.
 *  
 *  The basic attributes are velocity, acceleration, 
 *  strength, density, dissolvability, and color.
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.11.01
 */
public class Particle extends ParticleBase
{
    //~ Fields ................................................................
    private boolean dissolve;
    private double particleDensity;
    private double velocity;
    private double acceleration;
    private int strength;

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new Particle object.
     * @param color the object's color
     * @param willDissolve object's dissolvability
     * @param density the object's density
     */
    public Particle(Color color, boolean willDissolve, double density)
    {
        super(color);
        dissolve = willDissolve;
        particleDensity = density;

        // Initializes the particle's attributes
        velocity = 0.0;
        acceleration = 1.0;
        strength = 100;
    }

    //~ Methods ...............................................................
    /**
     * A getter method that returns this particle's density 
     * (a floating-point value).
     * 
     * @return particleDensity the particle's density
     */
    public double getDensity()
    {
        return particleDensity;
    }

    /**
     * A getter method that returns this particle's downward 
     * velocity (a floating-point value).
     * 
     * @return velocity the particle's velocity
     */
    public double getVelocity()
    {
        return velocity;
    }

    /**
     * A getter method that returns this particle's downward 
     * acceleration (a floating-point value).
     * 
     * @return acceleration the particle's acceleration
     */
    public double getAcceleration()
    {
        return acceleration;   
    }   

    /**
     * A getter method that returns this particle's strength 
     * (an integer value).
     * 
     * @return strength the particle's strength
     */
    public int getStrength()
    {
        return strength;
    }

    /**
     * A getter method that returns the boolean value indicating 
     * whether this particle can be dissolved (i.e., is it reactive).
     * 
     * @return dissolve the particle's dissolvability
     */
    public boolean willDissolve()
    {
        return dissolve;
    }

    /**
     * This method reduces the particle's strength by 1. 
     * If the strength becomes zero, this method will 
     * remove this particle from the world.
     */
    public void weaken()
    {
        strength = strength - 1;
        
        if (strength == 0)
        {
            this.remove();
        }
    }

    
    /**
     * Returns a boolean value indicating 
     * whether this particle is in free-fall or not.
     * 
     * @return isFalling to indicate whether the particle is falling
     */
    public boolean isFalling()
    { 
        int particleX = this.getGridX();
        int particleY = this.getGridY();
        
        // Finding the boundaries
        // by finding the maximum y value
        int worldHeight = this.getWorld().getHeight() - 1;
        int maxY = Math.min(worldHeight, particleY + 1);

        // The particle is falling if it is still in bounds
        // and there is no object immidiately underneath it
        return (particleY < maxY) && 
            (this.getWorld().getOneObjectAt(particleX, particleY + 1) == null);
    }

    
    /**
     * Add the particle's current acceleration to 
     * its velocity, to update its velocity and
     * implements the behavior of falling
     * 
     */
    public void fall()
    {
        // Updates velocity
        velocity += acceleration;

        for (int i = 0; i < (int)velocity; i++)
        {
            // Making particle fall by increasing 
            // the particle's current y position by 1
            // if it's still in bounds
            int particleY = this.getGridY();            
            
            if (particleY + 1 <= this.
                    getWorld().getHeight() - 1)
            {
                this.setGridY(particleY + 1);
            }
            
            // Resetting the velocity if particle hits an object
            if (!isFalling())
            {
                velocity = 0;
                break;
            }
        }

    }

    
    /**
     * This method changes place with another particle at the 
     * specified location, if appropriate, and returns a 
     * boolean value indicating whether this action succeeded.
     * 
     * @param x the x position to swap
     * @param y the y position to swap
     * @return a boolean indicating if it is possible to swap places
     */
    public boolean swapPlacesIfPossible(int x, int y)
    {
        int width = this.getWorld().getWidth() - 1;
        int height = this.getWorld().getHeight() - 1;

        // Check to see if desired place to swap is out of bounds
        if ((x >= 0 && x <= width) && (y >= 0 && y <= height))
        {
            //Obtain particle if it's in bounds
            Particle otherParticle = 
                this.getWorld().getOneObjectAt(x, y, Particle.class);
            
            // If there is a open space and no partticle, 
            // swap to that position
            if (otherParticle == null)
            {
                this.setGridX(x);
                this.setGridY(y);
                return true;
            }
                
            // If there is an object,
            // compare density to determine if it's possible to swap places
            if (otherParticle.getDensity() < this.getDensity())
            {
                // Get the particle's current location to swap the other
                // particle to this location
                int xOrig = this.getGridX();
                int yOrig = this.getGridY();

                // Swap the particle to the other particle's location
                this.setGridLocation(x, y);

                // Swap the other particle to the originial particle's
                // location
                otherParticle.setGridLocation(xOrig, yOrig);

                return true;
            }
        }

        // If the particles cannot be swapped 
        return false;
    }

    
    /**
     * This method implements the alternative motions of sliding
     * and flowing to another position under some circumstances
     * 
     * @return A boolean value indicating whether or not the particle moved
     */
    public boolean dodge()
    {
        // Get the current location of the particle
        int x = this.getGridX();
        int y = this.getGridY();

        // Check to see if particle can swap places with what is below it
        // or with what is below and to the left cell
        // or with what is below and to the right cell
        return swapPlacesIfPossible(x, y + 1) 
            || swapPlacesIfPossible(x - 1, y + 1) 
                || swapPlacesIfPossible(x + 1, y + 1);
    }
    

    /**
     * Executes one "turn" for this particle. 
     * Each turn, the particle should fall, if it is falling, 
     * or dodge otherwise.
     */
    public void act()
    {
        if (this.isFalling())
        {
            this.fall();
        }

        else
        {
            this.dodge();
        }
    }

    
    
        
        
        
        
        
        
        
        
    
    
    
    
    
    
}
