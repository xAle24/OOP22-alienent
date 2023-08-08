package it.unibo.alienenterprises.controller.api;

import java.util.List;

import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import it.unibo.alienenterprises.model.api.ShopModel;
import it.unibo.alienenterprises.model.api.UserAccount;

/**
 * This is the Shop's Controller. It is the way the model and the view will
 * communicate.
 * Its methods also ensure that every information the model and the view will
 * need are loaded.
 */
public interface ShopController {

    /**
     * It sets the implementation of shopModel the ShopController will be
     * referencing.
     * 
     * @param shopModel
     */
    void setModel(ShopModel shopModel);

    /**
     * It returns the UserAccountImpl that the ShopController is referencing.
     * 
     * @return the UserAccountImpl that the ShopController is referencing
     */
    UserAccount getUserAccount();

    /**
     * It returns the List of PowerUp, that is created by loading the yaml file with
     * the loadPwuYaml method.
     * 
     * @return a List of PowerUp
     */
    List<PowerUp> getPwu();

    /**
     * It returns the List of PowerUpInfo, that is created by loading the yaml file
     * with the loadPwuInfoYaml method.
     * 
     * @return a List of PowerUpInfo
     */
    List<PowerUpRenderer> getPwuInfo();

    /**
     * It loads the yaml file that contains all the pwu that are used in the game.
     */
    void loadPwuYaml();

    /**
     * It loads the yaml file that contains all the pwu information that are used to
     * set the Shop view.
     */
    void loadPwuInfoYaml();

    /**
     * Calling ShopModel's methods it checks if the user can buy the id
     * corresponding PWU and, if so, it procedes to update the user's money
     * and inventory.
     * 
     * @param id the id of the PWU
     * @return true if the buying action was successful, false otherwise
     */
    boolean buy(String id);

    /**
     * Closes the shop.
     */
    void closeShop();

}
