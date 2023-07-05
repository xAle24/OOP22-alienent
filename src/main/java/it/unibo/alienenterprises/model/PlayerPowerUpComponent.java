package it.unibo.alienenterprises.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.components.PowerUpComponent;

public class PlayerPowerUpComponent implements PowerUpComponent{

    private final Set<PowerUp> powerUps = new HashSet<>();

    @Override
    public void handle(final GameObject object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

    @Override
    public void addPowerUp(final PowerUp powerUp) {
        this.powerUps.add(powerUp);
    }

    @Override
    public void addAllPowerUp(final List<PowerUp> powerUpList) {
        this.powerUps.addAll(powerUpList);
    }


    
}
