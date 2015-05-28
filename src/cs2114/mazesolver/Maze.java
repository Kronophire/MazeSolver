package cs2114.mazesolver;

import sofia.util.Observable;

/**
 * // -------------------------------------------------------------------------
 * This is the Maze class that contains the method for the MazeSolver game
 * to function.
 *
 * @author Aska192
 * @version Feb 23, 2014
 */

public class Maze
    extends Observable
    implements IMaze
{
    // Field(s)
    private MazeCell[][] mazeBoard;
    private Location     startGoal;
    private Location     endGoal;
    private int          size;


    // ----------------------------------------------------------
    // Constructor
    /**
     * Creates a Maze, and holds a size parameter which is the length of the
     * maze. Since the maze is a square, the length is for all sides of
     * the maze.
     *
     * @param size
     *            The size of the maze.
     */
    public Maze(int size)
    {
        this.size = size;
        mazeBoard = new MazeCell[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                mazeBoard[i][j] = MazeCell.UNEXPLORED;
            }
        }

        startGoal = new Location(0, 0);
        endGoal = new Location(size - 1, size - 1);
    }


    // ~ Methods ...............................................................

    // ----------------------------------------------------------

    /**
     * The getCell method is a getter, and returns the cell on a certain
     * location in the maze.
     *
     * @param location
     *            The coordination of a point in the maze.
     * @return The maze's overall size with the given x and y coordinates, or
     *         returns an invalid cell when out of the bounds of the maze.
     */
    @Override
    public MazeCell getCell(ILocation location)
    {
        if (location.x() < this.size && location.y() < this.size
            && location.x() >= 0 && location.y() >= 0)
        {
            return mazeBoard[location.x()][location.y()];
        }
        else
        {
            return MazeCell.INVALID_CELL;
        }

    }


    /**
     * The getGoalLocation, a getter method,
     * returns the cell location of the goal.
     *
     * @return The end or goal of the maze.
     */
    @Override
    public ILocation getGoalLocation()
    {
        return endGoal;
    }


    /**
     * The getStartLocation, a getter method,
     * returns the cell location of the start.
     *
     * @return The start of the maze.
     */
    @Override
    public ILocation getStartLocation()
    {
        return startGoal;
    }


    /**
     * The setCell method is where the different types of the maze cell to be
     * inserted in the maze. The types included are invald cell, wall,
     * unexplored cell, and failed path.
     *
     * @param coordination
     *            The coordination of one point in the maze.
     * @param type
     *            The type of the MazeCell such as an unexplored cell.
     */
    @Override
    public void setCell(ILocation coordination, MazeCell type)
    {
        int x = coordination.x();
        int y = coordination.y();
        if ((getCell(coordination) != MazeCell.INVALID_CELL)
            && (!(coordination.equals(startGoal) || coordination
                .equals(endGoal)) || type != MazeCell.WALL))
        {
            mazeBoard[coordination.x()][coordination.y()] = type;
            Location locationChange = new Location(x, y);
            notifyObservers(locationChange);
        }

    }


    /**
     * The setGoalLocation method sets or inserts the goal location on the
     * maze.
     *
     * @param goal
     *            The goal or end point that can change the goal location.
     */
    @Override
    public void setGoalLocation(ILocation goal)
    {
        int x = goal.x();
        int y = goal.y();
        if (getCell(goal) != MazeCell.INVALID_CELL)
        {
            if (getCell(goal) == MazeCell.WALL)
            {
                setCell(goal, MazeCell.UNEXPLORED);
                Location locationChange = new Location(x, y);
                notifyObservers(locationChange);
            }

            endGoal = (Location)goal;
        }
    }


    /**
     * The setStartLocation method sets or inserts the start location on the
     * maze.
     *
     * @param start
     *            The starting point that can change the start location.
     */
    @Override
    public void setStartLocation(ILocation start)
    {
        int x = start.x();
        int y = start.y();
        if (getCell(start) != MazeCell.INVALID_CELL)
        {
            if (getCell(start) == MazeCell.WALL)
            {
                setCell(start, MazeCell.UNEXPLORED);
                Location locationChange = new Location(x, y);
                notifyObservers(locationChange);
            }
            startGoal = (Location)start;
        }
    }


    /**
     * The size method is the size or length of the maze.
     *
     * @return The maze's size.
     */
    @Override
    public int size()
    {
        return size;
    }


    /**
     * The solve method solves the maze game, but the actual method is
     * implemented in a separate class called MazeSolver.
     *
     * @return The solve method from the MazeSolver class.
     */
    @Override
    public String solve()
    {
        return new MazeSolver(this).solve();
    }

}
