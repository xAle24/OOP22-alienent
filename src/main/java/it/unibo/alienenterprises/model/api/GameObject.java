package it.unibo.alienenterprises.model.api;

import java.util.List;
import java.util.Optional;

import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * Models every object in the game map.
 */
public interface GameObject {
    /**
     * Return the input stat value.
     * @param stat
     * @return int of the input stat value
     */
    int getStatValue(Statistic stat);

    /**
     * return if the object is alive.
     * @return true if the object is alive, false otherwise
     */
    boolean isAlive();

    /**
     * return the position of the object.
     * @return the object position 
     */
    Point2D getPosition();

    /**
     * return the velocity vector.
     * @return the object velocity vector 
     */
    Vector2D getVelocity();

    /**
     * set the position of the object.
     * @param point
     */
    void setPosition(Point2D point);

    /**
     * set the velocity of the object.
     * @param vector
     */
    void setVelocity(Vector2D vector);

    /**
     * return the component of the given type or an empty optional if not present.
     * 
     * @param <T>
     * @param type
     * @return The component of the given type
     */
    default <T extends Component> Optional<T> getComponent(Class<T> type) {
        return this.getAllComponent().stream().filter(c -> type.isInstance(c)).map(c -> type.cast(c)).findFirst();
    }

    /**
     * return a list of all components.
     * @return a list with all the objects component
     */
    List<Component> getAllComponent();
}
