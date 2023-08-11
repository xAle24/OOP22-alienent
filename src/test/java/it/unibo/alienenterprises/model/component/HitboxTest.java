package it.unibo.alienenterprises.model.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.BoundaryHitboxComponent.Locations;
import it.unibo.alienenterprises.model.api.components.HitboxComponent.Type;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.impl.GameObjectImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.BomberHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.BoundaryHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleProjectileHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleShipHitboxComponentImpl;
/**
 * Test for all hitbox component.
 */
class HitboxTest {
    private static final int LIFE85 = 85;
    private static final int LIFE999 = 999;
    private static final int LIFE999N = -999;
    private static final int LIFE899N = -899;
    private static final int NUM5 = 5;
    private static final int LIFE15 = 15;
    private static final int LIFE50 = 50;
    private static final int LIFE99 = 99;
    private static final Map<Statistic, Integer> STATMAP1 = Map.of(Statistic.HP, 100, Statistic.DAMAGE, 50);
    private static final Map<Statistic, Integer> STATMAP2 = Map.of(Statistic.HP, 1, Statistic.DAMAGE, 15);
    private static final Map<Statistic, Integer> STATMAP3 = Map.of(Statistic.HP, 100, Statistic.DAMAGE, 1);
    private static final GameObject OBJ1 = new GameObjectImpl(new Point2D(2, 0), null, STATMAP1, null);
    private static final GameObject OBJ2 = new GameObjectImpl(new Point2D(2, 0), null, STATMAP2, null);
    private static final GameObject OBJ3 = new GameObjectImpl(new Point2D(10, 0), null, STATMAP3, null);
    private static final GameObject OBJ4 = new GameObjectImpl(new Point2D(10, 10), null, STATMAP2, null);
    @Test
    void canCollides() {
        final var hitbox1 = new BomberHitboxComponentImpl(OBJ1, true, Type.ENEMY, 2);
        final var hitbox2 = new SimpleProjectileHitboxComponentImpl(OBJ2, true, Type.PROJECTILE, 2);
        hitbox2.setShooter(Type.PLAYER);
        final var hitbox3 = new BoundaryHitboxComponentImpl(OBJ3, true, Type.BOUNDARY, new Point2D(0, 1), new Point2D(1, 1));
        hitbox3.setLocations(Locations.DOWN);
        final var hitbox4 = new SimpleProjectileHitboxComponentImpl(OBJ4, true, Type.PROJECTILE, 2);
        hitbox4.setShooter(Type.ENEMY);
        OBJ1.addComponent(hitbox1);
        OBJ2.addComponent(hitbox2);
        OBJ3.addComponent(hitbox3);
        OBJ4.addComponent(hitbox4);
        hitbox1.canCollide(hitbox2);
        assertEquals(0, hitbox2.getGameObject().gethealth());
        assertEquals(LIFE85, hitbox1.getGameObject().gethealth());
        hitbox4.canCollide(hitbox3);
        assertEquals(100, hitbox3.getGameObject().gethealth());
        hitbox2.canCollide(hitbox3);
        assertEquals(LIFE999N, hitbox2.getGameObject().gethealth());
        hitbox1.canCollide(hitbox4);
        assertEquals(LIFE85, hitbox1.getGameObject().gethealth());
        assertEquals(1, hitbox4.getGameObject().gethealth());
    }

