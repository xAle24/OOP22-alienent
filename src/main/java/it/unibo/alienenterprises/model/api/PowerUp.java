package it.unibo.alienenterprises.model.api;

import java.util.Map;

/**
 * PowerUp.
 */
public interface PowerUp {

    /**
     * 
     * set the PWU corresponding id.
     * 
     * @param id the id of the PWU
     * @return
     */
    void setId(String id);

    /**
     * 
     * set the PWU cost.
     * 
     * @param cost the PWU cost
     * @return
     */
    void setCost(int cost);

    /**
     * 
     * set the PWU maximum level.
     * 
     * @param maxLevel the PWU maximum level
     * @return
     */
    void setMaxLevel(int maxLevel);

    /**
     * 
     * set the PWU map of statistics.
     * 
     * @param statModifiers the PWU map of statistics
     * @return
     */
    void setStatModifiers(Map<Statistic, Integer> statModifiers);

    /**
     * 
     * get the PWU corresponding id.
     * 
     * @return the PWU corresponding id
     */
    String getId();

    /**
     * 
     * get the PWU cost.
     * 
     * @return the PWU cost
     */
    int getCost();

    /**
     * 
     * get the PWU maximum level.
     * 
     * @return the PWU maximum level
     */
    int getMaxLevel();

    /**
     * 
     * get the PWU map of statistics.
     * 
     * @return the PWU map of statistics
     */
    Map<Statistic, Integer> getStatModifiers();
}
