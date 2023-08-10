package it.unibo.alienenterprises.model.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Point2DTest {

    private Point2DTest(){

    }

    @Test
    public void distanceTest(){
        var p1 = new Point2D(0, 0);
        var p2 = new Point2D(3, 4);
        var p3 = new Point2D(10, 0);

        assertEquals(5, p1.distanceFrom(p2), 0.01);
        assertEquals(10, p1.distanceFrom(p3),0.01);
        assertTrue(p2.distanceFrom(p3) < 9);
        assertTrue(p2.distanceFrom(p3) > 8);
    }
    
}
