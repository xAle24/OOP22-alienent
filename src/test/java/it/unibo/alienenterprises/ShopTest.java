package it.unibo.alienenterprises;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.ControllerImpl;
import it.unibo.alienenterprises.controller.ShopControllerImpl;
import it.unibo.alienenterprises.controller.api.ShopController;
import it.unibo.alienenterprises.model.PowerUpImpl;
import it.unibo.alienenterprises.model.ShopModelImpl;
import it.unibo.alienenterprises.model.UserAccountHandlerImpl;
import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.ShopModel;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.model.api.UserAccount;
/*import javafx.scene.Scene;*/
import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.controllers.InitController;

/**
 * This tests all the methods of ShopModel and those that are related to the buy
 * action.
 * 
 * @author Ginevra Bartolini
 */
class ShopTest {

    private static final String NICKNAME1 = "afidfhkhi";
    private static final String PASSWORD1 = "T1";
    private static final String NICKNAME2 = "oijawe";
    private static final String PASSWORD2 = "T2";
    private static final String NICKNAME3 = "pASASCO";
    private static final String PASSWORD3 = "T3";
    private static final String NICKNAME4 = "asclco";
    private static final String PASSWORD4 = "T4";
    private static final int MONEY = 2_000_000;
    private static final int REMAINING_MONEY = 400_000;
    private static final String YAMLPASSWORD = "passwords";
    private static final String HEALTH = "Health";
    private static final String DAMAGE = "Damage";
    private static final String SPEED = "Speed";
    private static final int HEALTH_COST = 300_000;
    private static final int SPEED_COST = 200_000;
    private static final int DAMAGE_COST = 500_000;
    private static final int HEALTH_MAXLEVEL = 5;
    private static final int SPEED_MAXLEVEL = 3;
    private static final int DAMAGE_MAXLEVEL = 2;
    private static final int STAT = 5;
    private static final String SEPARATOR = "/";
    private static final String GAME_PATH = "src/main/resources/examplemvc";
    private static final String YML = ".yml";

    private final UserAccountHandlerImpl accountHandler = new UserAccountHandlerImpl();
    private UserAccount account;
    private final Controller contr = new ControllerImpl(new View() {

        @Override
        public void init(final Controller controller) {
        }

        @Override
        public void setScene(final ViewType type) {
        }

        @Override
        public Point2D getWidthHeight() {
            return null;
        }

    });
    private final InitController controller = new ShopControllerImpl();
    private final ShopController shopController = (ShopController) controller;
    private final ShopModel model = new ShopModelImpl(contr);
    private final List<PowerUp> pwu = new LinkedList<>();
    private final Map<Statistic, Integer> stats = new LinkedHashMap<>();

    /**
     * It tests if the shopController load the yaml file correctly.
     */
    @Test
    void testLoadPwu() {
        account = accountHandler.registration(NICKNAME1, PASSWORD1).get();
        account.setMoney(MONEY);
        contr.setUserAccount(account);
        delete(NICKNAME1);
        removePassword(NICKNAME1, PASSWORD1);

        buildPwuList();
        this.shopController.loadPwuYaml();

        this.shopController.getPwu().stream()
                .filter(sp -> this.pwu.stream().anyMatch(lp -> lp.getId().equals(sp.getId())))
                .forEach(sp -> {
                    final PowerUp currPwu = this.pwu.stream().filter(lp -> lp.getId().equals(sp.getId())).findFirst()
                            .orElse(null);
                    assertNotNull(currPwu);
                    assertEquals(currPwu.getCost(), sp.getCost());
                    assertEquals(currPwu.getMaxLevel(), sp.getMaxLevel());
                    assertEquals(currPwu.getStatModifiers(), sp.getStatModifiers());
                });
    }

    /**
     * It tests if the check function in ShopModel works correctly.
     */
    @Test
    void testCheck() {
        account = accountHandler.registration(NICKNAME2, PASSWORD2).get();
        account.setMoney(MONEY);
        contr.setUserAccount(account);
        delete(NICKNAME2);
        removePassword(NICKNAME2, PASSWORD2);

        this.shopController.loadPwuYaml();
        this.model.loadPwu(this.shopController.getPwu());
        assertEquals(Optional.of(-HEALTH_COST), model.check(HEALTH));
        assertEquals(Optional.of(-SPEED_COST), model.check(SPEED));
        assertEquals(Optional.of(-DAMAGE_COST), model.check(DAMAGE));

        account.setMoney(-account.getMoney());
        assertEquals(Optional.empty(), model.check(HEALTH));
        assertEquals(Optional.empty(), model.check(SPEED));
        assertEquals(Optional.empty(), model.check(DAMAGE));
    }

