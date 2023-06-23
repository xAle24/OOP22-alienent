package it.unibo.alienenterprises.model.api;

import java.util.Map;

/**
 * PowerUp
 */
public interface PowerUp {

    /**
     * 
     * set the PWU corresponding ID.
     * 
     * @return
     */
    void setID(String ID);

    /**
     * 
     * set the PWU cost.
     * 
     * @return
     */
    void setCost(int cost);

    /**
     * 
     * set the PWU maximum level.
     * 
     * @return
     */
    void setMaxLevel(int maxLevel);

    /**
     * 
     * set the PWU map of statistics.
     * 
     * @return
     */
    void setStatModifiers(Map<Statistic, Integer> statModifiers);

    /**
     * 
     * get the PWU corresponding ID.
     * 
     * @return the PWU corresponding ID
     */
    String getID();

    /**
     * 
     * get the PWU cost.
     * 
     * @return the PWU cost
     */
    int getCost();

    /**
     * 
     * get the PWU maximum levl.
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