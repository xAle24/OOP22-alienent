package it.unibo.alienenterprises.view.api;

import java.util.Set;

import it.unibo.alienenterprises.model.api.PowerUpRenderer;

/**
 * ShopView.
 */
public interface ShopView {

    /**
     * it shows the shop GUI.
     * 
     * @return
     * 
     */
    void show();

    /**
     * it start the shop GUI.
     * 
     * @return
     * 
     */
    void start();

    void loadPwuInfo(Set<PowerUpRenderer> pwuInfo);

    Set<PowerUpRenderer> getInfo();
}
