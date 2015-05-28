package cs2114.mazesolver;

import java.util.Stack;

/**
 * // -------------------------------------------------------------------------
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Aska192
 * @version Feb 25, 2014
 */
public class MazeSolver
{

    private Maze            maze;
    private Stack<Location> stackPoint;


    // ----------------------------------------------------------
    /**
     * Create a new MazeSolver object.
     *
     * @param maze
     *            The board representing the maze.
     */
    public MazeSolver(Maze maze)
    {
        this.maze = maze;

        stackPoint = new Stack<Location>();
    }


    // ----------------------------------------------------------
    /**
     * The solve method that is the various solution for maze.
     *
     * @return The stack points into string.
     */
    public String solve()
    {
        Location startingPoint = (Location)maze.getStartLocation();
        Location endingPoint = (Location)maze.getGoalLocation();
        stackPoint.push(startingPoint);
        maze.setCell(startingPoint, MazeCell.CURRENT_PATH);

        while (!stackPoint.isEmpty())
        {
            Location currentStack = stackPoint.peek();
            maze.setCell(currentStack, MazeCell.CURRENT_PATH);

            if (currentStack.equals(endingPoint))
            {
                return returnSolutionCoordinate();

            }

            checkValidDirections(currentStack);

        }
        return null;
    }


    // ----------------------------------------------------------
    /**
     * The returnSolutionCoordinate method is changing the stack points, which
     * are the coordination of the points on the maze into a string.
     *
     * @return The toString as of type StringBuilder.
     */
    public String returnSolutionCoordinate()
    {
        Stack<Location> backStack = new Stack<Location>();

        while (!stackPoint.isEmpty())
        {
            backStack.push(stackPoint.peek());
            stackPoint.pop();
        }

        StringBuilder strBuilder = new StringBuilder();

        while (!backStack.isEmpty())
        {
            strBuilder.append(backStack.peek().toString());
            backStack.pop();
        }

        return strBuilder.toString();

    }


    // ----------------------------------------------------------
    /**
     * The checkValidDirections method checks if the current location has the
     * surrounding open to move to.
     *
     * @param currentLocation
     *            The location or point on the maze.
     */
    public void checkValidDirections(Location currentLocation)
    {
        if (maze.getCell(currentLocation.east()).equals(MazeCell.UNEXPLORED))
        {
            this.stackPoint.push((Location)currentLocation.east());
        }
        else if (maze.getCell(currentLocation.west()).equals(
            MazeCell.UNEXPLORED))
        {
            this.stackPoint.push((Location)currentLocation.west());
        }
        else if (maze.getCell(currentLocation.north()).equals(
            MazeCell.UNEXPLORED))
        {
            this.stackPoint.push((Location)currentLocation.north());
        }
        else if (maze.getCell(currentLocation.south()).equals(
            MazeCell.UNEXPLORED))
        {
            this.stackPoint.push((Location)currentLocation.south());
        }
        else
        {
            this.maze.setCell(currentLocation, MazeCell.FAILED_PATH);
            this.stackPoint.pop();

        }

    }

}
