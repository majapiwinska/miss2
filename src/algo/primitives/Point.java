package algo.primitives;

/**
 * Created by kasiazukowska on 2016-01-10.
 */
public class Point {
    double x;
    double y;

    public Point() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public int getIntX() {
        return (int) x;
    }
    public int getIntY() {
        return (int) y;
    }
}