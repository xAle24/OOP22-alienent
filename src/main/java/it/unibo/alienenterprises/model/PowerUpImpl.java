package it.unibo.alienenterprises.model;

import java.util.Map;
import java.util.HashMap;

import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.Statistic;

public class PowerUpImpl implements PowerUp {

    private String ID;
    private int cost;
    private int maxLevel;
    private Map<Statistic, Integer> statModifiers = new HashMap<>();

    public PowerUpImpl() {
    }

    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    @Override
    public void setStatModifiers(Map<Statistic, Integer> statModifiers) {
        this.statModifiers.putAll(statModifiers);
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public Map<Statistic, Integer> getStatModifiers() {
        return statModifiers;
    }

}
