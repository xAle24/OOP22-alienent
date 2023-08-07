package it.unibo.alienenterprises.model.api.components;

import it.unibo.alienenterprises.model.api.GameObject;

/**
 * Enemy input component.
 */
public interface EnemyInputComponent extends InputComponent{
    /**
     * Set target of the enemy.
     * @param target what the enemy follow.
     */
    void setTarget(final GameObject target);
}
