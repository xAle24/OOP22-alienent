package it.unibo.alienenterprises.view.viewstates;

import it.unibo.alienenterprises.controller.Controller;

public class BaseState extends ViewStateAbs {

    public BaseState(Controller controller, String message) {
        super(controller, message);
    }

    @Override
    protected void onCloseRequest() {
        this.stage.close();
    }

}
