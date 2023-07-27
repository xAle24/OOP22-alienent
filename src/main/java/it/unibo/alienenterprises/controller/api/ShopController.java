package it.unibo.alienenterprises.controller.api;

import java.util.List;

import it.unibo.alienenterprises.model.UserAccountImpl;
import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import it.unibo.alienenterprises.model.api.ShopModel;

/**
 * Controller Shop.
 */
public interface ShopController {

    void loadShopModel();

    void loadShopView();

    List<PowerUpRenderer> getPwuInfo();

    List<PowerUp> getPwu();

    /**
     * Calling ShopModel methods it checks if the user can buy the id
     * corresponding PWU and if so it procedes to update the user's money
     * and inventory.
     * 
     * @param id the id of the PWU
     * @return
     */
    boolean buy(String id);

    UserAccountImpl getUserAccount();

    void setModel(ShopModel shopModel);

}
