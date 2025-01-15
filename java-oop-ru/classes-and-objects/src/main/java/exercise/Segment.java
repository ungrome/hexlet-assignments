package exercise;

// BEGIN
public class Segment {
    private final Point beginPoint;
    private final Point endPoint;


    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return this.beginPoint;
    }

    public Point getEndPoint() {
        return this.endPoint;
    }

    public Point getMidPoint() {
        int xMidPoint = (beginPoint.getX() + endPoint.getX()) / 2;
        int yMidpoint = (beginPoint.getY() + endPoint.getY()) / 2;
        return new Point(xMidPoint, yMidpoint);
    }
}
// END
