package it.unibo.alienenterprises.model.api;

import java.util.*;

public interface UserAccount {

    /**
     * 
     * Set the user money
     * 
     * @return
     * 
     */
    void setMoney(int changeMoney);

    /**
     * 
     * Set the user nickname
     * 
     * @return
     * 
     */
    void setNickname(String nickname);

    /**
     * 
     * Set the user highscore
     * 
     * @return
     * 
     */
    void setHighscore(int highscore);

    /**
     * 
     * Set the user inventory
     * 
     * @return
     * 
     */
    void setInventory(Map<Integer, Integer> newInventory);

    /**
     * 
     * Get the user money
     * 
     * @return the user money
     * 
     */
    int getMoney();

    /**
     * 
     * Get the user nickname
     * 
     * @return the user nickname
     * 
     */
    String getNickname();

    /**
     * 
     * Get the user highscore
     * 
     * @return the user highscore
     * 
     */
    int getHighscore();

    /**
     * 
     * Get the user inventory
     * 
     * @return the user inventory
     * 
     */
    Map<String, Integer> getInventory();

    /**
     * 
     * Get the current level of the ID corresponding PWU
     * 
     * @param ID the ID, corresponding to a PWU
     * @return current level of the ID corresponding PWU
     * 
     */
    int getCurrLevel(String ID);

    /**
     * 
     * Get the????
     * 
     * @return current level of the ID corresponding PWU
     * 
     */
    Set<String> getInventoryID();

    /**
     * 
     * Add the ID, corresponding to a PWU, to the user's inventory
     * 
     * @param ID the ID, corresponding to a PWU
     * @return
     * 
     */
    void updateInventory(String ID);

}
