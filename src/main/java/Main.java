import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Point2D> base = new ArrayList<>();
        base.add(new Point2D.Double(2, 2.7));
        base.add(new Point2D.Double(1, 2.5));
        base.add(new Point2D.Double(3, 4));
        Point2D pt1 = new Point2D.Double(0, 3);
        Point2D pt2 = new Point2D.Double(3, 9);

        RegLinear r1 = new RegLinear(0.05, 2.0, 3.0, base, pt1, pt2);
    }
}
