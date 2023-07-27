package it.unibo.alienenterprises.model;

import java.util.HashMap;
import java.util.Map;

import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.UserAccount;

/**
 * Implementation of UserAccount.
 */
public class UserAccountImpl implements UserAccount {

    private int money;
    private String nickname;
    private int highscore;
    private final Map<String, Integer> inventory = new HashMap<>(); // contiene l'id e il corrispondente livello di
                                                                    // acquisto
    private final Map<Statistic, Integer> toAddPwu = new HashMap<>();

    /**
     * Costructor. It is filled with nothing because otherwise yaml won't work.
     */
    public UserAccountImpl() {
        // This constructor is intentionally empty.
    }

    /**
     * Costructor. It is used in the case of registration of a new account.
     * 
     * @param nickname the nickname of the account
     */
    public UserAccountImpl(final String nickname) {
        this.nickname = nickname;
        this.money = 0;
        this.highscore = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMoney(final int changeMoney) {
        this.money += changeMoney;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHighscore(final int highscore) {
        this.highscore = highscore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventory(final Map<String, Integer> newInventory) {
        this.inventory.putAll(newInventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMoney() {
        return this.money;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNickname() {
        return this.nickname;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHighscore() {
        return this.highscore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Integer> getInventory() {
        return this.inventory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrLevel(final String id) {
        return inventory.containsKey(id) ? this.inventory.get(id) : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInventory(final String id) {
        if (!inventory.containsKey(id)) {
            inventory.put(id, 1);
        } else {
            this.inventory.computeIfPresent(id, (k, v) -> v + 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Statistic, Integer> getToAddPwu() {
        return this.toAddPwu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateToAddPwu(final Map<Statistic, Integer> mapToAdd) {
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
