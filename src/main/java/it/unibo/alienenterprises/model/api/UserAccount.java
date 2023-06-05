package it.unibo.alienenterprises.model.api;

import java.util.*;

public interface UserAccount {
    
    /**
     * 
     * Add the ID, corresponding to a PWU, to the user's inventory
     * 
     * @param ID the ID, corresponding to a PWU
     * @return
     * 
     */
    void updateInventory(int ID);

    /**
     * 
     * Saves all the user information in the saving file
     * 
     * @return
     * 
     */
    void save();

    /**
     * 
     * Get the user money
     * 
     * @return the user money
     * 
     */
    int getMoney();

    void setMoney(int changeMoney);

    /**
     * 
     * Get the current level of the ID corresponding PWU
     * 
     * @param ID the ID, corresponding to a PWU
     * @return current level of the ID corresponding PWU
     * 
     */
    int getCurrLevel(int ID);

    Set<Integer> getInventoryID();

}
