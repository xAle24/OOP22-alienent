package it.unibo.alienenterprises.model.geometry;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Circle2DTest {

    private final Circle2D circle1;
    private final Circle2D circle2;
    private final Circle2D circle3;

    private Circle2DTest() {
        this.circle1 = new Circle2D(Point2D.ORIGIN, 5);
        this.circle2 = new Circle2D(new Point2D(5, 5), 5);
        this.circle3 = new Circle2D(new Point2D(10, 10), 5);
    }

    @Test
    public void intersectWithCircle2DTest() {
        assertTrue(circle1.intersectWith(circle2));
        assertTrue(circle2.intersectWith(circle1));
        assertTrue(!circle1.intersectWith(circle3));
        assertTrue(!circle3.intersectWith(circle1));
    }

    @Test
    public void intersectWithLine2DTest() {
        final Line2D line1 = new Line2D(1, 0, 0);
        final Line2D line2 = new Line2D(-1, 1, 0);

        assertTrue(circle1.intersectWith(line1));
        assertTrue(!circle2.intersectWith(line1));
        assertTrue(!circle3.intersectWith(line1));

        assertTrue(circle1.intersectWith(line2));
        assertTrue(circle2.intersectWith(line2));
        assertTrue(circle3.intersectWith(line2));
    }

}
