package it.unibo.alienenterprises.model.api;

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
}
