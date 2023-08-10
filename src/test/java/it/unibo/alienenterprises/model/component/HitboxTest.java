package it.unibo.alienenterprises.model.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.GameObjectAbs;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.BoundaryHitboxComponent.Locations;
import it.unibo.alienenterprises.model.api.components.HitboxComponent.Type;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.impl.components.hitbox.BomberHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.BoundaryHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleProjectileHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleShipHitboxComponentImpl;

public class HitboxTest {
    private static final Map<Statistic, Integer> statmap1 = Map.of(Statistic.HP, 100, Statistic.DAMAGE, 50);
    private static final Map<Statistic, Integer> statmap2 = Map.of(Statistic.HP, 1, Statistic.DAMAGE, 15);
    private static final Map<Statistic, Integer> statmap3 = Map.of(Statistic.HP, 100, Statistic.DAMAGE, 1);
    private static final GameObject obj1 = new GameObjectAbs(new Point2D(2, 0), null, statmap1, null);
    private static final GameObject obj2 = new GameObjectAbs(new Point2D(2, 0), null, statmap2, null);
    private static final GameObject obj3 = new GameObjectAbs(new Point2D(10, 0), null, statmap3, null);
    private static final GameObject obj4 = new GameObjectAbs(new Point2D(10, 10), null, statmap2, null);
    @Test
    void canCollides() {
        var hitbox1 = new BomberHitboxComponentImpl(obj1, true, Type.ENEMY, 2);
        var hitbox2 = new SimpleProjectileHitboxComponentImpl(obj2, true, Type.PROJECTILE, 2);
        hitbox2.setShooter(Type.PLAYER);
        var hitbox3 = new BoundaryHitboxComponentImpl(obj3, true, Type.BOUNDARY, new Point2D(0, 1), new Point2D(1, 1));
        hitbox3.setLocations(Locations.DOWN);
        var hitbox4 = new SimpleProjectileHitboxComponentImpl(obj4, true, Type.PROJECTILE, 2);
        hitbox4.setShooter(Type.ENEMY);
        obj1.addComponent(hitbox1);
        obj2.addComponent(hitbox2);
        obj3.addComponent(hitbox3);
        obj4.addComponent(hitbox4);
        hitbox1.canCollide(hitbox2);
        assertEquals(0, hitbox2.getGameObject().gethealth());
        assertEquals(85, hitbox1.getGameObject().gethealth());
        hitbox4.canCollide(hitbox3);
        assertEquals(100, hitbox3.getGameObject().gethealth());
        hitbox2.canCollide(hitbox3);
        assertEquals(-999, hitbox2.getGameObject().gethealth());
        hitbox1.canCollide(hitbox4);
        assertEquals(85, hitbox1.getGameObject().gethealth());
        assertEquals(1, hitbox4.getGameObject().gethealth());
    }

    @Test
    void types() {
        obj2.setPosition(new Point2D(5, 0));
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
        obj1.setPosition(new Point2D(0, 0));
        obj2.setPosition(new Point2D(0, 0));
        obj3.setPosition(new Point2D(0, 0));
        obj4.setPosition(new Point2D(0, 0));
        obj1.heal(15);
        obj2.heal(1000);
        var hitbox1 = new BomberHitboxComponentImpl(obj1, true, Type.ENEMY, 2);
        var hitbox2 = new SimpleProjectileHitboxComponentImpl(obj2, true, Type.PROJECTILE, 2);
        hitbox2.setShooter(Type.PLAYER);
        var hitbox3 = new SimpleShipHitboxComponentImpl(obj3, true, Type.PLAYER, 2);
        var hitbox4 = new SimpleProjectileHitboxComponentImpl(obj4, true, Type.PROJECTILE, 2);
        hitbox4.setShooter(Type.ENEMY);
        obj1.addComponent(hitbox1);
        obj2.addComponent(hitbox2);
        obj3.addComponent(hitbox3);
        obj4.addComponent(hitbox4);
        //collisioni tra bomber e gli altri oggetti
        hitbox1.isColliding(hitbox2);
        assertEquals(0, obj2.gethealth());
        hitbox1.isColliding(hitbox3);
        obj2.heal(1);
        assertEquals(50, obj3.gethealth());
        obj1.heal(99);
        obj3.heal(50);
        hitbox1.isColliding(hitbox4);
        assertEquals(1, obj4.gethealth());
        //collisioni tra il proiettile sparato dal player e gli altri oggetti
        hitbox2.isColliding(hitbox1);
        assertEquals(85, obj1.gethealth());
        obj1.heal(15);
        hitbox2.isColliding(hitbox3);        
        assertEquals(100, obj3.gethealth());
        hitbox2.isColliding(hitbox4);        
        assertEquals(1, obj4.gethealth());
        //collisioni tra il player e gli altri oggetti
        hitbox3.isColliding(hitbox1);
        assertEquals(100, obj1.gethealth());
        hitbox3.isColliding(hitbox2);
        assertEquals(1, obj2.gethealth());
        hitbox3.isColliding(hitbox4);
        assertEquals(0, obj4.gethealth());
        obj4.heal(1);
        //collisioni tra il proiettile sparato dal nemico e gli altri oggetti
        hitbox4.isColliding(hitbox4);
        assertEquals(100, obj1.gethealth());
        hitbox4.isColliding(hitbox2);
        assertEquals(1, obj2.gethealth());
        hitbox4.isColliding(hitbox3);
        assertEquals(85, obj3.gethealth());
    }

