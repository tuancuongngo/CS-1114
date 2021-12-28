import sofia.micro.*;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

// -------------------------------------------------------------------------
/**
 *  Test the Acid() class
 *  Test the various situations where acid dissolves
 *  or doesn't dissolve.
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.11.01
 */
public class AcidTest extends TestCase
{
    //~ Fields ................................................................
    private ParticleWorld world;
    private Acid acid;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new AcidTest test object.
     */
    public AcidTest()
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
        acid = new Acid();
    }


    // ----------------------------------------------------------
    /**
     * Test the dissolveIfPossible() method when
     * acid can weaken particles above
     */
    public void testDissolveIfPossible1()
    {
        world.add(acid, 2, 2);
        
        // Add a sand particle above acid
        Sand sand0 = new Sand();
        world.add(sand0, 2, 1);

        acid.dissolveIfPossible(2, 1);
        
        // Both strengths weakened by 1
        assertEquals(99, acid.getStrength());
        assertEquals(99, sand0.getStrength());
        
    }
    
    
    /**
     * Test the dissolveIfPossible() method when
     * acid weakens particle below
     */
    public void testDissolveIfPossible2()
    {
        world.add(acid, 2, 2);
        
        Sand sand0 = new Sand();
        world.add(sand0, 2, 3);
      
        acid.dissolveIfPossible(2, 3);
        
        // The strength of sand particle weakened by 1
        assertEquals(99, sand0.getStrength());
        
        // The strength of acid weakened by 1
        assertEquals(99, acid.getStrength());
        
    }
    
    
    /**
     * Test the dissolveIfPossible() method when
     * acid weakens particle left
     */
    public void testDissolveIfPossible3()
    {
        world.add(acid, 2, 2);
        
        Sand sand0 = new Sand();
        world.add(sand0, 1, 2);
      
        acid.dissolveIfPossible(1, 2);
        
        // The strength of sand particle weakened by 1
        assertEquals(99, sand0.getStrength());
        
        // The strength of acid weakened by 1
        assertEquals(99, acid.getStrength());
        
    }
    
    
    /**
     * Test the dissolveIfPossible() method when
     * acid weakens particle right
     */
    public void testDissolveIfPossible4()
    {
        world.add(acid, 2, 2);
        
        Sand sand0 = new Sand();
        world.add(sand0, 3, 2);
      
        acid.dissolveIfPossible(3, 2);
        
        // The strength of sand particle weakened by 1
        assertEquals(99, sand0.getStrength());
        
        // The strength of acid weakened by 1
        assertEquals(99, acid.getStrength());
        
    }
    
    /**
     * Test the dissolveIfPossible() method when
     * Acid is no longer present
     * In other words, when this.getWorld() == null
     */
    public void testDissolveIfPossible5()
    {
        // Add and remove acid
        world.add(acid, 2, 2);
        world.remove(acid);
        
        Sand sand0 = new Sand();
        world.add(sand0, 3, 2);
      
        // Acid attempt to dissolve sand but it won't work
        acid.dissolveIfPossible(3, 2);
        
        assertEquals(100, sand0.getStrength());
        assertEquals(null, acid.getWorld());
    }
    
    /**
     * Test the dissolveIfPossible() method when
     * Acid strength is 0
     */
    public void testDissolveIfPossible6()
    {
        world.add(acid, 0, 3);
        // Weakens the strength of acid to 0
        for (int i = 0; i < 100; i++)
        {
            acid.weaken();
        }
        
        Sand sand0 = new Sand();
        world.add(sand0, 1, 3);
        acid.dissolveIfPossible(1, 3);
        
        // Acid will not be able to dissolve sand
        assertEquals(100, sand0.getStrength());
        
        // Acid is removed from world since it's strength is 0
        assertEquals(null, acid.getWorld());
        assertEquals(0, acid.getStrength());
        
    }
    
    /**
     * Test the dissolveIfPossible() method when
     * Acid cannot dissolve water particles
     */
    public void testDissolveIfPossible7()
    {
        world.add(acid, 0, 3);
        
        Water water0 = new Water();
        world.add(water0, 1, 3);
        acid.dissolveIfPossible(1, 3);
        
        // Acid will not be able to dissolve water
        assertEquals(100, water0.getStrength());
        
        // Acid still has full strength
        assertEquals(100, acid.getStrength());
        
    }
    
    
    /**
     * Test the dissolveIfPossible() method when
     * there is no surrounding objects
     */
    public void testDissolveIfPossible8()
    {
        world.add(acid, 0, 3);
        
        acid.dissolveIfPossible(1, 3);
        
        // Acid still has full strength
        assertEquals(100, acid.getStrength());
    }
    
    /**
     * Test the dissolveIfPossible() method
     * when the object to dissolve is out of bounds
     * in both x and y
     */
    public void testDissolveIfPossible9()
    {
        world.add(acid, 3, 3);
        
        // Add sand out of bounds
        Sand sand0 = new Sand();
        world.add(sand0, 4, 4);
        
        acid.dissolveIfPossible(4, 4);
        
        // Strengths are the same as before
        assertEquals(100, acid.getStrength());
        assertEquals(100, sand0.getStrength());
          
    }
    
    /**
     * Test the dissolveIfPossible() method
     * when the object to dissolve cannot be dissolve
     * and acid runs out of strength anyways
     */
    public void testDissolveIfPossible10()
    {
        world.add(acid, 2, 2);
        // Weakens acid strength to 0
        for (int i = 0; i < 100; i++)
        {
            acid.weaken();
        }
        
        // Add sand Left
        Water water0 = new Water();
        world.add(water0, 1, 2);
        
        acid.dissolveIfPossible(1, 2);
        
        // Acid is removed from world
        assertEquals(0, acid.getStrength());
        assertEquals(null, acid.getWorld());
        
        // water is still in orginial condition
        assertEquals(100, water0.getStrength());
    }
    
    /**
     * Test the dissolveIfPossible() method
     * when a sand particle is removed from the world
     * when it's strength runs out
     */
    public void testDissolveIfPossible11()
    {
        world.add(acid, 2, 2);
        
        // Add sand Left and weakens its strength = 1
        Sand sand0 = new Sand();
        world.add(sand0, 1, 2);
        
        for (int i = 0; i < 99; i++)
        {
            sand0.weaken();
        }
        
        acid.dissolveIfPossible(1, 2);
        
        // Sand is removed from world
        assertEquals(0, sand0.getStrength());
        assertEquals(null, sand0.getWorld());
        
        // Acid strength reduced by 1
        assertEquals(99, acid.getStrength());
        
    }
    
    /**
     * Test he dissolveIfPossible() method
     * when the object to dissolve is out of bounds
     * in x and not y
     */
    public void testDissolveIfPossible12()
    {
        world.add(acid, 3, 3);
        
        // Add sand out of bounds
        Sand sand0 = new Sand();
        world.add(sand0, 4, 3);
        
        acid.dissolveIfPossible(4, 3);
        
        // Strengths are the same
        assertEquals(100, acid.getStrength());
        assertEquals(100, sand0.getStrength());
        
    }
    
    /**
     * Test he dissolveIfPossible() method
     * when the object to dissolve is out of bounds
     * in y and not x
     */
    public void testDissolveIfPossible13()
    {
    
        world.add(acid, 3, 3);
        
        // Add sand out of bounds
        Sand sand0 = new Sand();
        world.add(sand0, 3, 4);
        
        acid.dissolveIfPossible(3, 4);
        
        // Strengths are the same
        assertEquals(100, acid.getStrength());
        assertEquals(100, sand0.getStrength());
        
    }
    
    /**
     * Test he dissolveIfPossible() method
     * when there is no partice to dissolve and acid 
     * strength is 0
     */
    public void testDissolveIfPossible14()
    {
        world.add(acid, 2, 2);
        
        // Weakens acid strength to 0
        for (int i = 0; i < 100; i++)
        {
            acid.weaken();
        }
        
        // Attempt to dissolve a non-existent object
        acid.dissolveIfPossible(1, 2);
        
        assertEquals(null, acid.getWorld());
    }
     
    /**
     * Test the dissolveIfPossible() method
     * when object dissolves 3 particles but run
     * out of strength to dissolve the last one
     */
    public void testDissolveIfPossible15()
    {
        world.add(acid, 2, 2);
        // Weakens acid strength to 3 so it
        // can only dissolve 3 sands
        for (int i = 0; i < 97; i++)
        {
            acid.weaken();
        }
        
        // Add sand Left
        Sand sand0 = new Sand();
        world.add(sand0, 1, 2);
        // Add sand Right
        Sand sand1 = new Sand();
        world.add(sand1, 3, 2);
        // Add sand Above
        Sand sand2 = new Sand();
        world.add(sand2, 2, 1);
        // Add sand Below
        Sand sand3 = new Sand();
        world.add(sand3, 2, 3);
        
        acid.act();
        
        // Acid is removed because it's strength got reduced to 0
        assertEquals(null, acid.getWorld());
        
        // Sand particle below didn't get reduced strength
        // because acid is gone
        assertEquals(100, sand3.getStrength());
        
    }
    
    /**
     * Test the act() method when acid weakens sand particles
     * above
     */
    public void testAct1()
    {
        world.add(acid, 2, 2);
        
        // Surrounds acid with sand particles above
        Sand sand0 = new Sand();
        world.add(sand0, 2, 1);

        acid.act();
        
        // The strength of sand particle weakened by 1
        assertEquals(99, sand0.getStrength());
        
        // The strength of acid weakened by 1
        assertEquals(99, acid.getStrength());
    }
    
    /**
     * Test the act() method when acid weakens sand particles
     * below
     */
    public void testAct2()
    {
        world.add(acid, 2, 2);
        
        Sand sand0 = new Sand();
        world.add(sand0, 2, 3);
      
        acid.act();
        
        // The strength of sand particle weakened by 1
        assertEquals(99, sand0.getStrength());
        
        // The strength of acid weakened by 1
        assertEquals(99, acid.getStrength());
    }
        
    /**
     * Test the act() method when acid weakens sand particles
     * left
     */
    public void testAct3()
    {
        world.add(acid, 2, 2);
        
        Sand sand0 = new Sand();
        world.add(sand0, 1, 2);
      
        acid.act();
        
        // The strength of sand particle weakened by 1
        assertEquals(99, sand0.getStrength());
        
        // The strength of acid weakened by 1
        assertEquals(99, acid.getStrength());
    }
    
    /**
     * Test the act() method when acid weakens sand particles
     * right
     */
    public void testAct4()
    {
        world.add(acid, 2, 2);
        
        Sand sand0 = new Sand();
        world.add(sand0, 3, 2);
      
        acid.act();
        
        // The strength of sand particle weakened by 1
        assertEquals(99, sand0.getStrength());
        
        // The strength of acid weakened by 1
        assertEquals(99, acid.getStrength());
    }
        
    /**
     * Test the act() method when acid is no longer in the world
     */
    public void testAct5()
    {
        // Add and remove acid
        world.add(acid, 2, 2);
        world.remove(acid);
        
        Sand sand0 = new Sand();
        world.add(sand0, 3, 2);
      
        // Acid attempt to dissolve sand but it won't work
        acid.act();
        
        assertEquals(100, sand0.getStrength());
        assertEquals(null, acid.getWorld());

    }
        
    /**
     * Test the act() method when acid strength is 0
     */
    public void testAct6()
    {
        world.add(acid, 0, 3);
        // Weakens the strength of acid to 0
        for (int i = 0; i < 100; i++)
        {
            acid.weaken();
        }
        
        assertEquals(0, acid.getStrength());
        
        Sand sand0 = new Sand();
        world.add(sand0, 1, 3);
        acid.act();
        
        // Acid will not be able to dissolve sand
        assertEquals(100, sand0.getStrength());
        
        // Acid is removed from world since it's strength is 0
        assertEquals(null, acid.getWorld());
        
    }
        
    /**
     * Test the act() method when all the particle finishes dissolving 
     * a particle and goes into free fall
     */
    public void testAct7()
    {
        world.add(acid, 2, 2);
        
        // Add sand particle 1 space belows acid
        Sand sand0 = new Sand();
        world.add(sand0, 2, 3);
        // Reduce sand's strength to 1
        for (int i = 0; i < 99; i++)
        {
            sand0.weaken();
        }
        
        acid.act();
        
        // Sand gets removed
        assertEquals(null, sand0.getWorld());
        
        // Acid falls to the bottom
        assertEquals(2, acid.getGridX());
        assertEquals(3, acid.getGridY());
        
    }
}
