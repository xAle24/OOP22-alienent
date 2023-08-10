package it.unibo.alienenterprises.controller.renderers;

public interface RendererManager<T> extends Renderable {

    /**
     * Adds one object the list by creating its {@link Renderer}.
     * 
     * @param obj the object to be rendered
     */
    public void addRenderer(T obj);
}
