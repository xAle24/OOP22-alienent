package it.unibo.alienenterprises.model;

import java.util.*;

import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.UserAccount;

public class UserAccountImpl implements UserAccount {

    private int money;
    private String nickname;
    private int highscore;
    private Map<String, Integer> inventory = new HashMap<>(); // contiene l'id e il corrispondente livello di acquisto
    private Map<Statistic, Integer> toAddPwu = new HashMap<>();

    public UserAccountImpl() {
    }

    public UserAccountImpl(String nickname) {
        this.nickname = nickname;
        this.money = 0;
        this.highscore = 0;
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
    public int getCurrLevel(String id) {
        return (inventory.containsKey(id)) ? this.inventory.get(id) : 0;
    }

    @Override
    public void updateInventory(String id) {
        if (!inventory.containsKey(id)) {
            inventory.put(id, 1);
        } else {
            this.inventory.computeIfPresent(id, (k, v) -> v + 1);
        }
        System.out.print(this.inventory.size());
    }

    @Override
    public Map<Statistic, Integer> getToAddPwu() {
        return this.toAddPwu;
    }

    @Override
    public void updateToAddPwu(Map<Statistic, Integer> mapToAdd) {
        if (toAddPwu.isEmpty()) {
            toAddPwu.putAll(mapToAdd);
        } else {
            mapToAdd.forEach((s, i) -> {
                if (toAddPwu.get(s) == 0) {
                    toAddPwu.replace(s, i);
                } else {
                    toAddPwu.replace(s, toAddPwu.get(s) + i);
                }
            });
        }
    }

}
