package it.unibo.alienenterprises.model.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test Class for Line2D.
 */
public final class Line2DTest {

    private final Line2D line1 = new Line2D(-1, 1, 0);
    private final Line2D line2 = new Line2D(0, 1, 0);

    private Line2DTest() {
    }

    /**
     * Test if the constructor throws the correct Exeption.
     */
    @Test
    public void constructorTest() {
        assertThrows(IllegalArgumentException.class, () -> new Line2D(0, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Line2D(0, 0, 1));
    }

    /**
     * Test the construction from two Points.
     */
    @Test
    public void fromTwoPointsTest() {
        final Point2D p1 = new Point2D(0, 0);
        final Point2D p2 = new Point2D(1, 0);
        final Point2D p3 = new Point2D(1, 1);
        Line2D x = Line2D.fromTwoPoints(p1, p2);
        Line2D xy = Line2D.fromTwoPoints(p1, p3);

        assertEquals(line1, xy);
        assertEquals(line2, x);

        assertThrows(IllegalArgumentException.class, () -> Line2D.fromTwoPoints(p1, p1));
    }

}
