package it.unibo.alienenterprises.view.api;

import java.util.List;

import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import javafx.scene.layout.BorderPane;

/**
 * ShopView.
 */
public interface ShopView {

    /**
     * It returns a list of PowerUpRenderer
     * 
     * @return
     */
    List<PowerUpRenderer> getInfo();

    /**
     * It setUps all the elements inside the GUI and returns
     * 
     * @return
     */
    BorderPane showShopView();
}
