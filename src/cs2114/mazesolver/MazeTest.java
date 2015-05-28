package cs2114.mazesolver;

/**
 * // -------------------------------------------------------------------------
 * This is the test class for Maze, and checks if the methods in it work
 * properly.
 *
 * @author Aska192
 * @version Feb 24, 2014
 */
public class MazeTest
    extends student.TestCase
{
    // ~ Instance/static fields ...............................................
    private Maze board;


    // ~ Constructor ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new MazeTest object.
     */
    public MazeTest()
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
        board = new Maze(4);
    }


    // ----------------------------------------------------------
    /* Insert your own test methods here */

    /**
     * Tests the getCell method, and see if it returns the axis of a point or
     * location.
     */
    public void testGetCell()
    {
        Location locate1 = new Location(2, 2);
        assertEquals(MazeCell.UNEXPLORED, board.getCell(locate1));
    }


    /**
     * Tests the getCell method when it's invalid, by checking if it goes out of
     * boundary of the maze.
     */
    public void testGetCellInvalid()
    {
        Location locate1 = new Location(5, 5);
        assertEquals(MazeCell.INVALID_CELL, board.getCell(locate1));
    }


    /**
     * Tests the getGoalLocation method and returns the goal location point in
     * the maze.
     */
    public void testGetGoalLocation()
    {
        Location goalLocate = new Location(3, 3);
        board.setGoalLocation(goalLocate);

        assertEquals(board.getGoalLocation(), goalLocate);
    }


    /**
     * Tests the getStartLocation method and returns the start location point in
     * the maze.
     */
    public void testGetStartLocation()
    {
        Location locate1 = new Location(0, 0);
        board.setStartLocation(locate1);

        assertEquals(board.getStartLocation(), locate1);
    }


    /**
     * Tests the startLocation method and checks to see if the setStartLocation
     * has properly placed the start location at a given point.
     */
    public void testSetStartLocation()
    {
        Location startLocate = new Location(2, 2);
        board.setCell(startLocate, MazeCell.WALL);
        board.setStartLocation(startLocate);

        assertEquals(board.getCell(startLocate), MazeCell.UNEXPLORED);
    }


    /**
     * Tests the startLocation method and checks to see if the setStartLocation
     * is invalid when placed out of boundary.
     */
    public void testSetStartLocationInvalid()
    {
        Location invStart = new Location(5, 5);
        board.setCell(invStart, MazeCell.INVALID_CELL);
        board.setStartLocation(invStart);

        assertEquals(board.getCell(invStart), MazeCell.INVALID_CELL);

    }


    /**
     * Tests the goalLocation method and checks to see if the setGoalLocation
     * has properly placed the goal location at a given point.
     */
    public void testSetGoalLocation()
    {
        Location goalLocate = new Location(1, 1);
        board.setCell(goalLocate, MazeCell.WALL);
        board.setGoalLocation(goalLocate);
        assertEquals(board.getCell(goalLocate), MazeCell.UNEXPLORED);
    }


    /**
     * Tests the goalLocation method and checks to see if the setGoalLocation is
     * invalid when placed out of boundary.
     */
    public void testSetGoalLocationInvalid()
    {
        Location invGoal = new Location(3, 3);
        board.setCell(invGoal, MazeCell.INVALID_CELL);
        board.setGoalLocation(invGoal);

        assertEquals(board.getCell(invGoal), MazeCell.INVALID_CELL);
    }


    /**
     * Tests the setCell method and checks that a cell type is inserted in the
     * maze.
     */
    public void testSetCell()
    {
        Location locate1 = new Location(2, 2);
        board.setCell(locate1, MazeCell.FAILED_PATH);

        assertEquals(board.getCell(locate1), MazeCell.FAILED_PATH);
    }


    /**
     * Test the setCell and getCell when outside of bound.
     */
    public void testGetCellInv1()
    {
        Location locate1 = new Location(3, 6);
        assertEquals(board.getCell(locate1), MazeCell.INVALID_CELL);
    }


    /**
     * Test the setCell and getCell when outside of bound.
     */
    public void testGetCellInv2()
    {
        Location locate1 = new Location(6, 3);
        assertEquals(board.getCell(locate1), MazeCell.INVALID_CELL);
    }


    /**
     * Test the setCell and getCell when outside of bound.
     */
    public void testGetCellInv3()
    {
        Location locate1 = new Location(-2, 6);
        assertEquals(board.getCell(locate1), MazeCell.INVALID_CELL);
    }


    /**
     * Test the setCell and getCell when outside of bound.
     */
    public void testGetCellInv4()
    {
        Location locate1 = new Location(6, -2);
        assertEquals(board.getCell(locate1), MazeCell.INVALID_CELL);
    }


    /**
     * Tests the size() method and checks how many items or points there are in
     * the maze or board.
     */
    public void testSize()
    {
        assertEquals(board.size(), 4);
    }


    /**
     * Tests the solve method and displays or returns the strings of the
     * coordination the solver took to solve the maze from the start location to
     * goal location passing along the wall cell in the maze.
     */
    public void testSolve()
    {
        Maze maze = new Maze(3);

        Location p1 = new Location(1, 0);
        Location p2 = new Location(2, 0);
        Location p3 = new Location(2, 1);
        Location p4 = new Location(0, 2);

        maze.setCell(p1, MazeCell.WALL);
        maze.setCell(p3, MazeCell.WALL);
        maze.setCell(p2, MazeCell.WALL);
        maze.setCell(p4, MazeCell.WALL);

        String string = "(0, 0)(0, 1)(1, 1)(1, 2)(2, 2)";

        assertEquals(string, maze.solve());
    }

}
