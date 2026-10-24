
/**
 * Student tests for ManagementCompany.
 * CMSC203 Assignment 4
 * @author Arbaz Saghir
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ManagementCompanyTestStudent {

    @Test
    public void testDefaultConstructor() {
        ManagementCompany company = new ManagementCompany();

        assertEquals("", company.getName());
        assertEquals("", company.getTaxID());
        assertEquals(0.0, company.getMgmFeePer(), 0.001);
        assertEquals(0, company.getPropertiesCount());
        assertEquals(5, company.getProperties().length);
        assertEquals("0,0,10,10", company.getPlot().toString());

        for (Property property : company.getProperties()) {
            assertNull(property);
        }
    }

    @Test
    public void testConstructorWithCompanyInformation() {
        ManagementCompany company =
                new ManagementCompany("Arbaz Rentals", "12345", 6.0);

        assertEquals("Arbaz Rentals", company.getName());
        assertEquals("12345", company.getTaxID());
        assertEquals(6.0, company.getMgmFeePer(), 0.001);
        assertEquals(0, company.getPropertiesCount());
        assertEquals("0,0,10,10", company.getPlot().toString());
    }

    @Test
    public void testConstructorWithPlotInformation() {
        ManagementCompany company = new ManagementCompany(
                "Arbaz Rentals", "12345", 6.0, 2, 3, 8, 9);

        assertEquals("Arbaz Rentals", company.getName());
        assertEquals("12345", company.getTaxID());
        assertEquals(6.0, company.getMgmFeePer(), 0.001);
        assertEquals("2,3,8,9", company.getPlot().toString());
        assertEquals(0, company.getPropertiesCount());
        assertEquals(5, company.getProperties().length);
    }

    @Test
    public void testCopyConstructor() {
        ManagementCompany original =
                new ManagementCompany("Arbaz Rentals", "12345", 6.0);

        original.addProperty(
                "Oak House", "Rockville", 2000, "Ali",
                1, 1, 2, 2);

        ManagementCompany copy = new ManagementCompany(original);

        assertNotSame(original, copy);
        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getTaxID(), copy.getTaxID());
        assertEquals(original.getMgmFeePer(),
                     copy.getMgmFeePer(), 0.001);
        assertEquals(original.toString(), copy.toString());
        assertEquals(1, copy.getPropertiesCount());

        assertNotSame(original.getPlot(), copy.getPlot());
        assertNotSame(original.getProperties(), copy.getProperties());
        assertNotSame(original.getProperties()[0],
                      copy.getProperties()[0]);
        assertNotSame(original.getProperties()[0].getPlot(),
                      copy.getProperties()[0].getPlot());

        original.getPlot().setX(5);
        original.getProperties()[0].getPlot().setX(7);
        original.removeLastProperty();

        assertEquals(0, copy.getPlot().getX());
        assertEquals(1, copy.getProperties()[0].getPlot().getX());
        assertEquals(1, copy.getPropertiesCount());
    }

    @Test
    public void testAddPropertyObject() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        Property property = new Property(
                "Oak House", "Rockville", 2000, "Ali",
                1, 1, 2, 2);

        assertEquals(0, company.addProperty(property));
        assertEquals(1, company.getPropertiesCount());

        assertNotSame(property, company.getProperties()[0]);
        assertNotSame(property.getPlot(),
                      company.getProperties()[0].getPlot());

        property.getPlot().setX(8);
        assertEquals(1, company.getProperties()[0].getPlot().getX());
    }

    @Test
    public void testAddPropertyWithoutPlot() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        assertEquals(0, company.addProperty(
                "Oak House", "Rockville", 2000, "Ali"));

        assertEquals("0,0,1,1",
                     company.getProperties()[0].getPlot().toString());

        // Both properties would use the same default plot.
        assertEquals(-4, company.addProperty(
                "Maple House", "Bethesda", 2500, "Sara"));

        assertEquals(1, company.getPropertiesCount());
    }

    @Test
    public void testAddPropertyWithPlot() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        assertEquals(0, company.addProperty(
                "Oak House", "Rockville", 2000, "Ali",
                0, 0, 2, 2));

        // Touching an existing edge is allowed.
        assertEquals(1, company.addProperty(
                "Maple House", "Bethesda", 2500, "Sara",
                2, 0, 2, 2));

        // Touching the company boundary is allowed.
        assertEquals(2, company.addProperty(
                "Pine House", "Gaithersburg", 3000, "Omar",
                8, 8, 2, 2));

        assertEquals(3, company.getPropertiesCount());
    }

    @Test
    public void testAddNullProperty() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        assertEquals(-2, company.addProperty((Property) null));
        assertEquals(0, company.getPropertiesCount());
    }

    @Test
    public void testAddPropertyOutsideCompanyPlot() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        assertEquals(-3, company.addProperty(
                "Outside Right", "Rockville", 2000, "Ali",
                9, 1, 2, 2));

        assertEquals(-3, company.addProperty(
                "Outside Left", "Rockville", 2000, "Ali",
                -1, 1, 2, 2));

        assertEquals(-3, company.addProperty(
                "Outside Top", "Rockville", 2000, "Ali",
                1, -1, 2, 2));

        assertEquals(-3, company.addProperty(
                "Outside Bottom", "Rockville", 2000, "Ali",
                1, 9, 2, 2));

        assertEquals(0, company.getPropertiesCount());
    }

    @Test
    public void testAddOverlappingProperty() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        company.addProperty(
                "First", "Rockville", 2000, "Ali",
                0, 0, 2, 2);

        company.addProperty(
                "Second", "Bethesda", 2500, "Sara",
                5, 5, 3, 3);

        // Overlaps the second property.
        assertEquals(-4, company.addProperty(
                "Overlap", "Rockville", 3000, "Omar",
                6, 6, 2, 2));

        assertEquals(2, company.getPropertiesCount());
    }

    @Test
    public void testAddPropertyWhenFull() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        for (int i = 0; i < 5; i++) {
            assertEquals(i, company.addProperty(
                    "House " + i, "Rockville",
                    1000 + i * 100, "Owner",
                    i * 2, 0, 2, 2));
        }

        assertEquals(-1, company.addProperty(
                "Sixth House", "Bethesda", 3000, "Sara",
                0, 3, 2, 2));

        assertEquals(5, company.getPropertiesCount());
    }

    @Test
    public void testRemoveLastProperty() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        // Removing from an empty company is safe.
        company.removeLastProperty();
        assertEquals(0, company.getPropertiesCount());

        company.addProperty(
                "First", "Rockville", 2000, "Ali",
                0, 0, 2, 2);

        company.addProperty(
                "Second", "Bethesda", 2500, "Sara",
                2, 0, 2, 2);

        company.removeLastProperty();

        assertEquals(1, company.getPropertiesCount());
        assertNull(company.getProperties()[1]);
        assertEquals(2000.0, company.getTotalRent(), 0.001);

        // The removed position can be reused.
        assertEquals(1, company.addProperty(
                "Replacement", "Bethesda", 3000, "Omar",
                2, 0, 2, 2));
    }

    @Test
    public void testIsPropertiesFull() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        assertFalse(company.isPropertiesFull());

        for (int i = 0; i < 5; i++) {
            company.addProperty(
                    "House " + i, "Rockville", 1000, "Owner",
                    i * 2, 0, 2, 2);
        }

        assertTrue(company.isPropertiesFull());

        company.removeLastProperty();
        assertFalse(company.isPropertiesFull());
    }

    @Test
    public void testGetPropertiesCount() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        assertEquals(0, company.getPropertiesCount());

        company.addProperty(
                "First", "Rockville", 2000, "Ali",
                0, 0, 2, 2);

        assertEquals(1, company.getPropertiesCount());

        company.addProperty((Property) null);
        assertEquals(1, company.getPropertiesCount());

        company.removeLastProperty();
        assertEquals(0, company.getPropertiesCount());
    }

    @Test
    public void testGetTotalRent() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        assertEquals(0.0, company.getTotalRent(), 0.001);

        company.addProperty(
                "First", "Rockville", 2000.25, "Ali",
                0, 0, 2, 2);

        company.addProperty(
                "Second", "Bethesda", 2500.50, "Sara",
                2, 0, 2, 2);

        assertEquals(4500.75, company.getTotalRent(), 0.001);
    }

    @Test
    public void testGetHighestRentProperty() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        assertNull(company.getHighestRentProperty());

        company.addProperty(
                "First", "Rockville", 2000, "Ali",
                0, 0, 2, 2);

        company.addProperty(
                "Highest", "Bethesda", 3500, "Sara",
                2, 0, 2, 2);

        company.addProperty(
                "Third", "Gaithersburg", 2500, "Omar",
                4, 0, 2, 2);

        assertEquals("Highest",
                     company.getHighestRentProperty().getPropertyName());

        assertEquals(3500.0,
                     company.getHighestRentProperty().getRentAmount(),
                     0.001);

        company.removeLastProperty();
        company.removeLastProperty();

        assertEquals("First",
                     company.getHighestRentProperty().getPropertyName());
    }

    @Test
    public void testIsMangementFeeValid() {
        assertTrue(new ManagementCompany("A", "1", 0)
                .isMangementFeeValid());

        assertTrue(new ManagementCompany("A", "1", 100)
                .isMangementFeeValid());

        assertTrue(new ManagementCompany("A", "1", 6)
                .isMangementFeeValid());

        assertFalse(new ManagementCompany("A", "1", -0.1)
                .isMangementFeeValid());

        assertFalse(new ManagementCompany("A", "1", 100.1)
                .isMangementFeeValid());
    }

    @Test
    public void testGetName() {
        ManagementCompany company =
                new ManagementCompany("Arbaz Rentals", "123", 5);

        assertEquals("Arbaz Rentals", company.getName());
    }

    @Test
    public void testGetTaxID() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123456789", 5);

        assertEquals("123456789", company.getTaxID());
    }

    @Test
    public void testGetProperties() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 5);

        assertEquals(5, company.getProperties().length);
        assertNull(company.getProperties()[0]);

        company.addProperty(
                "Oak House", "Rockville", 2000, "Ali",
                0, 0, 2, 2);

        assertEquals("Oak House",
                     company.getProperties()[0].getPropertyName());
        assertNull(company.getProperties()[1]);
    }

    @Test
    public void testGetMgmFeePer() {
        ManagementCompany company =
                new ManagementCompany("Rentals", "123", 6.5);

        assertEquals(6.5, company.getMgmFeePer(), 0.001);
    }

    @Test
    public void testGetPlot() {
        ManagementCompany company = new ManagementCompany(
                "Rentals", "123", 5, 1, 2, 8, 7);

        assertNotNull(company.getPlot());
        assertEquals("1,2,8,7", company.getPlot().toString());
    }

    @Test
    public void testToString() {
        ManagementCompany company =
                new ManagementCompany("Railey", "555555555", 6);

        company.addProperty(
                "Sunsational", "Beckman", 2613.0,
                "BillyBob Wilson", 2, 5, 2, 2);

        String expected =
                "List of the properties for Railey, taxID: 555555555\n"
                + "______________________________________________________\n"
                + "Sunsational,Beckman,BillyBob Wilson,2613.0\n"
                + "______________________________________________________\n"
                + "\n"
                + " total management Fee: 156.78";

        assertEquals(expected, company.toString());
    }
}
