package it.unibo.alienenterprises.model.api;

import java.util.List;
import java.util.Optional;

/**
 * Model of the shop.
 */
public interface ShopModel {

    /**
     * Given a Set of Pwu, it add all of them to a collection.
     * 
     * @param pwu the list to be added
     */
    void loadPwu(List<PowerUp> pwu);

    /**
     * return the amount of money the id corrsponding PWU cost,
     * if you have enough money to buy it, else it throws an exception.
     * 
     * @param id the id that represent the PWU the user want to buy
     * @return the negative amount of money the id's correspondent PWU cost
     * 
     */
    Optional<Integer> check(String id);

    /**
     * The method updates the user money and their inventory.
     * 
     * @param id          the id of the powerup to be added to the inventory
     * @param changeMoney the money to add or subtract to the current user money
     */
    void updateShop(String id, int changeMoney);

}
