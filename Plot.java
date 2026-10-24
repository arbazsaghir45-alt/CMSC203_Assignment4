

/*
 * Class: CMSC203
 * Assignment: 4
 * Description: Represents a rectangular plot of land.
 * Name: Arbaz Saghir
 */

public class Plot {

    private int x;
    private int y;
    private int width;
    private int depth;

    // Creates a plot at (0, 0) with size 1 by 1.
    public Plot() {
        this(0, 0, 1, 1);
    }

    public Plot(int x, int y, int width, int depth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.depth = depth;
    }

    public Plot(Plot otherPlot) {
        this(otherPlot.x, otherPlot.y,
             otherPlot.width, otherPlot.depth);
    }

    // Touching edges do not count as overlapping.
    public boolean overlaps(Plot plot) {
        return x < plot.x + plot.width
                && x + width > plot.x
                && y < plot.y + plot.depth
                && y + depth > plot.y;
    }

    // A property may touch the company's boundary.
    public boolean encompasses(Plot plot) {
        return plot.x >= x
                && plot.y >= y
                && plot.x + plot.width <= x + width
                && plot.y + plot.depth <= y + depth;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        return x + "," + y + "," + width + "," + depth;
    }
}
