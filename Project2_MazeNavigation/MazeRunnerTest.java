import sofia.micro.*;
import sofia.micro.jeroo.*;
import static sofia.micro.jeroo.CompassDirection.*;
import static sofia.micro.jeroo.RelativeDirection.*;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)
// -------------------------------------------------------------------------
/**
 *  Test class for MazeRunner
 *  Test the functions of the MazeRunner class
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.09.18
 */
public class MazeRunnerTest extends TestCase
{
    //~ Fields ................................................................


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new MazeRunnerTest test object.
     */
    public MazeRunnerTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------

    /**
     * Test the myProgram() method on Maze 10
     */
    public void testMyProgram()
    {
        // Set up initial conditions
        MazeIsland island = new MazeIsland(10);
        MazeRunner runner = new MazeRunner();
        island.add(runner, 2, 2);
        
        // Call myProgram()
        runner.myProgram();
        
        // Expected outcomes
        // The jeroo should end at (1, 1), so check the x and y coordinate
        assertEquals(1, runner.getGridX());   
        assertEquals(1, runner.getGridY());
        // Island should have 0 flowers and 0 nets left
        assertEquals(0, island.getObjects(Flower.class).size());
        assertEquals(0, island.getObjects(Net.class).size());

    }
    
    
    
    /**
     * Test the clearIsland() method on Maze 10
     */
    public void testClearIsland()
    {
        // Set up initial conditions
        MazeIsland island = new MazeIsland(10);
        MazeRunner runner = new MazeRunner();
        island.add(runner, 2, 2);
        
        // Call clearIsland()
        runner.clearIsland();
              
        // Expected outcomes
        // Island should have 0 flowers and 0 nets left
        assertEquals(0, island.getObjects(Flower.class).size());
        assertEquals(0, island.getObjects(Net.class).size());

    }
    
    
    
    /**
     * Test the WHILE loop in the clearIsland() method on Maze 20
     */
    public void testClearIslandA()
    {
        // Set up initial conditions
        MazeIsland island = new MazeIsland(20);
        MazeRunner runner1 = new MazeRunner();
        // Get a MazeRunner to clear the island
        island.add(runner1, 2, 2);
        runner1.clearIsland();
           
        // Set up another MazeRunner to try to get it
        // to clear the island
        MazeRunner runner2 = new MazeRunner();
        island.add(runner2, 1, 1);
        
        // Call clearIsland() on runner2
        runner2.clearIsland();
        
        // Expected outcomes
        // Since runner1 already cleared the island,
        // the while loop in clearIsland will not run
        // and runner2 should remain in the same initial
        // position of (1,1)
        assertEquals(1, runner2.getGridX());
        assertEquals(1, runner2.getGridY());
    }
    
    
    
    /**
     * Test the goToFinish() method on Maze 10 when
     * MazeRunner is not in position (1,1)
     */
    public void testGoToFinishA()
    {
        // Set up initial conditions
        MazeIsland island = new MazeIsland(10);
        MazeRunner runner = new MazeRunner();
        island.add(runner, 2, 2);
        // Clear all flowers and nets on the island before calling
        // the goToFinish() method for testing
        runner.clearIsland();
        
        // Call goToFinish()
        runner.goToFinish();
              
        // Expected outcomes
        // Runner should finish in position (1,1)
        assertEquals(1, runner.getGridX());
        assertEquals(1, runner.getGridY());

    }
    
    
    
    /**
     * Test the goToFinish() method on Maze 10 when
     * MazeRunner is already at its final position (1,1)
     */
    public void testGoToFinishB()
    {
        // Set up initial conditions
        MazeIsland island = new MazeIsland(10);
        MazeRunner runner = new MazeRunner();
        // Spawns MazeRunner at its finish position (1,1)
        island.add(runner, 1, 1);
        
        // Call goToFinish()
        runner.goToFinish();
              
        // Expected outcomes
        // Runner should finish in position (1,1) and not move
        assertEquals(1, runner.getGridX());
        assertEquals(1, runner.getGridY());
    }
    
    
    
    /**
     * Test the findOrientation() method on Maze 20
     * Specifically testing the left turn If statement
     */
    public void testFindOrientationA()
    {
        // Set up initial conditions
        MazeIsland island = new MazeIsland(20);
        MazeRunner runner = new MazeRunner();
        island.add(runner, 1, 1);
        // Make runner face West so that the square
        // to its LEFT is clear
        runner.turn180();
        
        // Call the findOrientation() method so the left turn
        // if branch is executed
        runner.findOrientation();
        
        // Expected outcomes
        // MazeRunner is facing SOUTH after turning LEFT
        assertTrue(runner.isFacing(SOUTH));
    }
    
    
    
    /**
     * Test the findOrientation() method on Maze 20
     * Specifically testing the turn180() If statement
     * when MazeRunner is stuck in a dead end
     */
    public void testFindOrientationB()
    {
        // Set up initial conditions
        MazeIsland island = new MazeIsland(20);
        MazeRunner runner = new MazeRunner();
        // Add MazeRunner to face a dead End at NORTH
        island.add(runner, 3, 3);
        runner.turn(LEFT);
        
        // Call the findOrientation() method so the 180 turn
        // if branch is executed
        runner.findOrientation();
        
        // Expected outcomes
        // MazeRunner is facing SOUTH after turning around 180
        assertTrue(runner.isFacing(SOUTH));
    }
    
    
    
    /**
     * Test the findOrientation() method on Maze 20
     * Specifically testing the last If statement
     * when MazeRunner needs to make a RIGHT turn
     */
    public void testFindOrientationC()
    {
        // Set up initial conditions
        MazeIsland island = new MazeIsland(20);
        MazeRunner runner = new MazeRunner();
        // Add MazeRunner to face WEST to a corner of the map
        // so it will turn RIGHT because there is
        // water to its LEFT and up AHEAD
        island.add(runner, 1, 11);
        runner.turn180();
        
        // Call the findOrientation() method so the RIGHT turn
        // if branch is executed
        runner.findOrientation();
        
        // Expected outcomes
        // Runner should face NORTH (turns RIGHT from facing WEST)
        assertTrue(runner.isFacing(NORTH));
    }
    
    
    
    /**
     * Test the turn180() method on Maze 10
     */
    public void testTurn180()
    {
        // Set up initial conditions
        MazeIsland island = new MazeIsland(10);
        MazeRunner runner = new MazeRunner();
        // MazeRunner faces East
        island.add(runner, 2, 1);
        
        // Call turn180()
        runner.turn180();
        
        // Expected outcomes
        // Runner should turn 180 to face West (opposite direction)
        assertTrue(runner.isFacing(WEST));
    }
    
}
