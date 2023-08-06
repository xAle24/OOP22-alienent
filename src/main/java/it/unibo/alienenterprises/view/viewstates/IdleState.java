package it.unibo.alienenterprises.view.viewstates;

import it.unibo.alienenterprises.controller.Controller;

/**
 * This State will be active when the player is sitting in the menu and nothing
 * is interrupted by quitting.
 */
public class IdleState extends ViewStateAbs {

    /**
     * Creates an instance of the {@link IdleState} class.
     * 
     * @param controller the main {@link Controller} of the game.
     * @param message    the message that will appear in the alert.
     */
    public IdleState(Controller controller, final String message) {
        super(controller, message);
    }

    @Override
    protected void onCloseRequest() {
        this.controller.save();
        this.stage.close();
    }

}
