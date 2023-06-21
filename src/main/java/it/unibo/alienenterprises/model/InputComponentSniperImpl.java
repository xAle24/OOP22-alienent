package it.unibo.alienenterprises.model;

import java.util.Random;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Geometry2D;
import it.unibo.alienenterprises.model.geometry.Geometry2D.Vector2D;

public class InputComponentSniperImpl implements InputComponent {

    Vector2D movement = new Vector2D(0, 0);
    boolean right, up = false;

    @Override
    public void handle(GameObject object) {
        object.setPosition(Geometry2D.traslate(object.getPosition(), movement));
        object.setVelocity(movement);
    }
    
    public void calculateMovement(GameObject enemy, GameObject player) {
        //movement on right/left direction
        if (enemy.getPosition().x() > player.getPosition().x() && Math.abs(enemy.getPosition().x() - player.getPosition().x()) > 700) {
            right = false;
        } else if(enemy.getPosition().x() > player.getPosition().x() && Math.abs(enemy.getPosition().x() - player.getPosition().x()) < 700){
            right = true;
        } else if(enemy.getPosition().x() < player.getPosition().x() && Math.abs(enemy.getPosition().x() - player.getPosition().x()) < 700) {
            right = false;
        } else {
            right = true;
        }

        //movement on up/down direction
        if (enemy.getPosition().y() > player.getPosition().y() && Math.abs(enemy.getPosition().y() - player.getPosition().y()) > 700) {
            right = false;
        } else if(enemy.getPosition().y() > player.getPosition().y() && Math.abs(enemy.getPosition().y() - player.getPosition().y()) < 700){
            right = true;
        } else if(enemy.getPosition().y() < player.getPosition().y() && Math.abs(enemy.getPosition().y() - player.getPosition().y()) < 700) {
            right = false;
        } else {
            right = true;
        }

        Random rnd = new Random(0);

        if (right && up) {
            movement = new Vector2D(rnd.nextInt(91), 0);
        } else if (!right && up){
            movement = new Vector2D(rnd.nextInt(91)+90, 0);
        } else if (!right && !up){
            movement = new Vector2D(rnd.nextInt(91)+180, 0);
        } else {
            movement = new Vector2D(rnd.nextInt(91)+270, 0);
        }
    }
}
