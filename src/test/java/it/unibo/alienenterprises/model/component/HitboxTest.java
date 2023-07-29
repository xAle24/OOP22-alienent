package it.unibo.alienenterprises.model.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.model.api.components.HitboxComponent.Type;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleShipHitboxComponentImpl;

public class HitboxTest {

    @Test
    void collides() {
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
     
}
