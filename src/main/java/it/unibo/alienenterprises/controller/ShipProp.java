package it.unibo.alienenterprises.controller;

import java.util.List;
import java.util.Map;

/**
 * ShipProp.
 * This class job is to be a support to load the ships in memory
 */
public final class ShipProp {
    private Map<String, Integer> stats;
    private Map<String, List<Map<String, String>>> components;

    /**
     * Void Constructor to work with yaml.
     */
    public ShipProp() {

    }

    /**
     * @return a map that represent the stats of the ship
     */
    public Map<String, Integer> getStats() {
        return stats;
    }

    /**
     * @param stats
     */
    public void setStats(final Map<String, Integer> stats) {
        this.stats = stats;
    }

    /**
     * @return a map containing what's needed to create a component
     */
    public Map<String, List<Map<String, String>>> getComponents() {
        return components;
    }

    /**
     * @param components
     */
    public void setComponents(final Map<String, List<Map<String, String>>> components) {
        this.components = components;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ShipProp [stats=" + stats + ", components=" + components + "]";
    }

}
