package it.unibo.alienenterprises.view.api;

import javafx.scene.layout.BorderPane;

/**
 * ShopView.
 * This sets up and showsthe shop GUI.
 * 
 * @author Ginevra Bartolini
 */
public interface ShopView {

    /**
     * It sets up all the elements inside the GUI and returns the GUI base, a
     * BorderPane.
     * 
     * @return the shop view GUI. It is made so that every elements is inside a
     *         BorderPane, the one that is returned.
     */
    BorderPane showShopView();
}
