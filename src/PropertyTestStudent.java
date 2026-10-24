/**
 * Student tests for Property.
 * CMSC203 Assignment 4
 * @author Arbaz Saghir
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PropertyTestStudent {

    @Test
    public void testDefaultConstructor() {
        Property property = new Property();

        assertEquals("", property.getPropertyName());
        assertEquals("", property.getCity());
        assertEquals("", property.getOwner());
        assertEquals(0.0, property.getRentAmount(), 0.001);
        assertNotNull(property.getPlot());
        assertEquals("0,0,1,1", property.getPlot().toString());
    }

    @Test
    public void testConstructorWithPropertyInformation() {
        Property property = new Property(
                "Oak House", "Rockville", 2500.0, "Ali");

        assertEquals("Oak House", property.getPropertyName());
        assertEquals("Rockville", property.getCity());
        assertEquals("Ali", property.getOwner());
        assertEquals(2500.0, property.getRentAmount(), 0.001);
        assertEquals("0,0,1,1", property.getPlot().toString());
    }

    @Test
    public void testConstructorWithPlotInformation() {
        Property property = new Property(
                "Oak House", "Rockville", 2500.0, "Ali",
                2, 3, 4, 5);

        assertEquals("Oak House", property.getPropertyName());
        assertEquals("Rockville", property.getCity());
        assertEquals("Ali", property.getOwner());
        assertEquals(2500.0, property.getRentAmount(), 0.001);

        assertEquals(2, property.getPlot().getX());
        assertEquals(3, property.getPlot().getY());
        assertEquals(4, property.getPlot().getWidth());
        assertEquals(5, property.getPlot().getDepth());
    }

    @Test
    public void testCopyConstructor() {
        Property original = new Property(
                "Oak House", "Rockville", 2500.0, "Ali",
                2, 3, 4, 5);

        Property copy = new Property(original);

        assertNotSame(original, copy);
        assertEquals(original.toString(), copy.toString());

        // The copied property must have its own Plot.
        assertNotSame(original.getPlot(), copy.getPlot());
        assertEquals("2,3,4,5", copy.getPlot().toString());

        // Changing the original Plot must not change the copy.
        original.getPlot().setX(8);
        original.getPlot().setWidth(1);

        assertEquals(2, copy.getPlot().getX());
        assertEquals(4, copy.getPlot().getWidth());
    }

    @Test
    public void testGetPropertyName() {
        Property property = new Property(
                "Maple Apartments", "Bethesda", 1800.0, "Sara");

        assertEquals("Maple Apartments", property.getPropertyName());
    }

    @Test
    public void testGetCity() {
        Property property = new Property(
                "Maple Apartments", "Bethesda", 1800.0, "Sara");

        assertEquals("Bethesda", property.getCity());
    }

    @Test
    public void testGetRentAmount() {
        Property property = new Property(
                "Maple Apartments", "Bethesda", 1800.75, "Sara");

        assertEquals(1800.75, property.getRentAmount(), 0.001);
    }

    @Test
    public void testGetOwner() {
        Property property = new Property(
                "Maple Apartments", "Bethesda", 1800.0, "Sara");

        assertEquals("Sara", property.getOwner());
    }

    @Test
    public void testGetPlot() {
        Property property = new Property(
                "Maple Apartments", "Bethesda", 1800.0, "Sara",
                1, 2, 3, 4);

        Plot plot = property.getPlot();

        assertNotNull(plot);
        assertEquals(1, plot.getX());
        assertEquals(2, plot.getY());
        assertEquals(3, plot.getWidth());
        assertEquals(4, plot.getDepth());
    }

    @Test
    public void testToString() {
        Property property = new Property(
                "Oak House", "Rockville", 2500.0, "Ali");

        assertEquals(
                "Oak House,Rockville,Ali,2500.0",
                property.toString());

        assertEquals(",,,0.0", new Property().toString());
    }
}
