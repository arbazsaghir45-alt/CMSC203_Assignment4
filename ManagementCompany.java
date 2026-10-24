

/*
 * Class: CMSC203
 * Assignment: 4
 * Description: Manages rental properties and calculates rent and fees.
 * Name: Arbaz Saghir
 */
public class ManagementCompany {

    public static final int MAX_PROPERTY = 5;
    public static final int MGMT_WIDTH = 10;
    public static final int MGMT_DEPTH = 10;

    private String name;
    private String taxID;
    private Property[] properties;
    private double mgmFeePer;
    private Plot plot;
    private int numberOfProperties;

    public ManagementCompany() {
        this("", "", 0.0);
    }

    public ManagementCompany(String name, String taxID,
                             double mgmFee) {
        this.name = name;
        this.taxID = taxID;
        this.mgmFeePer = mgmFee;
        this.properties = new Property[MAX_PROPERTY];
        this.plot = new Plot(0, 0, MGMT_WIDTH, MGMT_DEPTH);
        this.numberOfProperties = 0;
    }

    public ManagementCompany(String name, String taxID,
                             double mgmFee,
                             int x, int y, int width, int depth) {
        this(name, taxID, mgmFee);
        this.plot = new Plot(x, y, width, depth);
    }

    public ManagementCompany(ManagementCompany otherCompany) {
        this(otherCompany.name,
             otherCompany.taxID,
             otherCompany.mgmFeePer,
             otherCompany.plot.getX(),
             otherCompany.plot.getY(),
             otherCompany.plot.getWidth(),
             otherCompany.plot.getDepth());

        this.numberOfProperties = otherCompany.numberOfProperties;

        for (int i = 0; i < numberOfProperties; i++) {
            this.properties[i] =
                    new Property(otherCompany.properties[i]);
        }
    }

    public int addProperty(String name, String city,
                           double rent, String owner) {
        return addProperty(new Property(name, city, rent, owner));
    }

    public int addProperty(String name, String city,
                           double rent, String owner,
                           int x, int y, int width, int depth) {
        return addProperty(new Property(
                name, city, rent, owner, x, y, width, depth));
    }

    public int addProperty(Property property) {

        if (isPropertiesFull()) {
            return -1;
        }

        if (property == null) {
            return -2;
        }

        if (!plot.encompasses(property.getPlot())) {
            return -3;
        }

        for (int i = 0; i < numberOfProperties; i++) {
            if (properties[i].getPlot().overlaps(property.getPlot())) {
                return -4;
            }
        }

        int index = numberOfProperties;
        properties[index] = new Property(property);
        numberOfProperties++;

        return index;
    }

    public void removeLastProperty() {
        if (numberOfProperties > 0) {
            numberOfProperties--;
            properties[numberOfProperties] = null;
        }
    }

    public boolean isPropertiesFull() {
        return numberOfProperties == MAX_PROPERTY;
    }

    public int getPropertiesCount() {
        return numberOfProperties;
    }

    public double getTotalRent() {
        double total = 0.0;

        for (int i = 0; i < numberOfProperties; i++) {
            total += properties[i].getRentAmount();
        }

        return total;
    }

    public Property getHighestRentProperty() {
        int index = getHighestRentPropertyIndex();

        if (index == -1) {
            return null;
        }

        return properties[index];
    }

    private int getHighestRentPropertyIndex() {
        if (numberOfProperties == 0) {
            return -1;
        }

        int highestIndex = 0;

        for (int i = 1; i < numberOfProperties; i++) {
            if (properties[i].getRentAmount()
                    > properties[highestIndex].getRentAmount()) {
                highestIndex = i;
            }
        }

        return highestIndex;
    }

    // Spelling matches the provided Javadoc.
    public boolean isMangementFeeValid() {
        return mgmFeePer >= 0 && mgmFeePer <= 100;
    }

    public String getName() {
        return name;
    }

    public String getTaxID() {
        return taxID;
    }

    public Property[] getProperties() {
        return properties;
    }

    public double getMgmFeePer() {
        return mgmFeePer;
    }

    public Plot getPlot() {
        return plot;
    }

    @Override
    public String toString() {
        String report = "List of the properties for "
                + name + ", taxID: " + taxID + "\n";

        report += "______________________________________________________\n";

        for (int i = 0; i < numberOfProperties; i++) {
            report += properties[i].toString() + "\n";
        }

        report += "______________________________________________________\n";
        report += "\n";

        double managementFee = getTotalRent() * mgmFeePer / 100.0;

        report += " total management Fee: "
                + String.format(java.util.Locale.US,
                                "%.2f", managementFee);

        return report;
    }
}
