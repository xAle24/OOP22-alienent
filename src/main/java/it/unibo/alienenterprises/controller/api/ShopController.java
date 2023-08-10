package it.unibo.alienenterprises.controller.api;

import java.util.List;

import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import it.unibo.alienenterprises.model.api.UserAccount;

/**
 * This is the Shop's Controller. It is the way the model and the view will
 * communicate.
 * Its methods also ensure that every information the model and the view will
 * need are loaded.
 * 
 * @author Ginevra Bartolini
 */
public interface ShopController {

    /**
     * It returns the UserAccountImpl that the ShopController is referencing.
     * 
     * @return the UserAccountImpl that the ShopController is referencing
     */
    UserAccount getUserAccount();

    /**
     * It returns the List of PowerUp, that is created by loading the YAML file with
     * the loadPwuYaml method.
     * 
     * @return a List of PowerUp
     */
    List<PowerUp> getPwu();

    /**
     * It returns the List of PowerUpInfo, that is created by loading the YAML file
     * with the loadPwuInfoYaml method.
     * 
     * @return a List of PowerUpInfo
     */
    List<PowerUpRenderer> getPwuInfo();

    /**
     * It loads the YAML file that contains all the power ups that are used in the
     * game.
     */
    void loadPwuYaml();

    /**
     * It loads the YAML file that contains all the power ups information that are
     * used to
     * set the Shop view.
     */
    void loadPwuInfoYaml();

    /**
     * Calling ShopModel's methods it checks if the user can buy the id
     * corresponding power up and, if so, it procedes to update the user's money
     * and inventory.
     * 
     * @param id the id of the power up
     * @return true if the buying action was successful, false otherwise
     */
    boolean buy(String id);

    /**
     * Closes the shop.
     */
    void closeShop();

}
