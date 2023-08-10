package it.unibo.alienenterprises.view.viewstates;

import it.unibo.alienenterprises.controller.Controller;

/**
 * This state is active before logging in.
 * 
 * @author Giulia Bonifazi
 */
public class BaseState extends ViewStateAbs {

    /**
     * Creates a new instance of this class.
     * 
     * @param controller the main controller
     * @param message    the message for the alert
     */
    public BaseState(final Controller controller, final String message) {
        super(controller, message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCloseRequest() {
        super.getStage().close();
    }

}
