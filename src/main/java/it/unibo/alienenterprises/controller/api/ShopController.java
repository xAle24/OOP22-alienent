package it.unibo.alienenterprises.controller.api;

/**
 * Controller Shop.
 */
public interface ShopController {

    /**
     * Calling ShopModel methods it checks if the user can buy the id
     * corresponding PWU and if so it procedes to update the user's money
     * and inventory.
     * 
     * @param id the id of the PWU
     * @return
     */
    void buy(String id);

}
