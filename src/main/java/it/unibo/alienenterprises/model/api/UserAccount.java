package it.unibo.alienenterprises.model.api;

import java.util.Map;

/**
 * UserAccount.
 */
public interface UserAccount {

    /**
     * 
     * Set the user money.
     * 
     * @param changeMoney the money positive or negative to be added
     * @return
     * 
     */
    void setMoney(int changeMoney);

    /**
     * 
     * Set the user nickname.
     * 
     * @param nickname
     * @return
     * 
     */
    void setNickname(String nickname);

    /**
     * 
     * Set the user highscore.
     * 
     * @param highscore
     * @return
     * 
     */
    void setHighscore(int highscore);

    /**
     * 
     * Set the user inventory.
     * 
     * @param newInventory
     * @return
     * 
     */
    void setInventory(Map<String, Integer> newInventory);

    /**
     * 
     * Get the user money.
     * 
     * @return the user money
     * 
     */
    int getMoney();

    /**
     * 
     * Get the user nickname.
     * 
     * @return the user nickname
     * 
     */
    String getNickname();

    /**
     * 
     * Get the user highscore.
     * 
     * @return the user highscore
     * 
     */
    int getHighscore();

    /**
     * 
     * Get the user inventory.
     * 
     * @return the user inventory
     * 
     */
    Map<String, Integer> getInventory();

    /**
     * 
     * Get the current level of the id corresponding PWU.
     * 
     * @param id the id, corresponding to a PWU
     * @return current level of the id corresponding PWU
     * 
     */
    int getCurrLevel(String id);

    /**
     * 
     * Add the id, corresponding to a PWU, to the user's inventory.
     * 
     * @param id the id, corresponding to a PWU
     * @return
     * 
     */
    void updateInventory(String id);

    /**
     * Return the toAddPwu map, which is a map filled with the statistic percentage
     * to add at the start of each game.
     * 
     * @return a map filled with the statistic percentage
     *         to add at the start of each game.
     */
    Map<Statistic, Integer> getToAddPwu();

    /**
     * Update the map toAddPwu with the new statistic, after buying a new pwu.
     * If the map is empty, it fills with just the statistic modifiers of the buoght
     * pwu,
     * otherwise it update each statistic, adding the new percentage to the old.
     * 
     * @param mapToAdd the map of statisics of the bought pwu
     */
    void updateToAddPwu(Map<Statistic, Integer> mapToAdd);

}
