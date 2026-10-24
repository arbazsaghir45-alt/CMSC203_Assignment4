/**
 * Student tests for Plot.
 * CMSC203 Assignment 4
 * @author Arbaz Saghir
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlotTestStudent {

    @Test
    public void testDefaultConstructor() {
        Plot plot = new Plot();

        assertEquals(0, plot.getX());
        assertEquals(0, plot.getY());
        assertEquals(1, plot.getWidth());
        assertEquals(1, plot.getDepth());
    }

    @Test
    public void testParameterizedConstructor() {
        Plot plot = new Plot(2, 3, 4, 5);

        assertEquals(2, plot.getX());
        assertEquals(3, plot.getY());
        assertEquals(4, plot.getWidth());
        assertEquals(5, plot.getDepth());
    }

    @Test
    public void testCopyConstructor() {
        Plot original = new Plot(2, 3, 4, 5);
        Plot copy = new Plot(original);

        assertNotSame(original, copy);
        assertEquals("2,3,4,5", copy.toString());

        original.setX(9);
        assertEquals(2, copy.getX());
    }

    @Test
    public void testOverlaps() {
        Plot plot = new Plot(2, 2, 4, 4);

        // Partial overlap.
        assertTrue(plot.overlaps(new Plot(5, 5, 3, 3)));

        // One plot inside another.
        assertTrue(plot.overlaps(new Plot(3, 3, 1, 1)));

        // Identical plots.
        assertTrue(plot.overlaps(new Plot(2, 2, 4, 4)));

        // Separated plots in all four directions.
        assertFalse(plot.overlaps(new Plot(0, 2, 1, 2)));
        assertFalse(plot.overlaps(new Plot(7, 2, 1, 2)));
        assertFalse(plot.overlaps(new Plot(2, 0, 2, 1)));
        assertFalse(plot.overlaps(new Plot(2, 7, 2, 1)));

        // Touching edges or a corner.
        assertFalse(plot.overlaps(new Plot(6, 2, 2, 2)));
        assertFalse(plot.overlaps(new Plot(0, 2, 2, 2)));
        assertFalse(plot.overlaps(new Plot(2, 6, 2, 2)));
        assertFalse(plot.overlaps(new Plot(2, 0, 2, 2)));
        assertFalse(plot.overlaps(new Plot(6, 6, 2, 2)));

        // Crossing rectangles with no corners inside each other.
        Plot horizontal = new Plot(0, 4, 10, 2);
        Plot vertical = new Plot(4, 0, 2, 10);

        assertTrue(horizontal.overlaps(vertical));
        assertTrue(vertical.overlaps(horizontal));
    }

    @Test
    public void testEncompasses() {
        Plot plot = new Plot(2, 2, 6, 6);

        assertTrue(plot.encompasses(new Plot(3, 3, 2, 2)));
        assertTrue(plot.encompasses(new Plot(2, 2, 6, 6)));
        assertTrue(plot.encompasses(new Plot(6, 6, 2, 2)));

        // Extends beyond each boundary.
        assertFalse(plot.encompasses(new Plot(1, 3, 2, 2)));
        assertFalse(plot.encompasses(new Plot(3, 1, 2, 2)));
        assertFalse(plot.encompasses(new Plot(7, 3, 2, 2)));
        assertFalse(plot.encompasses(new Plot(3, 7, 2, 2)));

        // Overlap alone does not mean containment.
        assertFalse(plot.encompasses(new Plot(0, 0, 10, 10)));
    }

    @Test
    public void testSetX() {
        Plot plot = new Plot();
        plot.setX(5);
        assertEquals(5, plot.getX());
    }

    @Test
    public void testSetY() {
        Plot plot = new Plot();
        plot.setY(6);
        assertEquals(6, plot.getY());
    }

    @Test
    public void testSetWidth() {
        Plot plot = new Plot();
        plot.setWidth(7);
        assertEquals(7, plot.getWidth());
    }

    @Test
    public void testSetDepth() {
        Plot plot = new Plot();
        plot.setDepth(8);
        assertEquals(8, plot.getDepth());
    }

    @Test
    public void testGetX() {
        assertEquals(2, new Plot(2, 3, 4, 5).getX());
    }

    @Test
    public void testGetY() {
        assertEquals(3, new Plot(2, 3, 4, 5).getY());
    }

    @Test
    public void testGetWidth() {
        assertEquals(4, new Plot(2, 3, 4, 5).getWidth());
    }

    @Test
    public void testGetDepth() {
        assertEquals(5, new Plot(2, 3, 4, 5).getDepth());
    }

    @Test
    public void testToString() {
        Plot plot = new Plot(2, 3, 4, 5);
        assertEquals("2,3,4,5", plot.toString());
    }
}
