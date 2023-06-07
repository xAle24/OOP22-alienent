package it.unibo.alienenterprises.model;

//import java.util.*;

import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.Statistic;

public class PowerUpImpl implements PowerUp {

    private int ID;
    private int cost;
    private int maxLevel;
    // private Map<Statistic,Integer> statModifiers = new HashMap<>();

    public PowerUpImpl(int ID, int cost, int maxLevel) {
        this.ID = ID;
        this.cost = cost;
        this.maxLevel = maxLevel;
    }

    public Statistic getStat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStat'");
    }

    @Override
    public int getMaxLevel() {
        return this.maxLevel;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public int getID() {
        return this.ID;
    }
}
