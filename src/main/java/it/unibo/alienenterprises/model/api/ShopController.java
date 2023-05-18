package it.unibo.alienenterprises.model.api;

public interface ShopController {
    
    /**
     * Calling ShopModel methods it checks if the user can buy the ID
     * corresponding PWU and if so it procedes to update the user's money
     * and inventory
     * 
     * @return
     */
    void buy (int ID);

    /**
     * Calling ShopModel methods it gets the ID corresponding PWU level
     * 
     * @param  ID the ID that represent the PWU 
     * @return the ID corresponding PWU level
     */
    int setLevel (int ID);

    /**
     * return the current user's money amount
     * 
     * @return the current user's money amount
     * 
    */
    int setMoney ();
}
