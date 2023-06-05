package it.unibo.alienenterprises.model.api;

import java.util.*;

public interface ShopModel {
    /**
     * return the amount of money the ID corrsponding PWU cost,
     * if you have enough money to buy it, else it throws an exception
     * 
     * @param ID the ID that represent the PWU the user want to buy
     * @return the negative amount of money the ID's correspondent PWU cost
     * 
     */
    Optional<Integer> check(int ID);

    /**
     * 
     * updates various fields of the shop: the user's money, their inventory
     * and the PWUs still available. 
     * 
     * @param newMoney the amount of money to subtract or add
     * to the user's money
     * @param ID the ID that represent the PWU the user want to buy
     * @return 
     * 
     lho diviso in update moneu e update iventory
    void updateShop(int newMoney, int ID);*/

    /**
     * return the current user's money amount
     * 
     * @return the current user's money amount
     * 
    */
    int getMoney();

    /**
     * return the current user's inventory
     * 
     * @return the current user's inventory
     * 
    */
    Set<PowerUp> getInventory();


    public void updateInventory(int ID);

    public void updateMoney(int newMoney);

    /**
     * return the how many levels of the same PWU you have bought
     * 
     * @param ID the ID that represent the PWU 
     * @return the ID corresponding PWU level
     * 
    */
    int getLevel(int ID);

}
