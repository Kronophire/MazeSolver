package cs2114.mazesolver;

import android.widget.TextView;
import sofia.graphics.RectangleShape;
import sofia.graphics.Color;
import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * The MazeSolverScreen class is where most of the displays for the maze for us
 * to see and interact with. It will be displayed as a GUI, and through an
 * Android application that allows us to interact by touching or clicking the
 * tiles.
 *
 * @author Aska Toda (aska192)
 * @version (2014.03.23)
 */
public class MazeSolverScreen
    extends ShapeScreen
{
    // ~ Fields ................................................................
    private Maze             mazeBoard;
    private int              mode       = 0;
    private final static int ERASE_WALL = 1;
    private final static int SET_GOAL   = 2;
    private final static int DRAW_WALLS = 3;
    private final static int SET_START  = 4;
    private TextView         infoLabel;
    private int              length;


    // ~ Methods ...............................................................

    // ----------------------------------------------------------

    /**
     * Initializes or creates the image of the maze on the GUI.
     */
    public void initialize()
    {
        mazeBoard = new Maze(8);
        float min = Math.min(getWidth(), getHeight());
        length = (int)min / 8;
        for (int i = 0; i < 8; i++)
        {
            float leftX = length * i;
            float rightX = leftX + length;
            for (int j = 0; j < 8; j++)
            {
                float topY = length * j;
                float bottomY = topY + length;

                RectangleShape tileBoard =
                    new RectangleShape(leftX, topY, rightX, bottomY);
                tileBoard.setFillColor(Color.darkGray);
                tileBoard.setColor(Color.black);
                add(tileBoard);

            }
        }

        // Setting goal tile location its own color; red.
        int goalX = mazeBoard.getGoalLocation().x();
        int goalY = mazeBoard.getGoalLocation().y();

        RectangleShape goalTile =
            getShapes()
                .locatedAt((goalX + 0.5f) * length, (goalY + 0.5f) * length)
                .withClass(RectangleShape.class).front();
        goalTile.setFillColor(Color.red);

        // Setting start tile location to its own color; green.
        int startX = mazeBoard.getStartLocation().x();
        int startY = mazeBoard.getStartLocation().y();

        RectangleShape startTile =
            getShapes()
                .locatedAt((startX + 0.5f) * length, (startY + 0.5f) * length)
                .withClass(RectangleShape.class).front();
        startTile.setFillColor(Color.green);

    }


    /**
     * The ProcessTouch method that occurs when touching on one of the tile and
     * interacts with the GUI.
     *
     * @param x
     *            The x axis of the tile.
     * @param y
     *            The y axis of the tile.
     */
    public void processTouch(float x, float y)
    {
        int xCoord = (int)(x / length);
        int yCoord = (int)(y / length);

        RectangleShape tile =
            getShapes().locatedAt(x, y).withClass(RectangleShape.class).front();
        if (tile != null)
        {
            if (mode == ERASE_WALL && !mazeBoard.getGoalLocation()
                .equals(new Location(xCoord, yCoord))
                && !mazeBoard.getStartLocation()
                    .equals(new Location(xCoord, yCoord)))
            {
                ILocation erasingWall = new Location(xCoord, yCoord);
                mazeBoard.setCell(erasingWall, MazeCell.UNEXPLORED);
                tile.setFillColor(Color.darkGray);
            }
            else if (mode == SET_GOAL && !mazeBoard.getStartLocation()
                .equals(new Location(xCoord, yCoord)))
            {
                int prevGoalX = mazeBoard.getGoalLocation().x();
                int prevGoalY = mazeBoard.getGoalLocation().y();
                RectangleShape goalTile =
                    getShapes()
                        .locatedAt(
                            (prevGoalX + 0.5f) * length,
                            (prevGoalY + 0.5f) * length)
                        .withClass(RectangleShape.class).front();
                ILocation currLoc = new Location(prevGoalX, prevGoalY);
                mazeBoard.setCell(currLoc, MazeCell.UNEXPLORED);
                goalTile.setFillColor(Color.darkGray);

                ILocation goal = new Location(xCoord, yCoord);
                mazeBoard.setGoalLocation(goal);
                tile.setFillColor(Color.red);
            }
            else if (mode == DRAW_WALLS && !mazeBoard.getGoalLocation()
                .equals(new Location(xCoord, yCoord))
                && !mazeBoard.getStartLocation()
                    .equals(new Location(xCoord, yCoord)))
            {
                ILocation drawWall = new Location(xCoord, yCoord);
                mazeBoard.setCell(drawWall, MazeCell.WALL);
                tile.setFillColor(Color.black);
            }
            else if (mode == SET_START && !mazeBoard.getGoalLocation()
                .equals(new Location(xCoord, yCoord)))
            {
                int prevStartX = mazeBoard.getStartLocation().x();
                int prevStartY = mazeBoard.getStartLocation().y();
                RectangleShape startTile =
                    getShapes()
                        .locatedAt(
                            (prevStartX + 0.5f) * length,
                            (prevStartY + 0.5f) * length)
                        .withClass(RectangleShape.class).front();
                ILocation currLoc = new Location(prevStartX, prevStartY);
                mazeBoard.setCell(currLoc, MazeCell.UNEXPLORED);
                startTile.setFillColor(Color.darkGray);

                ILocation start = new Location(xCoord, yCoord);
                mazeBoard.setStartLocation(start);
                tile.setFillColor(Color.green);
            }
        }
    }


    /**
     * onTouchDown method occurs when one touches a tile the screen of the GUI,
     * then something happens.
     *
     * @param x
     *            The x axis of the tile.
     * @param y
     *            The y axis of the tile.
     */
    public void onTouchDown(float x, float y)
    {
        processTouch(x, y);
    }


    /**
     * onTouchMove method occurs when one sliders their finger across multiple
     * tiles then something happens.
     *
     * @param x
     *            The x axis of the tile.
     * @param y
     *            The y axis of the tile.
     */
    public void onTouchMove(float x, float y)
    {
        processTouch(x, y);
    }


    /**
     * When the Erase button is pressed on the GUI, this method will be executed
     * and turn into "erase_wall" mode. It will erase the walls in the maze.
     */
    public void eraseWallsClicked()
    {
        mode = ERASE_WALL;
    }


    /**
     * When the Goal button is pressed on the GUI, this method will be executed
     * and turn into "set_Goal" mode. The setGoal method puts the goal in the
     * maze on the indicated pressed / clicked location.
     */
    public void setGoalClicked()
    {
        mode = SET_GOAL;
    }


    /**
     * When the Draw button is pressed on the GUI, this method will be executed
     * and turn into "draw_walls" mode. The drawWalls method inserts walls in
     * the maze on the indicated pressed / clicked location.
     */
    public void drawWallsClicked()
    {
        mode = DRAW_WALLS;
    }


    /**
     * When the Start button is pressed on the GUI, this method will be executed
     * and turn into "set_Start" mode. The setStart method puts the start in the
     * maze on the indicated pressed / clicked location.
     */
    public void setStartClicked()
    {
        mode = SET_START;
    }


    /**
     * When the Solve button is pressed / clicked on the GUI, this method will
     * be executed. The solve method will solve the maze and returns or displays
     * the coordinates that were taken from the start to goal. If there was no
     * possibility to reach the goal then there is no solution.
     */
    public void solveClicked()
    {
        String result = mazeBoard.solve();
        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                ILocation currLocation = new Location(x, y);
                RectangleShape prevTile =
                    getShapes()
                        .locatedAt((x + 0.5f) * length, (y + 0.5f) * length)
                        .withClass(RectangleShape.class).front();
                if (mazeBoard.getCell(currLocation) == MazeCell.CURRENT_PATH)
                {
                    prevTile.setFillColor(Color.blue);
                }
                else if (mazeBoard.getCell(currLocation)
                    == MazeCell.FAILED_PATH)
                {
                    prevTile.setFillColor(Color.purple);
                }
            }
        }

        // Setting goal tile location back to its own color after solving.
        int goalX = mazeBoard.getGoalLocation().x();
        int goalY = mazeBoard.getGoalLocation().y();

        RectangleShape goalTile =
            getShapes()
                .locatedAt((goalX + 0.5f) * length, (goalY + 0.5f) * length)
                .withClass(RectangleShape.class).front();
        goalTile.setFillColor(Color.red);

        // Setting start tile location back to its own color after solving.
        int startX = mazeBoard.getStartLocation().x();
        int startY = mazeBoard.getStartLocation().y();

        RectangleShape startTile =
            getShapes()
                .locatedAt((startX + 0.5f) * length, (startY + 0.5f) * length)
                .withClass(RectangleShape.class).front();
        startTile.setFillColor(Color.green);

        if (result == null)
        {
            result = "No solution was possible";
        }

        infoLabel.setText(result);
    }


    /**
     * The getMaze is a getter method that gets the maze that is being
     * initialized.
     *
     * @return The mazeBoard, which is the maze where the maze game is being
     *         played.
     */
    public Maze getMaze()
    {
        return mazeBoard;
    }
}
