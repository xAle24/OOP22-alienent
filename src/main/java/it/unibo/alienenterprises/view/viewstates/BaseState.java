package it.unibo.alienenterprises.view.viewstates;

import it.unibo.alienenterprises.controller.Controller;

/**
 * This state is active before logging in.
 * 
 * @author Giulia Bonifazi
 */
public class BaseState extends ViewStateAbs {

    public BaseState(Controller controller, String message) {
        super(controller, message);
    }

    @Override
    protected void onCloseRequest() {
        this.stage.close();
    }

}
