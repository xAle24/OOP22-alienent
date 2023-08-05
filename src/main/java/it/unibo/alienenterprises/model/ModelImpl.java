package it.unibo.alienenterprises.model;

import it.unibo.alienenterprises.model.api.Model;
import it.unibo.alienenterprises.model.api.UserAccountHandler;

public final class ModelImpl implements Model {
    private final UserAccountHandler accountHandler;

    public ModelImpl() {
        accountHandler = new UserAccountHandlerImpl();
    }

    @Override
    public UserAccountHandler getAccountHandler() {
        return this.accountHandler;
    }
}
