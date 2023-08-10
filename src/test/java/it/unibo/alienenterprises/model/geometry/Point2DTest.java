package it.unibo.alienenterprises.model.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test Class for Point2D.
 */
final class Point2DTest {

    private static final double TOLL = 0.01;
    private static final Point2D P1 = new Point2D(0, 0);
    private static final Point2D P2 = new Point2D(3, 4);
    private static final Point2D P3 = new Point2D(10, 0);
    private static final int D4 = 10;
    private static final int D3 = 5;
    private static final int D2 = 8;
    private static final int D1 = 9;

    /**
     * Test the distanceFrom method.
     */
    @Test
    void distanceTest() {
        final var p1 = P1;
        final var p2 = P2;
        final var p3 = P3;

        assertEquals(D3, p1.distanceFrom(p2), TOLL);
        assertEquals(D4, p1.distanceFrom(p3), TOLL);
        assertTrue(p2.distanceFrom(p3) < D1);
        assertTrue(p2.distanceFrom(p3) > D2);
    }

}
