package it.unibo.alienenterprises.model.api;

/**
 * PowerUp 
 */
public interface PowerUp {
    
    
    /**
     * 
     * get the PWU corresponding Statistic
     * 
     * @return the PWU corresponding Statistic 
     */
    Statistic getStat();

    /**
     * 
     * get the PWU corresponding bonus modifier
     * 
     * @return the PWU corresponding bonus modifier 
     mi sa che non serve
    Statistic getBonus();*/

    int getMaxLevel();

    int getCost();

    int getID();
}