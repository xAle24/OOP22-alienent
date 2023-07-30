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
import it.unibo.alienenterprises.model.impl.components.hitbox.BomberHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleProjectileHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleShipHitboxComponentImpl;

public class HitboxTest {

    @Test
    void canCollides() {
        var hitbox1 = new SimpleShipHitboxComponentImpl(null, true, Type.ENEMY);
        var hitbox2 = new SimpleShipHitboxComponentImpl(null, true, Type.PROJECTILE);
        var hitbox3 = new SimpleShipHitboxComponentImpl(null, true, Type.PLAYER);
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
        var hitbox1 = new SimpleShipHitboxComponentImpl(null, true, Type.ENEMY);
        var hitbox2 = new SimpleShipHitboxComponentImpl(null, true, Type.PROJECTILE);
        var hitbox3 = new SimpleShipHitboxComponentImpl(null, true, Type.PLAYER);
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
        var hitbox1 = new BomberHitboxComponentImpl(obj1, true, Type.ENEMY);
        var hitbox2 = new SimpleProjectileHitboxComponentImpl(obj2, true, Type.PROJECTILE);
        var hitbox3 = new SimpleShipHitboxComponentImpl(obj3, true, Type.PLAYER);
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
}
