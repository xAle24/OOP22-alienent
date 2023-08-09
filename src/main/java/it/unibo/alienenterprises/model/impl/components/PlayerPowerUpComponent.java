package it.unibo.alienenterprises.model.impl.components;

import java.util.Map;
import java.util.Optional;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.PowerUpComponent;

/**
 * PlayerPowerUpComponent.
 * A class created to apply PowerUps to Players or other GameObjects.
 */
public class PlayerPowerUpComponent extends ComponentAbs implements PowerUpComponent {

    private Map<Statistic, Integer> powerUps;

    /**
     * @param object
     */
    public PlayerPowerUpComponent(final GameObject object) {
        super(object, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPoweUps(Map<Statistic, Integer> powerUps) {
        for (var s : powerUps.keySet()) {
            getGameObject().setStatValue(s, (getGameObject().getStatValue(s) * powerUps.get(s)) / 100);
        }
    }

    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        var ret = new PlayerPowerUpComponent(obj);
        ret.setPoweUps(powerUps);
        return Optional.of(ret);

    }
}