    @Test
    void types() {
        OBJ2.setPosition(new Point2D(NUM5, 0));
        final var hitbox1 = new BomberHitboxComponentImpl(OBJ1, true, Type.ENEMY, 2);
        final var hitbox2 = new SimpleProjectileHitboxComponentImpl(OBJ2, true, Type.PROJECTILE, 2);
        final var hitbox3 = new SimpleShipHitboxComponentImpl(OBJ3, true, Type.PLAYER, 2);
        OBJ1.addComponent(hitbox1);
        OBJ2.addComponent(hitbox2);
        OBJ3.addComponent(hitbox3);
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
        OBJ1.setPosition(new Point2D(0, 0));
        OBJ2.setPosition(new Point2D(0, 0));
        OBJ3.setPosition(new Point2D(0, 0));
        OBJ4.setPosition(new Point2D(0, 0));
        OBJ1.heal(LIFE15);
        OBJ2.heal(1000);
        final var hitbox1 = new BomberHitboxComponentImpl(OBJ1, true, Type.ENEMY, 2);
        final var hitbox2 = new SimpleProjectileHitboxComponentImpl(OBJ2, true, Type.PROJECTILE, 2);
        hitbox2.setShooter(Type.PLAYER);
        final var hitbox3 = new SimpleShipHitboxComponentImpl(OBJ3, true, Type.PLAYER, 2);
        final var hitbox4 = new SimpleProjectileHitboxComponentImpl(OBJ4, true, Type.PROJECTILE, 2);
        hitbox4.setShooter(Type.ENEMY);
        OBJ1.addComponent(hitbox1);
        OBJ2.addComponent(hitbox2);
        OBJ3.addComponent(hitbox3);
        OBJ4.addComponent(hitbox4);
        //collisioni tra bomber e gli altri oggetti
        hitbox1.isColliding(hitbox2);
        assertEquals(0, OBJ2.gethealth());
        hitbox1.isColliding(hitbox3);
        OBJ2.heal(1);
        assertEquals(LIFE50, OBJ3.gethealth());
        OBJ1.heal(LIFE99);
        OBJ3.heal(LIFE50);
        hitbox1.isColliding(hitbox4);
        assertEquals(1, OBJ4.gethealth());
        //collisioni tra il proiettile sparato dal player e gli altri oggetti
        hitbox2.isColliding(hitbox1);
        assertEquals(LIFE85, OBJ1.gethealth());
        OBJ1.heal(LIFE15);
        hitbox2.isColliding(hitbox3);
        assertEquals(100, OBJ3.gethealth());
        hitbox2.isColliding(hitbox4);
        assertEquals(1, OBJ4.gethealth());
        //collisioni tra il player e gli altri oggetti
        hitbox3.isColliding(hitbox1);
        assertEquals(100, OBJ1.gethealth());
        hitbox3.isColliding(hitbox2);
        assertEquals(1, OBJ2.gethealth());
        hitbox3.isColliding(hitbox4);
        assertEquals(0, OBJ4.gethealth());
        OBJ4.heal(1);
        //collisioni tra il proiettile sparato dal nemico e gli altri oggetti
        hitbox4.isColliding(hitbox4);
        assertEquals(100, OBJ1.gethealth());
        hitbox4.isColliding(hitbox2);
        assertEquals(1, OBJ2.gethealth());
        hitbox4.isColliding(hitbox3);
        assertEquals(LIFE85, OBJ3.gethealth());
    }

    @Test
    void collideBoundary() {
        final Map<Statistic, Integer> statmap1 = new HashMap<>();
        statmap1.put(Statistic.HP, 100);
        statmap1.put(Statistic.DAMAGE, 1);
        final var obj1 = new GameObjectImpl(new Point2D(2, 0), null, statmap1, null);
        final var obj2 = new GameObjectImpl(new Point2D(NUM5, 0), null, statmap1, null);
        final var obj3 = new GameObjectImpl(new Point2D(10, 0), null, statmap1, null);
        final var wallUp = new GameObjectImpl(new Point2D(0, 0), null, statmap1, null);
        final var wallRight = new GameObjectImpl(new Point2D(0, 0), null, statmap1, null);
        final var wallDown = new GameObjectImpl(new Point2D(0, 0), null, statmap1, null);
        final var wallLeft = new GameObjectImpl(new Point2D(0, 0), null, statmap1, null);
        final var hitbox1 = new BomberHitboxComponentImpl(obj1, true, Type.ENEMY, 2);
        final var hitbox2 = new SimpleProjectileHitboxComponentImpl(obj2, true, Type.PROJECTILE, 2);
        final var hitbox3 = new SimpleShipHitboxComponentImpl(obj3, true, Type.PLAYER, 2);
        final var hitboxUp = new BoundaryHitboxComponentImpl(wallUp, true, Type.BOUNDARY, new Point2D(0, 0), new Point2D(1, 1));
        hitboxUp.setLocations(Locations.UP);
        final var hitboxRight = new BoundaryHitboxComponentImpl(wallRight, true, Type.BOUNDARY, new Point2D(0, 0), 
            new Point2D(1, 1));
        hitboxRight.setLocations(Locations.RIGHT);
        final var hitboxDown = new BoundaryHitboxComponentImpl(wallDown, true, Type.BOUNDARY, new Point2D(0, 0), 
            new Point2D(1, 1));
        hitboxDown.setLocations(Locations.DOWN);
        final var hitboxLeft = new BoundaryHitboxComponentImpl(wallLeft, true, Type.BOUNDARY, new Point2D(0, 0), 
            new Point2D(1, 1));
        hitboxLeft.setLocations(Locations.LEFT);
        obj1.addComponent(hitbox1);
        obj2.addComponent(hitbox2);
        obj3.addComponent(hitbox3);
        wallUp.addComponent(hitboxUp);
        wallRight.addComponent(hitboxRight);
        wallDown.addComponent(hitboxDown);
        wallLeft.addComponent(hitboxLeft);
        hitboxUp.isColliding(hitbox2);
        assertEquals(LIFE899N, obj2.gethealth());
        obj2.heal(LIFE999);
        hitboxRight.isColliding(hitbox2);
        assertEquals(LIFE899N, obj2.gethealth());
        obj2.heal(LIFE999);
        hitboxDown.isColliding(hitbox2);
        assertEquals(LIFE899N, obj2.gethealth());
        obj2.heal(LIFE999);
        hitboxLeft.isColliding(hitbox2);
        assertEquals(LIFE899N, obj2.gethealth());
    }
}
