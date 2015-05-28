package cs2114.mazesolver;

import android.widget.Button;
import android.widget.TextView;
import sofia.graphics.ShapeView;

// -------------------------------------------------------------------------
/**
 * The test class for MazeSolverScreen.
 *
 * @author Aska Toda
 * @version 2014.03.23)
 */
public class MazeSolverScreenTests
    extends student.AndroidTestCase<MazeSolverScreen>
{
    // ~ Fields ................................................................

    private ShapeView        shapeView;
    private TextView         infoLabel;
    private MazeSolverScreen mazeScreen;
    private Button           drawWalls;
    private Button           eraseWalls;
    private Button           setStart;
    private Button           setGoal;
    private Button           solve;

    // This field will store the pixel width/height of a cell in the maze.
    private int              cellSize;


    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public MazeSolverScreenTests()
    {
        super(MazeSolverScreen.class);
    }


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Initializes the text fixtures.
     */
    public void setUp()
    {
        float viewSize = Math.min(shapeView.getWidth(), shapeView.getHeight());

        cellSize = (int)(viewSize / 8);
        mazeScreen = this.getScreen();

    }


    // ~ Private methods .......................................................

    // ----------------------------------------------------------
    /**
     * Simulates touching down on the middle of the specified cell in the maze.
     */
    private void touchDownCell(int x, int y)
    {
        touchDown(shapeView, (x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates clicking the middle of the specified cell in the maze. This is
     * equivalent to calling: touchDownCell(x, y); touchUp();
     */
    private void clickCell(int x, int y)
    {
        touchDownCell(x, y);
        touchUp();
    }


    /**
     * Simulates clicking the middle of the specified cell in the maze
     * simultaneously. This is equivalent to calling: onTouchMove();
     */
    private void touchMoveCell(int x, int y)
    {
        touchMove((x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    /**
     * The test case for eraseWallsClicked that checks if the walls were
     * correctly removed or erased when clicking on them.
     */
    public void testEraseWallsClicked()
    {
        click(drawWalls);
        clickCell(1, 1);
        clickCell(2, 2);

        click(eraseWalls);
        clickCell(1, 1);
        clickCell(2, 2);

        assertEquals(
            MazeCell.UNEXPLORED,
            mazeScreen.getMaze().getCell(new Location(1, 1)));
    }


    /**
     * The test case for drawWallsClicked that checks if the walls were
     * correctly inserted or drawn into the maze when clicking on a tile.
     */
    public void testDrawWallsClicked()
    {
        click(drawWalls);
        clickCell(1, 1);
        clickCell(2, 2);

        assertEquals(
            MazeCell.WALL,
            mazeScreen.getMaze().getCell(new Location(2, 2)));
    }


    /**
     * The test case for setGoalClicked that checks if the goal location was
     * appropriately added or set when clicking on a tile.
     */
    public void testSetGoalClicked()
    {
        click(setGoal);
        clickCell(6, 6);

        int x = mazeScreen.getMaze().getGoalLocation().x();
        int y = mazeScreen.getMaze().getGoalLocation().y();
        ILocation goalPoint = new Location(x, y);

        assertEquals(goalPoint, new Location(6, 6));
    }


    /**
     * The test case for setStartClicked that checks if the start location was
     * appropriately added or set when clicking on a tile.
     */
    public void testSetStartClicked()
    {
        click(setStart);
        clickCell(1, 1);

        int x = mazeScreen.getMaze().getStartLocation().x();
        int y = mazeScreen.getMaze().getStartLocation().y();
        ILocation startPoint = new Location(x, y);

        assertEquals(startPoint, new Location(1, 1));

    }


    /**
     * The test case for setSolveClicked that checks if the solve method
     * correctly solved the maze.
     */
    public void testSolveClicked()
    {
        click(solve);

        assertEquals("(0, 0)(1, 0)(2, 0)(3, 0)(4, 0)(5, 0)"
            + "(6, 0)(7, 0)(7, 1)(6, 1)(5, 1)(4, 1)(3, 1)(2, 1)(1, 1)(0, 1)"
            + "(0, 2)(1, 2)(2, 2)(3, 2)(4, 2)(5, 2)(6, 2)(7, 2)(7, 3)(6, 3)"
            + "(5, 3)(4, 3)(3, 3)(2, 3)(1, 3)(0, 3)(0, 4)(1, 4)(2, 4)(3, 4)"
            + "(4, 4)(5, 4)(6, 4)(7, 4)(7, 5)(6, 5)(5, 5)(4, 5)(3, 5)(2, 5)"
            + "(1, 5)(0, 5)(0, 6)(1, 6)(2, 6)(3, 6)(4, 6)(5, 6)"
            + "(6, 6)(7, 6)(7, 7)", infoLabel.getText().toString());
    }


    /**
     * The test case for setSolveClicked that checks if the solve method
     * correctly have a situation where the maze couldn't be solved.
     */
    public void testNoSolveClicked()
    {
        click(drawWalls);
        touchDownCell(1, 2);
        touchMoveCell(3, 2);
        touchUp();

        for (int x = 0; x < 8; x++)
        {
            clickCell(x, 2);
        }

        click(solve);
        assertEquals("No solution was possible", infoLabel
            .getText().toString());
    }
}