    @Test
    void collideBoundary() {
        Map<Statistic, Integer> statmap1 = new HashMap<>();
        statmap1.put(Statistic.HP, 100);
        statmap1.put(Statistic.DAMAGE, 1);
        var obj1 = new GameObjectAbs(new Point2D(2, 0), null, statmap1, null);
        var obj2 = new GameObjectAbs(new Point2D(5, 0), null, statmap1, null);
        var obj3 = new GameObjectAbs(new Point2D(10, 0), null, statmap1, null);
        var wallUp = new GameObjectAbs(new Point2D(0, 0), null, statmap1, null);
        var wallRight = new GameObjectAbs(new Point2D(0, 0), null, statmap1, null);
        var wallDown = new GameObjectAbs(new Point2D(0, 0), null, statmap1, null);
        var wallLeft = new GameObjectAbs(new Point2D(0, 0), null, statmap1, null);
        var hitbox1 = new BomberHitboxComponentImpl(obj1, true, Type.ENEMY, 2);
        var hitbox2 = new SimpleProjectileHitboxComponentImpl(obj2, true, Type.PROJECTILE, 2);
        var hitbox3 = new SimpleShipHitboxComponentImpl(obj3, true, Type.PLAYER, 2);
        var hitboxUp = new BoundaryHitboxComponentImpl(wallUp, true, Type.BOUNDARY, new Point2D(0, 0), new Point2D(1, 1));
        hitboxUp.setLocations(Locations.UP);
        var hitboxRight = new BoundaryHitboxComponentImpl(wallRight, true, Type.BOUNDARY, new Point2D(0, 0), new Point2D(1, 1));
        hitboxRight.setLocations(Locations.RIGHT);
        var hitboxDown = new BoundaryHitboxComponentImpl(wallDown, true, Type.BOUNDARY, new Point2D(0, 0), new Point2D(1, 1));
        hitboxDown.setLocations(Locations.DOWN);
        var hitboxLeft = new BoundaryHitboxComponentImpl(wallLeft, true, Type.BOUNDARY, new Point2D(0, 0), new Point2D(1, 1));
        hitboxLeft.setLocations(Locations.LEFT);
        obj1.addComponent(hitbox1);
        obj2.addComponent(hitbox2);
        obj3.addComponent(hitbox3);
        wallUp.addComponent(hitboxUp);
        wallRight.addComponent(hitboxRight);
        wallDown.addComponent(hitboxDown);
        wallLeft.addComponent(hitboxLeft);
        hitboxUp.isColliding(hitbox2);
        assertEquals(-899, obj2.gethealth());
        obj2.heal(999);
        hitboxRight.isColliding(hitbox2);
        assertEquals(-899, obj2.gethealth());
        obj2.heal(999);
        hitboxDown.isColliding(hitbox2);
        assertEquals(-899, obj2.gethealth());
        obj2.heal(999);
        hitboxLeft.isColliding(hitbox2);
        assertEquals(-899, obj2.gethealth());
    }
}
