package it.unibo.alienenterprises.model.api;

import java.util.List;
import java.util.Optional;

/**
 * This is the model of the shop.
 * This implements all the operations related to power up buying and to account
 * updating.
 * 
 * @author Ginevra Bartolini
 */
public interface ShopModel {

    /**
     * Given a Set of power up, it adds all of them to a collection.
     * 
     * @param pwu the list to be added
     */
    void loadPwu(List<PowerUp> pwu);

    /**
     * Returns the negative amount of money the id's corresponding power up cost,
     * if you have enough money to buy it, else it returns an Optional.empty().
     * 
     * @param id the id that represent the power up the user want to buy
     * @return the negative amount of money the id's correspondent power up cost
     * 
     */
    Optional<Integer> check(String id);

    /**
     * Updates the user money and their inventory.
     * 
     * @param id          the id of the power up to be added to the inventory
     * @param changeMoney the money to add or subtract to the current user money
     */
    void updateShop(String id, int changeMoney);

}
