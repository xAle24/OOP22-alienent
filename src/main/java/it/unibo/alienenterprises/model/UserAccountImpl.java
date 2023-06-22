package it.unibo.alienenterprises.model;

import java.util.*;

import it.unibo.alienenterprises.model.api.UserAccount;

public class UserAccountImpl implements UserAccount {

    private int money;
    private String nickname;
    private int highscore;
    private Map<String, Integer> inventory = new HashMap<>(); // contiene l'ID e il corrispondente livello di acquisto

    public UserAccountImpl() {
    }

    @Override
    public void setMoney(int changeMoney) {
        this.money += changeMoney;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    @Override
    public void setInventory(Map<Integer, Integer> newInventory) {
        this.inventory.putAll(inventory);
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public String getNickname() {
        return this.nickname;
    }

    @Override
    public int getHighscore() {
        return this.highscore;
    }

    @Override
    public Map<String, Integer> getInventory() {
        return this.inventory;
    }

    @Override
    public int getCurrLevel(String ID) {
        return (inventory.containsKey(ID)) ? this.inventory.get(ID) : 0;
    }

    @Override
    public Set<String> getInventoryID() {
        return this.inventory.keySet();
    }

    @Override
    public void updateInventory(String ID) {
        if (!inventory.containsKey(ID)) {
            inventory.put(ID, 1);
        } else {
            this.inventory.computeIfPresent(ID, (k, v) -> v + 1);
        }
        System.out.print(this.inventory.size());
    }

}
