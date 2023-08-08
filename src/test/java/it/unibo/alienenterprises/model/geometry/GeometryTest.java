package it.unibo.alienenterprises.model.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GeometryTest {

    @Test
    void pointDistance() {
        var p1 = new Point2D(0, 0);
        var p2 = new Point2D(3, 4);
        var p3 = new Point2D(10, 0);

        assertEquals(5, p1.distanceFrom(p2));
        assertEquals(10, p1.distanceFrom(p3));
        assertTrue(p2.distanceFrom(p3) < 9);
        assertTrue(p2.distanceFrom(p3) > 8);
    }

    @Test
    void circleIntersect() {
        var c1 = new Circle2D(new Point2D(0, 0), 4);
        var c2 = new Circle2D(new Point2D(1, 1), 20);
        var c3 = new Circle2D(new Point2D(1, 20), 1);
        assertTrue(c1.intersectWhith(c2));
        assertTrue(c2.intersectWhith(c1));
        assertTrue(c2.intersectWhith(c3));
        assertTrue(c3.intersectWhith(c2));
        assertFalse(c3.intersectWhith(c1));
        assertFalse(c1.intersectWhith(c3));
    }

    @Test
    void translatePointTest() {
        var vec1 = Vector2D.fromComponents(5, 5);
        var vec2 = Vector2D.fromComponents(-2, -1);
        var vec3 = Vector2D.fromComponents(-1, 2);

        var p = new Point2D(0, 0);
        assertEquals(new Point2D(5, 5), vec1.translate(p));
        assertEquals(new Point2D(-2, -1), vec2.translate(p));
        assertEquals(new Point2D(-1, 2), vec3.translate(p));

        assertEquals(new Point2D(2, 6), vec3.translate(vec2.translate(vec1.translate(p))));
    }

    @Test
    void fromAngleAndModuleTest() {
        final double ang1 = 45;
        final double ang2 = 90;
        final double ang3 = 270;

        final double module = 10;

        var vec1 = Vector2D.fromAngleAndModule(ang1, module);
        assertEquals(module, vec1.getModule());
        assertEquals(ang1, vec1.getAngle());

        var vec2 = Vector2D.fromAngleAndModule(ang2, module);
        assertEquals(module, vec2.getModule());
        assertEquals(ang2, vec2.getAngle());

        var vec3 = Vector2D.fromAngleAndModule(ang3, module);
        assertEquals(module, vec3.getModule());
        assertEquals(ang3, vec3.getAngle());
    }

    @Test
    void vectorAdditionTest() {
        var vec1 = Vector2D.fromComponents(5, 5);
        var vec2 = Vector2D.fromComponents(-2, -1);
        var vec3 = Vector2D.fromComponents(-1, 2);

        assertEquals(Vector2D.fromComponents(3, 4), vec1.add(vec2));
        assertEquals(Vector2D.fromComponents(4, 7), vec1.add(vec3));
        assertEquals(Vector2D.fromComponents(2, 6), vec1.add(vec2).add(vec3));
    }

    @Test
    void vectorFromTwoPoints(){
        var a = new Point2D(102.17201533801102, 208.48643612629408);
        var b = new Point2D(337.5, 171.0);
        var xComp = b.getX() - a.getX();
        var yComp = b.getY() - a.getY();
        System.out.println(xComp);
        System.out.println(yComp);

        System.out.println(Math.sqrt(xComp * xComp + yComp * yComp));

        var v1 = Vector2D.fromTwoPoints(a, b);
        System.out.println(v1);
        System.out.println(v1.getAngle());
    }

}
