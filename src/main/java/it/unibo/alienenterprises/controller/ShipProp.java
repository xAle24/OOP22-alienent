package it.unibo.alienenterprises.controller;

import java.util.List;
import java.util.Map;

public class ShipProp {
    private String name;
    private Map<String, Integer> stats;
    private Map<String, List<Map<String,String>>> components;

    public ShipProp() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }

    public Map<String, List<Map<String, String>>> getComponents() {
        return components;
    }

    public void setComponents(Map<String, List<Map<String, String>>> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "ShipProp [name=" + name + ", stats=" + stats + ", components=" + components + "]";
    }
    
}