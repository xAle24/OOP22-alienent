package it.unibo.alienenterprises.model.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.model.api.GameObjectAbs;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.HitboxComponent.Type;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.components.hitbox.BomberHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleProjectileHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleShipHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.BoundaryHitbox.LeftBoundaryHitboxComponent;
import it.unibo.alienenterprises.model.impl.components.hitbox.BoundaryHitbox.LowerBoundaryHitboxComponent;
import it.unibo.alienenterprises.model.impl.components.hitbox.BoundaryHitbox.RightBoundaryHitboxComponent;
import it.unibo.alienenterprises.model.impl.components.hitbox.BoundaryHitbox.UpperBoundaryHitboxComponent;

public class HitboxTest {

    @Test
    void canCollides() {
        Map<Statistic, Integer> statmap1 = new HashMap<>();
        statmap1.put(Statistic.HP, 100);
        statmap1.put(Statistic.DAMAGE, 1);
        Map<Statistic, Integer> statmap2 = new HashMap<>();
        statmap2.put(Statistic.HP, 1);
        statmap2.put(Statistic.DAMAGE, 15);
        Map<Statistic, Integer> statmap3 = new HashMap<>();
        statmap3.put(Statistic.HP, 100);
        statmap3.put(Statistic.DAMAGE, 1);
        var obj1 = new GameObjectAbs(new Point2D(2, 0), null, statmap1);
        var obj2 = new GameObjectAbs(new Point2D(5, 0), null, statmap2);
        var obj3 = new GameObjectAbs(new Point2D(10, 0), null, statmap3);
        var hitbox1 = new BomberHitboxComponentImpl(obj1, true, Type.ENEMY, 2);
        var hitbox2 = new SimpleProjectileHitboxComponentImpl(obj2, true, Type.PROJECTILE, 2);
        var hitbox3 = new SimpleShipHitboxComponentImpl(obj3, true, Type.PLAYER, 2);
        obj1.addComponent(hitbox1);
        obj2.addComponent(hitbox2);
        obj3.addComponent(hitbox3);
        assertTrue(hitbox1.canCollide(Type.PROJECTILE));
        assertTrue(hitbox1.canCollide(Type.PLAYER));
        assertFalse(hitbox1.canCollide(Type.ENEMY));
        assertTrue(hitbox2.canCollide(Type.ENEMY));
        assertTrue(hitbox2.canCollide(Type.PLAYER));
        assertFalse(hitbox2.canCollide(Type.PROJECTILE));
        assertTrue(hitbox3.canCollide(Type.PROJECTILE));
        assertTrue(hitbox3.canCollide(Type.ENEMY));
        assertFalse(hitbox3.canCollide(Type.PLAYER));
    }

    @Test
    void types() {
        Map<Statistic, Integer> statmap1 = new HashMap<>();
        statmap1.put(Statistic.HP, 100);
        statmap1.put(Statistic.DAMAGE, 1);
        Map<Statistic, Integer> statmap2 = new HashMap<>();
        statmap2.put(Statistic.HP, 1);
        statmap2.put(Statistic.DAMAGE, 15);
        Map<Statistic, Integer> statmap3 = new HashMap<>();
        statmap3.put(Statistic.HP, 100);
        statmap3.put(Statistic.DAMAGE, 1);
        var obj1 = new GameObjectAbs(new Point2D(2, 0), null, statmap1);
        var obj2 = new GameObjectAbs(new Point2D(5, 0), null, statmap2);
        var obj3 = new GameObjectAbs(new Point2D(10, 0), null, statmap3);
        var hitbox1 = new BomberHitboxComponentImpl(obj1, true, Type.ENEMY, 2);
        var hitbox2 = new SimpleProjectileHitboxComponentImpl(obj2, true, Type.PROJECTILE, 2);
        var hitbox3 = new SimpleShipHitboxComponentImpl(obj3, true, Type.PLAYER, 2);
        obj1.addComponent(hitbox1);
        obj2.addComponent(hitbox2);
        obj3.addComponent(hitbox3);
        assertEquals(Type.ENEMY, hitbox1.getType());
        assertEquals(Type.PROJECTILE, hitbox2.getType());
        assertEquals(Type.PLAYER, hitbox3.getType());
        assertNotEquals(hitbox1.getType(), Type.PROJECTILE);
        assertNotEquals(hitbox1.getType(), Type.PLAYER);
        assertNotEquals(hitbox2.getType(), Type.ENEMY);
        assertNotEquals(hitbox2.getType(), Type.PLAYER);
        assertNotEquals(hitbox3.getType(), Type.ENEMY);
        assertNotEquals(hitbox3.getType(), Type.PROJECTILE);
    }
     
