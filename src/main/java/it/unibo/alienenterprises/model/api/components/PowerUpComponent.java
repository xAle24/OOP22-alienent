package it.unibo.alienenterprises.model.api.components;

import java.util.Map;

import it.unibo.alienenterprises.model.api.Statistic;

/**
 * PowerUpComponent.
 * The component that have the job of updating the stats of a GameObject.
 */
public interface PowerUpComponent extends Component {

    /**
     * Set the modifiers that will be applied to the statistics of the GameObject.
     * 
     * @param powerUps
     */
    void setPoweUps(Map<Statistic, Integer> powerUps);
}
