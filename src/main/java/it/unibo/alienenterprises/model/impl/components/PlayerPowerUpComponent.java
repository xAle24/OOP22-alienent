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
    public void setPoweUps(final Map<Statistic, Integer> powerUps) {
        double mod;
        int oldStat;
        for (final var s : powerUps.keySet()) {
            mod = (powerUps.get(s)/(double) 100) + 1.0;
            oldStat = getGameObject().getStatValue(s);
            getGameObject().setStatValue(s, (int) (oldStat * mod));
        }
        var newHp = getGameObject().getStatValue(Statistic.HP);
        getGameObject().heal(newHp-getGameObject().gethealth());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        var ret = new PlayerPowerUpComponent(obj);
        ret.setPoweUps(powerUps);
        return Optional.of(ret);

    }
}
