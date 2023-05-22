package it.unibo.alienenterprises.model.api;

import java.util.List;

import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.geometry.Geometry2D.Point2D;
import it.unibo.alienenterprises.model.geometry.Geometry2D.Vector2D;

public abstract class GameObjectAbs implements GameObject {
    private Point2D position;
    private Vector2D velocity;

    @Override
    public Type getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

    @Override
    public boolean isAlive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAlive'");
    }

    @Override
    public Point2D getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public Vector2D getVelocity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVelocity'");
    }

    @Override
    public abstract List<Component> getAllComponent();

}
