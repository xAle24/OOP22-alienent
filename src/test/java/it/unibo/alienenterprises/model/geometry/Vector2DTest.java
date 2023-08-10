package it.unibo.alienenterprises.model.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

public class Vector2DTest {

    private static final double TOLL = 0.01;

    private Vector2DTest(){

    }

    @Test
    public void fromAngleAndModuleTest(){
        Vector2D vec = Vector2D.fromAngleAndModule(45, 1);
        assertEquals(45,vec.getAngle());
        assertEquals(1,vec.getModule());

        vec = Vector2D.fromAngleAndModule(380, 1);
        assertEquals(20, vec.getAngle());


        vec = Vector2D.fromAngleAndModule(-380, 1);
        assertEquals(340, vec.getAngle());
    }

    @Test
    public void fromComponentsTest(){
        Vector2D vec1 = Vector2D.fromAngleAndModule(90, 1);
        Vector2D test = Vector2D.fromComponents(0, 1);
        assertEquals(vec1,test);
        assertEquals(0,test.getxComp(),TOLL);
        assertEquals(1,test.getyComp(),TOLL);

        vec1 = Vector2D.fromAngleAndModule(0, 1);
        test = Vector2D.fromComponents(1, 0);
        assertEquals(vec1,test);
        assertEquals(1,test.getxComp(),TOLL);
        assertEquals(0,test.getyComp(),TOLL);

        vec1 = Vector2D.fromAngleAndModule(180, 1);
        test = Vector2D.fromComponents(-1, 0);
        assertEquals(vec1,test);
        assertEquals(-1,test.getxComp(),TOLL);
        assertEquals(0,test.getyComp(),TOLL);
    }

    @Test
    public void fromTwoPointsTest(){
        Point2D a = new Point2D(0, 0);
        Point2D b = new Point2D(345, 25);
        Vector2D vec = Vector2D.fromTwoPoints(a, b);
        assertEquals(b.getX(),vec.translate(a).getX(),TOLL);
        assertEquals(b.getY(),vec.translate(a).getY(),TOLL);
    }

    @Test
    public void fromTwoPointsAndModuleTest(){
        Point2D a = new Point2D(0, 0);
        Point2D b = new Point2D(345, 0);
        Vector2D vec = Vector2D.fromTwoPointsAndModule(a, b, 1);
        assertEquals(b,vec.mul(345).translate(a));
    }

    @Test
    public void translatePointTest() {
        var vec1 = Vector2D.fromComponents(5, 5);
        var vec2 = Vector2D.fromComponents(-2, -1);
        var vec3 = Vector2D.fromComponents(-1, 2);

        Point2D a = new Point2D(0, 0);

        Point2D b = new Point2D(5, 5);
        assertEquals(b.getX(), vec1.translate(a).getX(), TOLL);
        assertEquals(b.getY(), vec1.translate(a).getY(), TOLL);

        b = new Point2D(-2, -1);
        assertEquals(b.getX(), vec2.translate(a).getX(), TOLL);
        assertEquals(b.getY(), vec2.translate(a).getY(), TOLL);

        b = new Point2D(-1, 2);
        assertEquals(b.getX(), vec3.translate(a).getX(), TOLL);
        assertEquals(b.getY(), vec3.translate(a).getY(), TOLL);

        b = new Point2D(2, 6);
        var at = vec3.translate(vec2.translate(vec1.translate(a)));
        assertEquals(b.getX(), at.getX(), TOLL);
        assertEquals(b.getY(), at.getY(), TOLL);
    }


}
