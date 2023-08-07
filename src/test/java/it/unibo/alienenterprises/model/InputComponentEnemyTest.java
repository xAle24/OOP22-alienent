package it.unibo.alienenterprises.model;
/**
 * Test class for enemy input component.
 */
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.GameObjectAbs;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.components.InputComponentBomberImpl;
import it.unibo.alienenterprises.model.impl.components.InputComponentSniperImpl;
import it.unibo.alienenterprises.model.impl.components.InputComponentTankImpl;

public class InputComponentEnemyTest {
    private static final Map<Statistic, Integer> stat = Map.of(Statistic.HP, 5, Statistic.SPEED, 5);
    private static final GameObject player = new GameObjectAbs(new Point2D(0, 0), null, stat);
    private static final GameObject sniper = new GameObjectAbs(new Point2D(950, 0), null, stat);
    private static final GameObject tank = new GameObjectAbs(new Point2D(450, 0), null, stat);
    private static final GameObject bomber = new GameObjectAbs(new Point2D(450, 0), null, stat);

    @Test
    public void SniperTest() {
        final InputComponentSniperImpl inputSniper = new InputComponentSniperImpl(sniper, true);
        sniper.addComponent(inputSniper);
        inputSniper.setTarget(player);
        inputSniper.update(30);
        assertEquals(Vector2D.fromTwoPointsAndModule(new Point2D(950, 0), new Point2D(0, 0), sniper.getStatValue(Statistic.SPEED)), sniper.getVelocity());
        sniper.setPosition(new Point2D(50, 50));
        inputSniper.update(30);
        assertEquals(Vector2D.fromTwoPointsAndModule(new Point2D(50, 50), new Point2D(0, 0), -sniper.getStatValue(Statistic.SPEED)), sniper.getVelocity());
    }
    @Test
    public void TankTest() {
        final InputComponentTankImpl inputTank = new InputComponentTankImpl(tank, true);
        tank.addComponent(inputTank);
        inputTank.setTarget(player);
        inputTank.update(30);
        assertEquals(Vector2D.fromTwoPointsAndModule(new Point2D(450, 0), new Point2D(0, 0), tank.getStatValue(Statistic.SPEED)), tank.getVelocity());
        tank.setPosition(new Point2D(50, 50));
        inputTank.update(30);
        assertEquals(Vector2D.fromTwoPointsAndModule(new Point2D(50, 50), new Point2D(0, 0), 0), tank.getVelocity());
    }
    @Test
    public void BomberTest() {
        final InputComponentBomberImpl inputBomber = new InputComponentBomberImpl(bomber, true);
        bomber.addComponent(inputBomber);
        inputBomber.setTarget(player);
        inputBomber.update(30);
        assertEquals(Vector2D.fromTwoPointsAndModule(new Point2D(450, 0), new Point2D(0, 0), bomber.getStatValue(Statistic.SPEED)), bomber.getVelocity());
        bomber.setPosition(new Point2D(50, 50));
        inputBomber.update(30);
        assertEquals(Vector2D.fromTwoPointsAndModule(new Point2D(50, 50), new Point2D(0, 0), bomber.getStatValue(Statistic.SPEED)), bomber.getVelocity());
    }
}
