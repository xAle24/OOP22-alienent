package it.unibo.alienenterprises.model.api;

import java.util.List;
import java.util.Optional;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

/**
 * Models every object in the game map.
 */
public interface GameObject {
    /**
     * Different game object avaible
     */
    enum Type {
        ENEMY, PLAYER, PROJECTILE;
    }

    /**
     * get the type of the game object
     * @return
     */
    Type getType();

    /**
     * return if the object is alive
     * @return 
     */
    boolean isAlive();

    /**
     * return the position of the object
     * @return
     */
    Point2D getPosition();

    /**
     * return the velocity vector 
     * @return
     */
    Vector2D getVelocity();

    /**
     * return the component of the given type or an empty optional if not present
     * @param <T>
     * @param type
     * @return
     */
    default <T extends Component> Optional<T> getComponent(Class<T> type){
        return this.getAllComponent().stream().filter(c-> type.isInstance(c)).map(c->type.cast(c)).findFirst();
    }

    /**
     * return a list of all components
     * @return
     */
   List<Component> getAllComponent();
}