    /**
     * It tests if updateShop updates user fiels correctly.
     */
    @Test
    void testUpdateShop() {
        account = accountHandler.registration(NICKNAME3, PASSWORD3).get();
        account.setMoney(MONEY);
        contr.setUserAccount(account);
        delete(NICKNAME3);
        removePassword(NICKNAME3, PASSWORD3);

        this.shopController.loadPwuYaml();
        this.model.loadPwu(this.shopController.getPwu());

        this.model.updateShop(HEALTH, model.check(HEALTH).get());
        this.model.updateShop(HEALTH, model.check(HEALTH).get());
        this.model.updateShop(DAMAGE, model.check(DAMAGE).get());
        this.model.updateShop(SPEED, model.check(SPEED).get());

        assertEquals(2, account.getCurrLevel(HEALTH));
        assertEquals(1, account.getCurrLevel(DAMAGE));
        assertEquals(1, account.getCurrLevel(SPEED));
        assertEquals(REMAINING_MONEY, account.getMoney());
        assertEquals(buildToAddPwu(), account.getToAddPwu());
    }

    /**
     * Test if everything works given the starting point is the buy method in
     * ShopController.
     */
    @Test
    void testBuy() {
        account = accountHandler.registration(NICKNAME4, PASSWORD4).get();
        account.setMoney(MONEY);
        contr.setUserAccount(account);
        delete(NICKNAME4);
        removePassword(NICKNAME4, PASSWORD4);
        this.shopController.loadPwuYaml();
        controller.init(contr, null);

        assertTrue(shopController.buy(HEALTH));
        assertTrue(shopController.buy(HEALTH));
        assertTrue(shopController.buy(SPEED));
        assertTrue(shopController.buy(DAMAGE));

        assertEquals(2, account.getCurrLevel(HEALTH));
        assertEquals(1, account.getCurrLevel(DAMAGE));
        assertEquals(1, account.getCurrLevel(SPEED));
        assertEquals(REMAINING_MONEY, account.getMoney());
        assertEquals(buildToAddPwu(), account.getToAddPwu());

        assertFalse(shopController.buy(DAMAGE));
    }

    private Map<Statistic, Integer> buildToAddPwu() {
        stats.put(Statistic.HP, STAT * 2);
        stats.put(Statistic.SPEED, STAT);
        stats.put(Statistic.DAMAGE, STAT);
        stats.put(Statistic.DEFENCE, 0);
        stats.put(Statistic.PROJECTILESPEED, 0);
        stats.put(Statistic.COOLDOWN, 0);
        stats.put(Statistic.RECOVERY, 0);
        return stats;
    }

    private void buildPwuList() {
        final PowerUp health = new PowerUpImpl();
        health.setId(HEALTH);
        health.setCost(HEALTH_COST);
        health.setMaxLevel(HEALTH_MAXLEVEL);
        stats.put(Statistic.HP, STAT);
        stats.put(Statistic.SPEED, 0);
        stats.put(Statistic.DAMAGE, 0);
        stats.put(Statistic.DEFENCE, 0);
        stats.put(Statistic.PROJECTILESPEED, 0);
        stats.put(Statistic.COOLDOWN, 0);
        stats.put(Statistic.RECOVERY, 0);
        health.setStatModifiers(stats);
        pwu.add(health);

        final PowerUp speed = new PowerUpImpl();
        speed.setId(SPEED);
        speed.setCost(SPEED_COST);
        speed.setMaxLevel(SPEED_MAXLEVEL);
        stats.replace(Statistic.HP, 0);
        stats.replace(Statistic.SPEED, STAT);
        speed.setStatModifiers(stats);
        pwu.add(speed);

        final PowerUp damage = new PowerUpImpl();
        damage.setId(DAMAGE);
        damage.setCost(DAMAGE_COST);
        damage.setMaxLevel(DAMAGE_MAXLEVEL);
        stats.replace(Statistic.SPEED, 0);
        stats.replace(Statistic.DAMAGE, STAT);
        damage.setStatModifiers(stats);
        pwu.add(damage);
    }

    // CPD-OFF
    @SuppressWarnings("CPD-START")
    private void delete(final String nickname) {
        final File deleteFile = new File(GAME_PATH + SEPARATOR + nickname + YML);
        if (deleteFile.exists()) {
            deleteFile.delete();
        }
    }
    // CPD-ON

    @SuppressWarnings("all")
    // CPD-OFF
    private void removePassword(final String nickname, final String password) {
        try {
            final List<String> yamlLines = Files.readAllLines(Paths.get(GAME_PATH + SEPARATOR + YAMLPASSWORD + YML));
            final String check = "--- {" + nickname + ": " + password + "}";
            for (int i = 0; i < yamlLines.size(); i++) {
                if (yamlLines.get(i).equals(check)) {
                    yamlLines.remove(i);
                }
            }
            Files.write(Paths.get(GAME_PATH + SEPARATOR + YAMLPASSWORD + YML), yamlLines);
        } catch (IOException i) {
        }

    }
    // CPD-ON
}
