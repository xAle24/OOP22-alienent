package it.unibo.alienenterprises.controller;

import java.util.Map;


public class ShipProp {
    private String name;
    private Map<String, Integer> stats;
    private String input;
    private String hitboxID;
    private int hitboxr;
    private String shooter;

    public ShipProp(){

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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getHitboxID() {
        return hitboxID;
    }

    public void setHitboxID(String hitboxID) {
        this.hitboxID = hitboxID;
    }

    public int getHitboxr() {
        return hitboxr;
    }

    public void setHitboxr(int hitboxr) {
        this.hitboxr = hitboxr;
    }

    public String getShooter() {
        return shooter;
    }

    public void setShooter(String shooter) {
        this.shooter = shooter;
    }

    @Override
    public String toString() {
        return "ShipProp [name=" + name + ", stats=" + stats + ", input=" + input + ", hitboxID=" + hitboxID
                + ", hitboxr=" + hitboxr + ", shooter=" + shooter + "]";
    }

    

}