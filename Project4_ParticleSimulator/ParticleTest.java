import sofia.micro.*;
import sofia.graphics.Color;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

// -------------------------------------------------------------------------
/**
 *  Test the Particle() class
 *  Summarize what your test objectives are.
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.11.01
 */
public class ParticleTest extends TestCase
{
    //~ Fields ................................................................
    private ParticleWorld world;
    private Particle particle;
    
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new ParticleTest test object.
     */
    public ParticleTest()
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
        world = new ParticleWorld(4, 4);
        // Make a grey particle that is green, dissolvable, and 1.0 density
        particle = new Particle(Color.green, true, 1.0);

    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */

    /**
     * Test the constructor of the Particle() class
     */
    public void testParticle()
    {
        assertEquals(true, particle.willDissolve());
        assertEquals(1.0, particle.getDensity(), 0.0001);
    }
    
    /**
     * Test the getDensity() method
     */
    public void testGetDensity()
    {
        assertEquals(1.0, particle.getDensity(), 0.0001);
    }
    
    /**
     * Test the getVelocity() method
     */
    public void testGetVelocity()
    {
        assertEquals(0.0, particle.getVelocity(), 0.0001);
    }
    
    
    /**
     * Test the getAcceleration() method
     */
    public void testGetAcceleration()
    {
        assertEquals(1.0, particle.getAcceleration(), 0.0001);
    }
    
    
    /**
     * Test the getStrength() method
     */
    public void testGetStrength()
    {
        assertEquals(100, particle.getStrength());
    }
    
    
    /**
     * Test the willDissolve() method
     */
    public void testWillDissolve()
    {
        assertEquals(true, particle.willDissolve());
    }
    
    /**
     * Test the weaken() method
     * when particle is at 100 strength
     */
    public void testWeaken1()
    {
        particle.weaken();
        assertEquals(99, particle.getStrength());
    }
    
    /**
     * Test the weaken() method
     * when particle's strength is fully reduced
     */
    public void testWeaken2()
    {
        world.add(particle, 1, 1);
        // Reduce particle strength to 1
        for (int i = 0; i < 100; i++)
        {
            particle.weaken();
        }
           
        // Particle's strength got removed to 0 so
        // it will be removed from the world
        assertEquals(0, particle.getStrength());
        assertEquals(null, particle.getWorld());
        
    }
    
    /**
     * Test the isFalling() method when the particle is not falling
     * by adding particle to the bottom of the map
     */
    public void testIsFalling1()
    {
        world.add(particle, 5, world.getHeight() - 1);
        assertFalse(particle.isFalling());
           
    }
    
    /**
     * Test the isFalling() method when the particle is falling
     */
    public void testIsFalling2()
    {
        world.add(particle, 5, 1);
        assertTrue(particle.isFalling());
    }
    
    /**
     * Test the fall() method when
     * particle hits an object and
     * velocity is reset to 0
     */
    public void testFall1()
    {
        world.add(particle, 2, 0);
        
        // Add a steel particle 2 spaces directly below
        Steel steel = new Steel();
        world.add(steel, 2, 2);
        
        particle.fall();

        // particle will fall on top of the steel particle
        // and its velocity will be 0
        assertEquals(0.0, particle.getVelocity(), 0.0001);
        assertEquals(2, particle.getGridX());
        assertEquals(1, particle.getGridY());
    }
        
    /**
     * Test the fall() method when
     * particle is at the bottom
     */
    public void testFall2()
    {
        world.add(particle, 2, 3);
        particle.fall();
        
        assertEquals(0.0, particle.getVelocity(), 0.0001);
        assertEquals(2, particle.getGridX());
        assertEquals(3, particle.getGridY());
    }
    
    /**
     * Test the fall() method when
     * particle falls and doesn't hit anything
     */
    public void testFall3()
    {
        world.add(particle, 2, 0);
        particle.fall();
        
        // Particle falls down 1 and does not hit anything
        // so velocity does not get reset
        assertEquals(1.0, particle.getVelocity(), 0.0001);
        assertEquals(2, particle.getGridX());
        assertEquals(1, particle.getGridY());
    }
   
    /**
     * Test the swapPlacesIfPossible() method 
     * when it is possible to swap places
     */
    public void testSwapPlacesIfPossible1()
    {
        world.add(particle, 2, 0);
        
        // Add a particle with less density directly below
        Particle swap = new Particle(Color.khaki, true, 0.5);
        world.add(swap, 2, 1);
        
        // Swap places of the 2 particles
        assertTrue(particle.swapPlacesIfPossible(2, 1));     
    }
    
    /**
     * Test the swapPlacesIfPossible() method
     * when it is not possible to swap places
     * due to higher density 
     */
    public void testSwapPlacesIfPossible2()
    {
        world.add(particle, 2, 0);
        
        // Add a particle with more density directly below
        Particle swap = new Particle(Color.khaki, true, 5.0);
        world.add(swap, 2, 1);
        
        // Swap places of the 2 particles, which will fail
        assertFalse(particle.swapPlacesIfPossible(2, 1));    
    }
    
    /**
     * Test the swapPlacesIfPossible() method
     * when attempting to swap out of max x bounds
     */
    public void testSwapPlacesIfPossible3()
    {
        world.add(particle, 3, 3);
        
        // Swap places with an out of bounds position, 
        // which will fail
        assertFalse(particle.swapPlacesIfPossible(
            4, 3));      
    }
    
    /**
     * Test the swapPlacesIfPossible() method
     * when attempting to swap out of min x bounds
     */
    public void testSwapPlacesIfPossible4()
    {
        world.add(particle, 0, 3);
        
        // Swap places with an out of bounds position, 
        // which will fail
        assertFalse(particle.swapPlacesIfPossible(
            -1, 3));      
    }
    
    /**
     * Test the swapPlacesIfPossible() method
     * when attempting to swap out of max y bounds
     */
    public void testSwapPlacesIfPossible5()
    {
        world.add(particle, 3, 3);
        
        // Swap places with an out of bounds position, 
        // which will fail
        assertFalse(particle.swapPlacesIfPossible(
            3, 4));      
    }
    
    /**
     * Test the swapPlacesIfPossible() method
     * when attempting to swap out of min y bounds
     */
    public void testSwapPlacesIfPossible6()
    {
        world.add(particle, 0, 0);
        
        // Swap places with an out of bounds position, 
        // which will fail
        assertFalse(particle.swapPlacesIfPossible(
            0, -1));      
    }
    
}
