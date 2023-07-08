package it.unibo.alienenterprises.model.api;

import java.util.Optional;
import java.util.Set;

import it.unibo.alienenterprises.model.UserAccountImpl;

/**
 * Model of the shop.
 */
public interface ShopModel {
    /**
     * return the amount of money the id corrsponding PWU cost,
     * if you have enough money to buy it, else it throws an exception.
     * 
     * @param id   the id that represent the PWU the user want to buy
     * @param user the user to be checked
     * @return the negative amount of money the id's correspondent PWU cost
     * 
     */
    Optional<Integer> check(String id, UserAccountImpl user);

    /**
     * The method updates the user money and their inventory.
     * 
     * @param id          the id of the powerup to be added to the inventory
     * @param user        the user which inventory and money are to be changed
     * @param changeMoney the money to add or subtract to the current user money
     * @return
     */
    void updateShop(String id, UserAccountImpl user, int changeMoney);

    /**
     * Given a Set of Pwu, it add all of them to a collection.
     * 
     * @return
     * 
     */
    void loadPwu(Set<PowerUp> pwu);

    Set<PowerUp> getPwu();

}
