package it.unibo.alienenterprises.model.api;

import java.util.Collection;
import java.util.List;
import java.util.Map;
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
     * @param stat the statistic whose value you want.
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
     * @return the vector2D of the velocity
     */
    Vector2D getVelocity();

    /**
     * set the position of the object.
     * @param point the point you want to assign as the location of the object.
     */
    void setPosition(Point2D point);

    /**
     * set the velocity of the object.
     * @param vector the velocity vector you want to assign.
     */
    void setVelocity(Vector2D vector);

    /**
     * return the component of the given type or an empty optional if not present.
     * @param <T> 
     * @param type type of component you want
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

    /**
     * return the stats map.
     * @return a map with value of all stats
     */
    Map<Statistic, Integer> getAllStats();

    /**
     * Set the value of the given stat.
     * @param stat The statistic you want to value.
     * @param value the value you want to assign.
     */
    void setStatValue(Statistic stat, int value);

    /**
     * Insert a new component.
     * @param component the new component you want to add.
     */
    void addComponent(Component component);
    /**
     * subtract the life from the object.
     * @param dmg amount of life to be subtracted.
     */
    void hit(int dmg);
    /**
     * adds life to the object.
     * @param heal Amount of life to add.
     */
    void heal(int heal);
    /**
     * Insert multiple components at the game object.
     * @param components components to add to the object.
     */
    void addAllComponent(Collection<Component> components);
    /**
     * Return the health of the object.
     * @return int value of health.
     */
    int gethealth();
    /**
     * heal the game object every second of the recovery statistic.
     * @param deltatime Time passed since the last cycle.
     */
    void recovery(double deltatime);
    /**
     * Return the Id of the object.
     * @return string of the Id.
     */
    String getId();
    /**
     * Update the game object.
     * @param deltatime Time passed since the last cycle.
     */
    void update(double deltatime);
}
