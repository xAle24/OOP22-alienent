package it.unibo.alienenterprises.view.api;

import java.util.List;

import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import javafx.scene.layout.BorderPane;

/**
 * ShopView.
 */
public interface ShopView {

    /**
     * It saves the list containing the pwu info inside a collection.
     * 
     * @param pwuInfo
     */
    void loadPwuInfo(List<PowerUpRenderer> pwuInfo);

    /**
     * It returns a list of PowerUpEenderer
     * 
     * @return
     */
    List<PowerUpRenderer> getInfo();

    /**
     * It setUps all the elements inside the GUI and returns
     * 
     * @return
     */
    BorderPane setUpShopView();

    /**
     * it shows the shop GUI.
     * 
     * @return
     * 
     */
    void show();

}
