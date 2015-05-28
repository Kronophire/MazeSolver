package cs2114.mazesolver;

/**
 * // -------------------------------------------------------------------------
 * This is the test class for the MazeSolver class, and checks if the
 * methods in it work properly, which purposely solves the maze game.
 *
 * @author Aska192
 * @version Feb 24, 2014
 */

public class MazeSolverTest
    extends student.TestCase
{

    // ~ Instance/static fields ...............................................
    private Maze maze;


// private Stack<ILocation> stackPoint;
// private MazeSolver solver;

    // ~ Constructor ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new MazeSolverTest object.
     */
    public MazeSolverTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    // ~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */

    public void setUp()
    {
        maze = new Maze(3);

        Location p1 = new Location(1, 0);
        Location p2 = new Location(2, 0);
        Location p3 = new Location(2, 1);
        Location p4 = new Location(0, 2);

        maze.setCell(p1, MazeCell.WALL);
        maze.setCell(p3, MazeCell.WALL);
        maze.setCell(p2, MazeCell.WALL);
        maze.setCell(p4, MazeCell.WALL);

    }


    // ----------------------------------------------------------
    /* Insert your own test methods here */

    /**
     * Tests the solve method that checks if it displays a string of
     * coordination.
     */
    public void testSolve()
    {
        String string = "(0, 0)(0, 1)(1, 1)(1, 2)(2, 2)";

        assertEquals(string, maze.solve());
    }


    /**
    * Tests the solve method with different setup of the maze, and returns
    * the specific coordination it took to go from the start location to the
    * goal location.
    */
    public void testSolve2()
    {
        maze = new Maze(5);

        Location p1 = new Location(1, 0);
        Location p2 = new Location(1, 1);
        Location p3 = new Location(3, 1);
        Location p4 = new Location(3, 2);
        Location p5 = new Location(3, 2);
        Location p6 = new Location(3, 3);
        Location p7 = new Location(2, 3);
        Location p8 = new Location(1, 3);
        Location p9 = new Location(0, 3);

        Location endGoal = new Location(3, 4);

        maze.setGoalLocation(endGoal);
        maze.setCell(p1, MazeCell.WALL);
        maze.setCell(p3, MazeCell.WALL);
        maze.setCell(p2, MazeCell.WALL);
        maze.setCell(p4, MazeCell.WALL);
        maze.setCell(p5, MazeCell.WALL);
        maze.setCell(p6, MazeCell.WALL);
        maze.setCell(p7, MazeCell.WALL);
        maze.setCell(p8, MazeCell.WALL);
        maze.setCell(p9, MazeCell.WALL);

        String string =
            "(0, 0)(0, 1)(0, 2)(1, 2)(2, 2)(2, 1)(2, 0)(3, 0)(4, 0)"
                + "(4, 1)(4, 2)(4, 3)(4, 4)(3, 4)";

        assertEquals(maze.solve(), string);
    }


    /**
    * Tests the solve method when it's not possible to solve the maze.
    */
    public void testSolveNull()
    {
        Maze maze2 = new Maze(3);
        Location p1 = new Location(1, 0);
        Location p2 = new Location(2, 0);
        Location p3 = new Location(2, 1);
        Location p4 = new Location(0, 2);
        Location p5 = new Location(1, 1);

        maze2.setCell(p1, MazeCell.WALL);
        maze2.setCell(p3, MazeCell.WALL);
        maze2.setCell(p2, MazeCell.WALL);
        maze2.setCell(p4, MazeCell.WALL);
        maze2.setCell(p5, MazeCell.WALL);

        assertEquals(null, maze2.solve());
    }


    /**
     * Tests the returnSolutionCoordinate method and checks to see if
     * the maze display the coordinates of the solution.
     */
    public void testReturnSolutionCoordinate()
    {
        MazeSolver solver = new MazeSolver(maze);
        solver.returnSolutionCoordinate();

        assertEquals(maze.size(), 3);

    }


    /**
     * Tests the checkValidDirections and check the directions the solve
     * method can take at a certain point.
     */
    public void testCheckValidDirections()
    {
        MazeSolver solver = new MazeSolver(maze);

        Location loc1 = new Location(0, 1);
        solver.checkValidDirections(loc1);

        assertEquals(maze.size(), 3);
    }

}
