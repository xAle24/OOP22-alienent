package it.unibo.alienenterprises.model.api.components;

import it.unibo.alienenterprises.model.api.GameObject;

public abstract class ComponentAbs implements Component{

    private final GameObject gameObject;
    private boolean enabled;

    public ComponentAbs(GameObject object, boolean enabled) {
        this.gameObject = object;
        this.enabled = enabled;
    }

    @Override
    public void Update(double deltatime) {
        //vuoto
    }

    @Override
    public void start() {
        //vuoto
    }

    @Override
    public void enable() {
        this.enabled = true;
    }

    @Override
    public void disable() {
        this.enabled = false;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    protected GameObject getGameObject(){
        return this.gameObject;
    }
    
}
