package it.unibo.alienenterprises.model.api;

import java.util.Map;

/**
 * UserAccount.
 * 
 * @author Ginevra Bartolini
 */
public interface UserAccount {

    /**
     * 
     * Sets the user money.
     * 
     * @param changeMoney the money, positive or negative, to be added
     * 
     */
    void setMoney(int changeMoney);

    /**
     * 
     * Sets the user nickname.
     * 
     * @param nickname
     * 
     */
    void setNickname(String nickname);

    /**
     * 
     * Sets the user highscore.
     * 
     * @param highscore
     * 
     */
    void setHighscore(int highscore);

    /**
     * 
     * Sets the user inventory, which is a map filled with the ids of the power ups
     * already bought.
     * 
     * @param newInventory
     * 
     */
    void setInventory(Map<String, Integer> newInventory);

    /**
     * Sets the toAddPwu map, which is a map filled with the statistic percentages
     * to add at the start of each game.
     * 
     * @param toAddPwu
     */
    void setToAddPwu(Map<Statistic, Integer> toAddPwu);

    /**
     * 
     * Gets the user money.
     * 
     * @return the user money
     * 
     */
    int getMoney();

    /**
     * 
     * Gets the user nickname.
     * 
     * @return the user nickname
     * 
     */
    String getNickname();

    /**
     * 
     * Gets the user highscore.
     * 
     * @return the user highscore
     * 
     */
    int getHighscore();

    /**
     * 
     * Gets the user inventory, which is a map filled with the ids of the power ups
     * already bought.
     * 
     * @return the user inventory
     * 
     */
    Map<String, Integer> getInventory();

    /**
     * 
     * Gets the current level of the id corresponding power up.
     * 
     * @param id the id, corresponding to a power up
     * @return current level of the id corresponding power up
     * 
     */
    int getCurrLevel(String id);

    /**
     * Returns the toAddPwu map, which is a map filled with the statistic percentage
     * to add at the start of each game.
     * 
     * @return a map filled with the statistic percentage
     *         to add at the start of each game.
     */
    Map<Statistic, Integer> getToAddPwu();

    /**
     * 
     * Adds the id, corresponding to a power up, to the user's inventory.
     * 
     * @param id the id, corresponding to a power up
     * @return
     * 
     */
    void updateInventory(String id);

    /**
     * Updates the map toAddPwu with the new statistics, after buying a new power
     * up.
     * If the map is empty, it fills with just the statistic modifiers of the bought
     * power up,
     * otherwise it updates each statistic, adding the new percentage to the old.
     * 
     * @param mapToAdd the map of statistics of the bought power up
     */
    void updateToAddPwu(Map<Statistic, Integer> mapToAdd);

}
