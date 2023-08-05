package it.unibo.alienenterprises.model.impl.components;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.PowerUpComponent;

/**
 * PlayerPowerUpComponent.
 * A class created to apply PowerUps to Players or other GameObjects.
 */
public class PlayerPowerUpComponent extends ComponentAbs implements PowerUpComponent {

    private final Set<PowerUp> powerUps = new HashSet<>();

    /**
     * @param object
     */
    public PlayerPowerUpComponent(final GameObject object) {
        super(object, false);
    }

    @Override
    public void start() {
        if (isEnabled()) {
            powerUps.stream()
                    .map((p) -> p.getStatModifiers())
                    .forEach((m) -> applyModifiers(m));
            powerUps.clear();
        }
    }

    @Override
    public void addPowerUp(final PowerUp powerUp) {
        this.powerUps.add(powerUp);
    }

    @Override
    public void addAllPowerUp(final List<PowerUp> powerUpList) {
        this.powerUps.addAll(powerUpList);
    }

    /**
     * It apply the modifiers described in the given map to the object
     * 
     * @param map
     */
    private void applyModifiers(final Map<Statistic, Integer> map) {
        for (var s : map.keySet()) {
            getGameObject().setStatValue(s, (getGameObject().getStatValue(s) * map.get(s)) / 100);
        }
    }

    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        var ret = new PlayerPowerUpComponent(obj);
        ret.addAllPowerUp(powerUps.stream().toList());
        return Optional.of(ret);

    }
}
