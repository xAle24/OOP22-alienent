package it.unibo.alienenterprises.model;

import java.util.HashMap;
import java.util.Map;

import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.UserAccount;

/**
 * Implementation of UserAccount.
 * 
 * @author Ginevra Bartolini
 */
public class UserAccountImpl implements UserAccount {

    private int money;
    private String nickname;
    private int highscore;
    private final Map<String, Integer> inventory = new HashMap<>();
    private final Map<Statistic, Integer> toAddPwu = new HashMap<>();

    /**
     * Costructor. It is filled with nothing because otherwise yaml won't work.
     */
    public UserAccountImpl() {
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
        this.inventory.clear();
        this.inventory.putAll(newInventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setToAddPwu(final Map<Statistic, Integer> toAddPwu) {
        this.toAddPwu.clear();
        this.toAddPwu.putAll(toAddPwu);
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
    public Map<Statistic, Integer> getToAddPwu() {
        return this.toAddPwu;
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
        this.inventory.compute(id,
                (k, v) -> v == null ? 1 : v + 1);
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
                toAddPwu.compute(s, (k, v) -> v == 0 ? i : v + i);
            });
        }

    }

}
