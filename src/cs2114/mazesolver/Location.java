package cs2114.mazesolver;

/**
 * // -------------------------------------------------------------------------
 * This is the Location class where the coordination and location is
 * implemented.
 *
 * @author Aska192
 * @version Feb 23, 2014
 */
public class Location
    implements ILocation
{
    // Field(s)
    private int xCoord;
    private int yCoord;


    // ----------------------------------------------------------
    // Constructor

    /**
     * Create a new Location object.
     *
     * @param x
     *            The x coordinate for the maze.
     * @param y
     *            The y coordinate for the maze.
     */
    public Location(int x, int y)
    {
        this.xCoord = x;
        this.yCoord = y;
    }


    // ----------------------------------------------------------
    // Methods

    /**
     * The equals method is declaring the x and y axis of the maze.
     *
     * @return The x and y axis of the maze.
     * @param obj
     *            An object to compare with.
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Location && ((Location)obj).x() == xCoord
            && ((Location)obj).y() == yCoord;
    }


    /**
     * The toString method is returning the coordination or location in brackets
     * and inside will be the coordinates.
     *
     * @return The x and y axis inside brackets of the maze, as a string.
     */
    @Override
    public String toString()
    {
        return "(" + x() + ", " + y() + ")";
    }


    /**
     * The east method is moving towards the right.
     *
     * @return The new location by moving right one unit.
     */
    @Override
    public ILocation east()
    {
        return new Location(x() + 1, y());
    }


    /**
     * The north method is moving towards the top.
     *
     * @return The new location by moving top one unit.
     */
    @Override
    public ILocation north()
    {
        return new Location(x(), y() - 1);
    }


    /**
     * The south method is moving towards the bottom.
     *
     * @return The new location by moving bottom one unit.
     */
    @Override
    public ILocation south()
    {
        return new Location(x(), y() + 1);
    }


    /**
     * The west method is moving towards the left.
     *
     * @return The new location by moving left one unit.
     */
    @Override
    public ILocation west()
    {
        return new Location(x() - 1, y());
    }


    /**
     * The x() method is the x-axis of the maze.
     *
     * @return The x axis of the maze.
     */
    @Override
    public int x()
    {
        return xCoord;
    }


    /**
     * The y() method is the y-axis of the maze.
     *
     * @return The y axis of the maze.
     */
    @Override
    public int y()
    {
        return yCoord;
    }
}
