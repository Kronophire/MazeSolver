package cs2114.mazesolver;

/**
 * // -------------------------------------------------------------------------
 * This is the test for the Location class, and it checks to see if the methods
 * it contains work properly.
 *
 * @author Aska192
 * @version Feb 24, 2014
 */
public class LocationTest
    extends student.TestCase
{
    // ~ Instance/static fields ...............................................
    private Location point1;
    private Location point2;
    private Location point3;


    // ~ Constructor ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new LocationTest object.
     */
    public LocationTest()
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
        point1 = new Location(3, 3);
        point2 = new Location(5, 7);
        point3 = new Location(3, 3);
    }


    // ----------------------------------------------------------
    /* Insert your own test methods here */

    /**
     * Tests the equals method that compares 2 points, being same and different.
     */
    public void testEquals()
    {
        assertTrue(point1.equals(point3));
        assertFalse(point1.equals(point2));
    }


    /**
     * Tests the toString method that checks if the string would return with the
     * x and y axis in a bracket.
     */
    public void testToString()
    {
        assertEquals(point1.toString(), "(3, 3)");
    }


    /**
     * Tests the east method that checks to see if the point moves one unit
     * right.
     */
    public void testEast()
    {
        Location point4 = new Location(4, 3);

        assertEquals(point4, point1.east());
    }


    /**
     * Tests the west method that checks to see if the point moves one unit
     * left.
     */
    public void testWest()
    {
        Location point5 = new Location(2, 3);

        assertEquals(point5, point1.west());

    }


    /**
     * Tests the north method that checks to see if the point moves one unit
     * top.
     */
    public void testNorth()
    {
        Location point6 = new Location(3, 2);

        assertEquals(point6, point1.north());

    }


    /**
     * Tests the south method that checks to see if the point moves one unit
     * bottom.
     */
    public void testSouth()
    {
        Location point7 = new Location(3, 4);

        assertEquals(point7, point1.south());

    }


    /**
     * Tests the x() method that checks the x-axis from a point.
     */
    public void testX()
    {
        assertEquals(point2.x(), 5);

    }


    /**
     * Tests the y() method that checks the y-axis from a point.
     */
    public void testY()
    {
        assertEquals(point2.y(), 7);

    }

}
