package it.unibo.alienenterprises.controller.api;

import java.util.Set;

import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import it.unibo.alienenterprises.model.api.UserAccount;

/**
 * Controller Shop.
 */
public interface ShopController {

    void loadShopModel();

    void loadShopView();

    void showShop(UserAccount user);

    /**
     * Calling ShopModel methods it checks if the user can buy the id
     * corresponding PWU and if so it procedes to update the user's money
     * and inventory.
     * 
     * @param id the id of the PWU
     * @return
     */
    void buy(String id);

    Set<PowerUp> getModelPwu();

    Set<PowerUpRenderer> getViewInfo();

}
