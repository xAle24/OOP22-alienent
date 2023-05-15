package it.unibo.alienenterprises.model.api.components;

import it.unibo.alienenterprises.model.api.Command;

/**
 * InputComponent
 */
public interface InputComponent extends Component {

    /**
     * Metodo non definitivo ancora da decidere
     * 
     * @param command
     */
    void addInput(Command command);
}
