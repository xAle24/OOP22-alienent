package it.unibo.alienenterprises.model.api.components;

import java.util.List;

import it.unibo.alienenterprises.model.api.PowerUp;

/**
 * PowerUpComponent
 */
public interface PowerUpComponent extends Component {

    /**
     * Add the given powerUp to the component
     * 
     * @param powerUp
     */
    void addPowerUp(PowerUp powerUp);

    /**
     * Add the powerUps to the component
     * 
     * @param powerUpList
     */
    void addAllPowerUp(List<PowerUp> powerUpList);
}
