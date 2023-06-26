package it.unibo.alienenterprises.model;

import java.util.Map;
import java.util.HashMap;

import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.Statistic;

/**
 * PowerUpImpl.
 */
public class PowerUpImpl implements PowerUp {

    private String id;
    private int cost;
    private int maxLevel;
    private final Map<Statistic, Integer> statModifiers = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCost(final int cost) {
        this.cost = cost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxLevel(final int maxLevel) {
        this.maxLevel = maxLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatModifiers(final Map<Statistic, Integer> statModifiers) {
        this.statModifiers.putAll(statModifiers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCost() {
        return cost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Statistic, Integer> getStatModifiers() {
        return statModifiers;
    }

}
