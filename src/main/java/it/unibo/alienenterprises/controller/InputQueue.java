package it.unibo.alienenterprises.controller;

import java.util.concurrent.LinkedBlockingQueue;
import it.unibo.alienenterprises.model.api.InputSupplier.Input;

/**
 * {@link LinkedBlockingQueue} extended to accommodate {@link Inputs}.
 * 
 * @author Giulia Bonifazi
 */
public class InputQueue extends LinkedBlockingQueue<String> {

    static final long serialVersionUID = 42L;

    /**
     * Creates new instance of this class.
     * 
     * @param maxSize maximum amount of elements.
     */
    public InputQueue(final int maxSize) {
        super(maxSize);
    }

    /**
     * Polls a {@link String} from the queue and filters it into an {@link Input}.
     * 
     * @return the input
     */
    public Input pollInput() {
        Input input;
        final var str = this.poll();
        switch (str) {
            case "w":
                input = Input.ACCELERATE;
                break;
            case "s":
                input = Input.STOP_ACCELERATE;
                break;
            case "a":
                input = Input.TURN_LEFT;
                break;
            case "d":
                input = Input.TURN_RIGHT;
                break;
            case " ":
                input = Input.SHOOT;
                break;
            default:
                input = Input.NOTHING;
        }
        return input;
    }
}