    @Test
    void collidesHitbox() {
        Map<Statistic, Integer> statmap1 = new HashMap<>();
        statmap1.put(Statistic.HP, 100);
        statmap1.put(Statistic.DAMAGE, 1);
        Map<Statistic, Integer> statmap2 = new HashMap<>();
        statmap2.put(Statistic.HP, 1);
        statmap2.put(Statistic.DAMAGE, 15);
        Map<Statistic, Integer> statmap3 = new HashMap<>();
        statmap3.put(Statistic.HP, 100);
        statmap3.put(Statistic.DAMAGE, 1);
        var obj1 = new GameObjectAbs(new Point2D(2, 0), null, statmap1);
        var obj2 = new GameObjectAbs(new Point2D(5, 0), null, statmap2);
        var obj3 = new GameObjectAbs(new Point2D(10, 0), null, statmap3);
        var hitbox1 = new BomberHitboxComponentImpl(obj1, true, Type.ENEMY, 2);
        var hitbox2 = new SimpleProjectileHitboxComponentImpl(obj2, true, Type.PROJECTILE, 2);
        var hitbox3 = new SimpleShipHitboxComponentImpl(obj3, true, Type.PLAYER, 2);
        obj1.addComponent(hitbox1);
        obj2.addComponent(hitbox2);
        obj3.addComponent(hitbox3);
        hitbox2.isColliding(obj3);        
        assertEquals(85, obj3.gethealth());
        hitbox2.isColliding(obj1);
        assertEquals(85, obj1.gethealth());
        hitbox1.isColliding(obj2);
        assertEquals(0, obj2.gethealth());
        hitbox1.isColliding(obj3);
        assertEquals(-914, obj3.gethealth());
        hitbox3.isColliding(obj1);
        assertEquals(85, obj1.gethealth());
        hitbox3.isColliding(obj2);
        assertEquals(-1, obj2.gethealth());
    }

    @Test
    void collideBoundary() {
        Map<Statistic, Integer> statmap1 = new HashMap<>();
        statmap1.put(Statistic.HP, 100);
        statmap1.put(Statistic.DAMAGE, 1);
        var obj1 = new GameObjectAbs(new Point2D(2, 0), null, statmap1);
        var obj2 = new GameObjectAbs(new Point2D(5, 0), null, statmap1);
        var obj3 = new GameObjectAbs(new Point2D(10, 0), null, statmap1);
        var wallUp = new GameObjectAbs(new Point2D(0, 0), null, statmap1);
        var wallRight = new GameObjectAbs(new Point2D(0, 0), null, statmap1);
        var wallDown = new GameObjectAbs(new Point2D(0, 0), null, statmap1);
        var wallLeft = new GameObjectAbs(new Point2D(0, 0), null, statmap1);
        var hitbox1 = new BomberHitboxComponentImpl(obj1, true, Type.ENEMY, 2);
        var hitbox2 = new SimpleProjectileHitboxComponentImpl(obj2, true, Type.PROJECTILE, 2);
        var hitbox3 = new SimpleShipHitboxComponentImpl(obj3, true, Type.PLAYER, 2);
        var hitboxUp = new UpperBoundaryHitboxComponent(wallUp, true, Type.BOUNDARY, new Point2D(0, 0), new Point2D(1, 1));
        var hitboxRight = new RightBoundaryHitboxComponent(wallRight, true, Type.BOUNDARY, new Point2D(0, 0), new Point2D(1, 1));
        var hitboxDown = new LowerBoundaryHitboxComponent(wallDown, true, Type.BOUNDARY, new Point2D(0, 0), new Point2D(1, 1));
        var hitboxLeft = new LeftBoundaryHitboxComponent(wallLeft, true, Type.BOUNDARY, new Point2D(0, 0), new Point2D(1, 1));
        obj1.addComponent(hitbox1);
        obj2.addComponent(hitbox2);
        obj3.addComponent(hitbox3);
        wallUp.addComponent(hitboxUp);
        wallRight.addComponent(hitboxRight);
        wallDown.addComponent(hitboxDown);
        wallLeft.addComponent(hitboxLeft);
        hitboxUp.isColliding(obj2);
        assertEquals(-899, obj2.gethealth());
        obj2.heal(999);
        hitboxRight.isColliding(obj2);
        assertEquals(-899, obj2.gethealth());
        obj2.heal(999);
        hitboxDown.isColliding(obj2);
        assertEquals(-899, obj2.gethealth());
        obj2.heal(999);
        hitboxLeft.isColliding(obj2);
        assertEquals(-899, obj2.gethealth());
        //upper boundary
        obj1.setVelocity(new Vector2D(1, 1));
        obj3.setVelocity(new Vector2D(1, 1));
        hitboxUp.isColliding(obj1);
        assertEquals(0.0, obj1.getVelocity().getyComp());
        hitboxUp.isColliding(obj3);
        assertEquals(0.0, obj3.getVelocity().getyComp());
        //lower boundary
        obj1.setVelocity(new Vector2D(1, 1));
        obj3.setVelocity(new Vector2D(1, 1));
        hitboxDown.isColliding(obj1);
        assertEquals(0.0, obj1.getVelocity().getyComp());
        hitboxDown.isColliding(obj3);
        assertEquals(0.0, obj3.getVelocity().getyComp());
        //left boundary
        obj1.setVelocity(new Vector2D(1, 1));
        obj3.setVelocity(new Vector2D(1, 1));
        hitboxLeft.isColliding(obj1);
        assertEquals(0.0, obj1.getVelocity().getxComp());
        hitboxLeft.isColliding(obj3);
        assertEquals(0.0, obj3.getVelocity().getxComp());
        //right boundary
        obj1.setVelocity(new Vector2D(1, 1));
        obj3.setVelocity(new Vector2D(1, 1));
        hitboxRight.isColliding(obj1);
        assertEquals(0.0, obj1.getVelocity().getxComp());
        hitboxRight.isColliding(obj3);
        assertEquals(0.0, obj3.getVelocity().getxComp());
    }
}
