package it.unibo.alienenterprises.view.api;

import java.util.List;

import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import javafx.scene.layout.BorderPane;

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

    BorderPane setUpShopView();

    void loadPwuInfo(List<PowerUpRenderer> pwuInfo);

    List<PowerUpRenderer> getInfo();
}
