package it.unibo.alienenterprises.controller.renderers;

import it.unibo.alienenterprises.model.api.GameObject;

/**
 * Creates a simple {@link Renderer}.
 * 
 * @author Giulia Bonifazi
 */
public class SimpleRenderer extends RendererAbs {

    /**
     * Creates a new instance of this class.
     * 
     * @param obj   the object
     * @param objID the object id
     */
    public SimpleRenderer(final GameObject obj, final String objID) {
        super(obj, objID);
    }

}
