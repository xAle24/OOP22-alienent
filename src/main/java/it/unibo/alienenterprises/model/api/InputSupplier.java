package it.unibo.alienenterprises.model.api;

import java.util.List;

public interface InputSupplier {

    enum Input{
        /**
         * Called when the player must accelerate.
         */
        ACCELERATE,
        /**
         * Called when the player stops accelerating.
         */
        STOP_ACCELERATE,
        /**
         * Called when player turns left.
         */
        TURN_LEFT,
        /**
         * Called when player turns right.
         */
        TURN_RIGHT,
        /**
         * Called when player stops turning.
         */
        STOP_TURN;
    }

    List<Input> getInputList();

    void addInput();

    void clearInputList();

}
