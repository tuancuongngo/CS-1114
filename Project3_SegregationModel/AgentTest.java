import sofia.micro.*;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

// -------------------------------------------------------------------------
/**
 *  Test the various methods in the Agent class
 *  This will test the methods in the class of animals
 *  to see if they function correctly
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 10.13.2019
 */
public class AgentTest extends TestCase
{
    //~ Fields ................................................................
    private City testCity;
    private Agent elephant1;
    private Agent elephant2;
    private Agent monkey1;
    private Agent monkey2;
    private Agent monkey3;

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new AgentTest test object.
     */
    public AgentTest()
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
        testCity = new City(3, 3);
        elephant1 = new Agent("elephant", 0.3);
        elephant2 = new Agent("elephant", 0.3);
        monkey1 = new Agent("monkey", 0.3);
        monkey2 = new Agent("monkey", 0.3);
        monkey3 = new Agent("monkey", 0.3);
    }

    // ----------------------------------------------------------
    
    /**
     * Test the default Agent() constructor
     */
    public void testAgent()
    {
        Agent bigElephant = new Agent();
        testCity.add(bigElephant, 0, 0);
        
        // There should be an elephant named
        // bigElephant at (0, 0) with a satisfaction
        // threshold of 0.3 or 30%
        assertEquals("elephant", 
            testCity.getOneObjectAt(0, 0, Agent.class).getKind());
        assertEquals(0, bigElephant.getGridX());
        assertEquals(0, bigElephant.getGridY());
        assertEquals(0.3, 
            testCity.getOneObjectAt(0, 0, Agent.class).getThreshold(),
                0.0001);
    }
    
    /**
     * Test the Agent(String animalName, double satisfactionPercent)
     * method
     */
    public void testAgent1()
    {
        monkey1 = new Agent("monkey", 0.5);
        testCity.add(monkey1, 0, 0);
        
        // There should be an monkey named
        // monkey1 at (0, 0) with a satisfaction
        // threshold of 0.5 or 50%
        assertEquals("monkey", 
            testCity.getOneObjectAt(0, 0, Agent.class).getKind());
        assertEquals(0, monkey1.getGridX());
        assertEquals(0, monkey1.getGridY());
        assertEquals(0.5, 
            testCity.getOneObjectAt(0, 0, Agent.class).getThreshold(),
                0.0001);
        
        
        
    }
    
    /**
     * Test the getKind() method
     */
    public void testGetKind()
    {
        monkey1 = new Agent("monkey", 0.5);
        elephant1 = new Agent("elephant", 0.9);
        
        testCity.add(monkey1, 0, 0);
        testCity.add(elephant1, 1, 1);
        
        // There should be a monkey Agent at (0, 0)
        assertEquals("monkey", 
            testCity.getOneObjectAt(0, 0, Agent.class).getKind());
           
        // There should be a elephant Agent at (1, 1)
        assertEquals("elephant", 
            testCity.getOneObjectAt(1, 1, Agent.class).getKind());

    }
    
    /**
     * Test the getThreshold() method
     */
    public void testGetThreshold()
    {
        monkey1 = new Agent("monkey", 0.5);
        elephant1 = new Agent("elephant", 0.9);
        
        testCity.add(monkey1, 0, 0);
        testCity.add(elephant1, 1, 1);
        
        // monkey1 threshold should be 0.5
        assertEquals(0.5, 
            testCity.getOneObjectAt(0, 0, Agent.class).getThreshold(),
                0.0001);
        
        // elephant1 threshold should be 0.9        
        assertEquals(0.9, 
            testCity.getOneObjectAt(1, 1, Agent.class).getThreshold(),
                0.0001);
        
        
        
    }
    
    /**
     * Test the isSameKindAs() method
     */
    public void testIsSameKindAs()
    {
        monkey1 = new Agent("monkey", 0.5);
        elephant1 = new Agent("elephant", 0.9);
        elephant2 = new Agent("elephant", 0.9);
                
        // elephant is not the same kind as monkey
        assertFalse(monkey1.isSameKindAs(elephant1));
        // both Agents are elephants
        assertTrue(elephant2.isSameKindAs(elephant1));
    }
    
    /**
     * Test the isSatisfiedAt() method
     * in a scenario where an elephant is in
     * the UPPER LEFT CORNER and a monkey is
     * on the LEFT EDGE
     */
    public void testIsSatisfiedAt1()
    {

        testCity.add(elephant1, 0, 0);
        testCity.add(elephant2, 1, 0);
        testCity.add(monkey1, 0, 1);

        // Elephants should be satisfied since they are surrounded
        // by 50 % elephant
        assertTrue(elephant1.isSatisfiedAt(0, 0));  
        assertTrue(elephant2.isSatisfiedAt(1, 0));

        // Monkey is not satisfied because it surrounded by
        // 100% elephants
        assertFalse(monkey1.isSatisfiedAt(0, 1));

    }

    
    /**
     * Test the isSatisfiedAt() method
     * in a scenario where an elephant is in
     * the MIDDLE of the map; another elephant is in 
     * the TOP EDGE of the map; a monkey is in the 
     * BOTTOM EDGE of the map; and another monkey is
     * on the RIGHT EDGE of the map.
     */
    public void testIsSatisfiedAt2()
    {

        testCity.add(elephant1, 1, 1);
        testCity.add(elephant2, 1, 0);
        testCity.add(monkey1, 0, 1);
        testCity.add(monkey2, 2, 1);
        testCity.add(monkey3, 1, 2);

        // elephant1 should not be satisfied
        assertFalse(elephant1.isSatisfiedAt(1, 1));  
        
        // The rest of the agents are satisfied
        assertTrue(elephant2.isSatisfiedAt(1, 0));
        assertTrue(monkey1.isSatisfiedAt(0, 1));
        assertTrue(monkey2.isSatisfiedAt(2, 1));
        assertTrue(monkey3.isSatisfiedAt(1, 2));

    }

    
    /**
     * Test the isSatisfiedAt() method
     * in a scenario where an elephant is BY ITSELF
     */
    public void testIsSatisfiedAt3()
    {
        testCity.add(elephant1, 1, 1);
        // Elephant is surrounded by 0% of its kind
        // so it is not satisfied
        assertFalse(elephant1.isSatisfiedAt(1, 1));  
    }

    
    /**
     * Test the isSatisfied() method
     */
    public void testIsSatisfied()
    {
        testCity.add(elephant1, 1, 1);
        testCity.add(elephant2, 1, 0);
        testCity.add(monkey1, 0, 1);
        testCity.add(monkey2, 2, 1);
        testCity.add(monkey3, 1, 2);

        // elephant1 should not be satisfied
        assertFalse(elephant1.isSatisfied());  
        
        // The rest of the agents are satisfied
        assertTrue(elephant2.isSatisfied());
        assertTrue(monkey1.isSatisfied());
        assertTrue(monkey2.isSatisfied());
        assertTrue(monkey3.isSatisfied());
    }

    
    /**
     * Test the relocate() method
     * where 2 agents are unhappy
     */
    public void testRelocate()
    {
        // Add the satisfied elephant
        testCity.add(elephant2, 1, 0);

        // Add the satisfied monkeys
        testCity.add(monkey1, 2, 1);
        testCity.add(monkey2, 2, 2);

        // Add the unsatisfied elephant
        testCity.add(elephant1, 1, 1);

        // Add the unsatisfied monkey
        testCity.add(monkey3, 0, 1);

        // Relocating the unsatisfied Monkey and Elephant
        monkey3.relocate();
        elephant1.relocate();

        // Monkey will move to the next space where it's 
        // satisfied, which will be at (1, 2)
        assertEquals(1, monkey3.getGridX());
        assertEquals(2, monkey3.getGridY());

        // Elephant will move to the next space where it's 
        // satisfied, which will be at (0, 1)
        assertEquals(0, elephant1.getGridX());
        assertEquals(1, elephant1.getGridY());

        // Testing relocate on the satisfied animals
        monkey1.relocate();
        monkey2.relocate();
        elephant2.relocate();

        // The other animals will remain in their original position
        // because they are already satisfied
        assertEquals(2, monkey1.getGridX());
        assertEquals(1, monkey1.getGridY());

        assertEquals(2, monkey2.getGridX());
        assertEquals(2, monkey2.getGridY());

        assertEquals(1, elephant2.getGridX());
        assertEquals(0, elephant2.getGridY());

    }

    
    /**
     * Test the act() method
     * After executing this method for each Agent,
     * everyone will be satisfied.
     */
    public void testAct()
    {
        testCity.add(elephant1, 0, 0);
        testCity.add(elephant2, 1, 0);
        testCity.add(monkey1, 2, 0);
        testCity.add(monkey2, 0, 1);
        testCity.add(monkey3, 2, 2);

        // Calling the act() method on all Agents
        elephant1.act();
        elephant2.act();
        monkey1.act();
        monkey2.act();
        monkey3.act();

        // Everyone should be satisfied after they have been 
        // relocated
        assertTrue(elephant1.isSatisfied());
        assertTrue(elephant2.isSatisfied());
        assertTrue(monkey1.isSatisfied());
        assertTrue(monkey2.isSatisfied());
        assertTrue(monkey3.isSatisfied());

    }
    
}
