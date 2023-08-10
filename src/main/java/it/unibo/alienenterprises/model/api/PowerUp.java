package it.unibo.alienenterprises.model.api;

import java.util.Map;

/**
 * PowerUp.
 * This stores the basic information about the power up.
 * 
 * @author Ginevra Bartolini
 */
public interface PowerUp {

    /**
     * 
     * Sets the power up corresponding id.
     * 
     * @param id the id of the power up
     */
    void setId(String id);

    /**
     * 
     * Sets the power up cost.
     * 
     * @param cost the power up cost
     */
    void setCost(int cost);

    /**
     * 
     * Sets the power up maximum level.
     * 
     * @param maxLevel the power up maximum level
     */
    void setMaxLevel(int maxLevel);

    /**
     * 
     * Sets the power up map of statistics.
     * 
     * @param statModifiers the power up map of statistics
     */
    void setStatModifiers(Map<Statistic, Integer> statModifiers);

    /**
     * 
     * Gets the power up corresponding id.
     * 
     * @return the power up corresponding id
     */
    String getId();

    /**
     * 
     * Gets the power up cost.
     * 
     * @return the power up cost
     */
    int getCost();

    /**
     * 
     * Gets the power up maximum level.
     * 
     * @return the power up maximum level
     */
    int getMaxLevel();

    /**
     * 
     * Gets the power up map of statistics.
     * 
     * @return the power up map of statistics
     */
    Map<Statistic, Integer> getStatModifiers();
}
