

/*
 * Class: CMSC203
 * Assignment: 4
 * Description: Stores rental property information and its plot.
 * Name: Arbaz Saghir
 */

public class Property {

    private String propertyName;
    private String city;
    private double rentAmount;
    private String owner;
    private Plot plot;

    public Property() {
        this("", "", 0.0, "");
    }

    public Property(String propertyName, String city,
                    double rentAmount, String owner) {
        this(propertyName, city, rentAmount, owner, 0, 0, 1, 1);
    }

    public Property(String propertyName, String city,
                    double rentAmount, String owner,
                    int x, int y, int width, int depth) {

        this.propertyName = propertyName;
        this.city = city;
        this.rentAmount = rentAmount;
        this.owner = owner;
        this.plot = new Plot(x, y, width, depth);
    }

    // Creates an independent copy, including a new Plot.
    public Property(Property otherProperty) {
        this(otherProperty.propertyName,
             otherProperty.city,
             otherProperty.rentAmount,
             otherProperty.owner,
             otherProperty.plot.getX(),
             otherProperty.plot.getY(),
             otherProperty.plot.getWidth(),
             otherProperty.plot.getDepth());
    }

    public Plot getPlot() {
        return plot;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getCity() {
        return city;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return propertyName + "," + city + ","
                + owner + "," + rentAmount;
    }
}