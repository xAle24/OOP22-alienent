package it.unibo.alienenterprises.controller;

import java.util.List;
import java.util.Map;

/**
 * ShipProp
 * This class has the only use of simplify the loading of the ships in memory
 */
public class ShipProp {
    private Map<String, Integer> stats;
    private Map<String, List<Map<String, String>>> components;

    /**
     * Void Constructor to work with yaml
     */
    public ShipProp() {

    }

    /**
     * @return
     */
    public Map<String, Integer> getStats() {
        return stats;
    }

    /**
     * @param stats
     */
    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }

    /**
     * @return
     */
    public Map<String, List<Map<String, String>>> getComponents() {
        return components;
    }

    /**
     * @param components
     */
    public void setComponents(Map<String, List<Map<String, String>>> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "ShipProp [stats=" + stats + ", components=" + components + "]";
    }

}