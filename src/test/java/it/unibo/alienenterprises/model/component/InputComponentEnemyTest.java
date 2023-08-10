package it.unibo.alienenterprises.model.component;
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
/**
 * Test for enemis input.
 */
public class InputComponentEnemyTest {
    private static final int UPDATETIME = 30;
    private static final int POS50 = 50;
    private static final int POS950 = 950;
    private static final int POS450 = 450;
    private static final Map<Statistic, Integer> STAT = Map.of(Statistic.HP, 5, Statistic.SPEED, 5);
    private static final GameObject PLAYER = new GameObjectAbs(new Point2D(0, 0), null, STAT, "a");
    private static final GameObject SNIPER = new GameObjectAbs(new Point2D(POS950, 0), null, STAT, "b");
    private static final GameObject TANK = new GameObjectAbs(new Point2D(POS450, 0), null, STAT, "c");
    private static final GameObject BOMBER = new GameObjectAbs(new Point2D(POS450, 0), null, STAT, "d");
    /**
     * Test if sniper input works correctly.
     */
    @Test
    public void sniperTest() {
        final InputComponentSniperImpl inputSniper = new InputComponentSniperImpl(SNIPER, true);
        SNIPER.addComponent(inputSniper);
        inputSniper.setTarget(PLAYER);
        inputSniper.update(UPDATETIME);
        assertEquals(Vector2D.fromTwoPointsAndModule(new Point2D(POS950, 0), new Point2D(0, 0), 
            SNIPER.getStatValue(Statistic.SPEED)), SNIPER.getVelocity());
        SNIPER.setPosition(new Point2D(POS50, POS50));
        inputSniper.update(UPDATETIME);
        assertEquals(Vector2D.fromTwoPointsAndModule(new Point2D(POS50, POS50), new Point2D(0, 0), 
            -SNIPER.getStatValue(Statistic.SPEED)), SNIPER.getVelocity());
    }
    /**
     * Test if tank input works correctly.
     */
    @Test
    public void tankTest() {
        final InputComponentTankImpl inputTank = new InputComponentTankImpl(TANK, true);
        TANK.addComponent(inputTank);
        inputTank.setTarget(PLAYER);
        inputTank.update(UPDATETIME);
        assertEquals(Vector2D.fromTwoPointsAndModule(new Point2D(POS450, 0), new Point2D(0, 0), 
            TANK.getStatValue(Statistic.SPEED)), TANK.getVelocity());
        TANK.setPosition(new Point2D(POS50, POS50));
        inputTank.update(UPDATETIME);
        assertEquals(Vector2D.fromTwoPointsAndModule(new Point2D(POS50, POS50), new Point2D(0, 0), 0), 
            TANK.getVelocity());
    }
    /**
     * Test if bomber input works correctly.
     */
    @Test
    public void bomberTest() {
        final InputComponentBomberImpl inputBomber = new InputComponentBomberImpl(BOMBER, true);
        BOMBER.addComponent(inputBomber);
        inputBomber.setTarget(PLAYER);
        inputBomber.update(UPDATETIME);
        assertEquals(Vector2D.fromTwoPointsAndModule(new Point2D(POS450, 0), new Point2D(0, 0), 
            BOMBER.getStatValue(Statistic.SPEED)), BOMBER.getVelocity());
        BOMBER.setPosition(new Point2D(POS50, POS50));
        inputBomber.update(UPDATETIME);
        assertEquals(Vector2D.fromTwoPointsAndModule(new Point2D(POS50, POS50), new Point2D(0, 0), 
            BOMBER.getStatValue(Statistic.SPEED)), BOMBER.getVelocity());
    }
}
