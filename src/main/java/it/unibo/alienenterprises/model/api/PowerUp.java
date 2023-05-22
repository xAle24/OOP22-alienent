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
     */
    Statistic getBonus();

}