package it.unibo.alienenterprises.model.impl.components;

import java.util.Optional;
import java.util.function.Supplier;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.ShooterComponent;

public class AutomaticShooterComponent extends ComponentAbs implements ShooterComponent {

    public AutomaticShooterComponent(GameObject object, boolean enabled) {
        super(object, enabled);
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        super.start();
    }

    @Override
    public void update(double deltatime) {
        // TODO Auto-generated method stub
        super.update(deltatime);
    }

    @Override
    public void shoot() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shoot'");
    }

    @Override
    public Supplier<GameObject> getProjectileSupplier() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProjectileSupplier'");
    }

    @Override
    public void setProjectileSupplier(Supplier<GameObject> pSupplier) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setProjectileSupplier'");
    }


    @Override
    public Optional<Component> duplicate(GameObject obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'duplicate'");
    }

}
