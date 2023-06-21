package it.unibo.alienenterprises.model.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Geometry2D.Point2D;
import it.unibo.alienenterprises.model.geometry.Geometry2D.Vector2D;

public abstract class GameObjectAbs implements GameObject {
    private Point2D position;
    private Vector2D velocity;
    private Map<Statistic,Integer> stats; 
    private Type type;
    private final InputComponent input;
    private final HitboxComponent hitbox;

    public GameObjectAbs(Type type, Point2D pos, Vector2D vector, HitboxComponent hitbox, InputComponent input){
        this.type = type;
        this.position = pos;
        this.velocity = vector;
        this.input = input;
        this.hitbox = hitbox;
    } 

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public abstract boolean isAlive();

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point2D point) {
        position = point;
    }

    @Override
    public Vector2D getVelocity() {
        return velocity;
    }


    @Override
    public void setVelocity(Vector2D vector) {
        velocity = vector;
    }

    @Override
    public List<Component> getAllComponent(){      
        List<Component> component = new ArrayList<>();
        component.add(hitbox);
        component.add(input);
        return component;
    }

    @Override
    public int getStatValue(Statistic stat) {
        return stats.get(stat);
    }

}
