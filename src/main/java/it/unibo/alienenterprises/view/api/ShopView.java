package it.unibo.alienenterprises.view.api;

import javafx.scene.layout.BorderPane;

/**
 * ShopView.
 */
public interface ShopView {

    /**
     * It setUps all the elements inside the GUI and returns.
     * 
     * @return the shop view GUI. It is made so that every elements is inside a
     *         BorderPane, the one that is returned.
     */
    BorderPane showShopView();
}
