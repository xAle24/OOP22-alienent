package it.unibo.alienenterprises.model.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test Class for Vector2D.
 */
final class Vector2DTest {
    private static final int ANG_TEST2 = 340;
    private static final int ANG_TEST1 = 20;
    private static final int MOD1 = 1;
    private static final double TOLL = 0.01;
    private static final int ANG1 = 45;
    private static final int ANG2 = 380;
    private static final int ANG3 = 180;
    private static final int RIGHT_ANGLE = 90;
    private static final int X1 = 345;
    private static final int X2 = 2;
    private static final int Y1 = 25;
    private static final int Y2 = 6;
    private static final int COMP5 = 5;

    /**
     * Test the construction of a vector2D from Angle and Module.
     */
    @Test
    void fromAngleAndModuleTest() {
        Vector2D vec = Vector2D.fromAngleAndModule(ANG1, MOD1);
        assertEquals(ANG1, vec.getAngle());
        assertEquals(MOD1, vec.getModule());

        vec = Vector2D.fromAngleAndModule(ANG2, MOD1);
        assertEquals(ANG_TEST1, vec.getAngle());

        vec = Vector2D.fromAngleAndModule(-ANG2, MOD1);
        assertEquals(ANG_TEST2, vec.getAngle());
    }

    /**
     * Test the construction of a vector2D from the components.
     */
    @Test
    void fromComponentsTest() {
        Vector2D vec1 = Vector2D.fromAngleAndModule(RIGHT_ANGLE, MOD1);
        Vector2D test = Vector2D.fromComponents(0, MOD1);
        assertEquals(vec1, test);
        assertEquals(0, test.getxComp(), TOLL);
        assertEquals(1, test.getyComp(), TOLL);

        vec1 = Vector2D.fromAngleAndModule(0, MOD1);
        test = Vector2D.fromComponents(1, 0);
        assertEquals(vec1, test);
        assertEquals(1, test.getxComp(), TOLL);
        assertEquals(0, test.getyComp(), TOLL);

        vec1 = Vector2D.fromAngleAndModule(ANG3, MOD1);
        test = Vector2D.fromComponents(-1, 0);
        assertEquals(vec1, test);
        assertEquals(-1, test.getxComp(), TOLL);
        assertEquals(0, test.getyComp(), TOLL);
    }

    /**
     * Test the construction of a vector2D from Two Points.
     */
    @Test
    void fromTwoPointsTest() {
        final Point2D a = Point2D.ORIGIN;
        final Point2D b = new Point2D(X1, Y1);
        final Vector2D vec = Vector2D.fromTwoPoints(a, b);
        assertEquals(b.getX(), vec.translate(a).getX(), TOLL);
        assertEquals(b.getY(), vec.translate(a).getY(), TOLL);
    }

    /**
     * Test the construction of a vector2D from Two Points and module.
     */
    @Test
    void fromTwoPointsAndModuleTest() {
        final Point2D a = new Point2D(0, 0);
        final Point2D b = new Point2D(X1, 0);
        final Vector2D vec = Vector2D.fromTwoPointsAndModule(a, b, 1);
        assertEquals(b, vec.mul(X1).translate(a));
    }

    /**
     * Test the translate method.
     */
    @Test
    void translatePointTest() {
        final var vec1 = Vector2D.fromComponents(COMP5, COMP5);
        final var vec2 = Vector2D.fromComponents(-X2, -1);
        final var vec3 = Vector2D.fromComponents(-1, X2);

        final Point2D a = new Point2D(0, 0);

        Point2D b = new Point2D(COMP5, COMP5);
        assertEquals(b.getX(), vec1.translate(a).getX(), TOLL);
        assertEquals(b.getY(), vec1.translate(a).getY(), TOLL);

        b = new Point2D(-X2, -1);
        assertEquals(b.getX(), vec2.translate(a).getX(), TOLL);
        assertEquals(b.getY(), vec2.translate(a).getY(), TOLL);

        b = new Point2D(-1, X2);
        assertEquals(b.getX(), vec3.translate(a).getX(), TOLL);
        assertEquals(b.getY(), vec3.translate(a).getY(), TOLL);

        b = new Point2D(X2, Y2);
        final var at = vec3.translate(vec2.translate(vec1.translate(a)));
        assertEquals(b.getX(), at.getX(), TOLL);
        assertEquals(b.getY(), at.getY(), TOLL);
    }

}
