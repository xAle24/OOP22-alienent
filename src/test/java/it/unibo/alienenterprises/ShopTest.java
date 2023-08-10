package it.unibo.alienenterprises;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
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

public class ShopTest {
    /*
     * private static final int MONEY = 5000;
     * private static final int HIGHSCORE = 80_000;
     */
    private static final String NICKNAME = "AccountTest";
    private static final String PASSWORD = "AccountPass";
    private static final int MONEY = 2000;
    private static final int REMAINING_MONEY = 400;
    private static final String YAMLPASSWORD = "passwords";
    private static final String HEALTH = "Health";
    private static final String DAMAGE = "Damage";
    private static final String SPEED = "Speed";
    private static final int HEALTH_COST = 300;
    private static final int SPEED_COST = 200;
    private static final int DAMAGE_COST = 500;
    private static final int HEALTH_MAXLEVEL = 5;
    private static final int SPEED_MAXLEVEL = 3;
    private static final int DAMAGE_MAXLEVEL = 2;
    private static final int STAT = 5;
    private static final String SEPARATOR = File.separator;
    private static final String GAME_PATH = "src/main/resources/examplemvc";
    private static final String YML = ".yml";

    private final UserAccountHandlerImpl accountHandler = new UserAccountHandlerImpl();
    private UserAccount account;
    private Controller contr = new ControllerImpl(new View() {

        @Override
        public void init(Controller controller) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'init'");
        }

        @Override
        public void setScene(ViewType type) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setScene'");
        }

        @Override
        public Point2D getWidthHeight() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getWidthHeight'");
        }

    });
    private InitController controller = new ShopControllerImpl();
    private ShopController shopController = (ShopController) controller;
    private ShopModel model = new ShopModelImpl(contr);
    private List<PowerUp> pwu = new LinkedList<>();
    private Map<Statistic, Integer> stats = new LinkedHashMap<>();

    public ShopTest() {
        account = accountHandler.registration(NICKNAME, PASSWORD).get();
        account.setMoney(MONEY);
        contr.setUserAccount(account);
        delete();
        removePassword();
    }

    @Test
    public void testLoadPwu() {
        buildPwuList();
        this.shopController.loadPwuYaml();

        this.shopController.getPwu().stream()
                .filter(sp -> this.pwu.stream().anyMatch(lp -> lp.getId().equals(sp.getId())))
                .forEach(sp -> {
                    PowerUp currPwu = this.pwu.stream().filter(lp -> lp.getId().equals(sp.getId())).findFirst()
                            .orElse(null);
                    assertNotNull(currPwu);
                    assertEquals(currPwu.getCost(), sp.getCost());
                    assertEquals(currPwu.getMaxLevel(), sp.getMaxLevel());
                    assertEquals(currPwu.getStatModifiers(), sp.getStatModifiers());
                });
    }

    @Test
    public void testCheck() {
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

    @Test
    public void testUpdateShop() {
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

    @Test
    public void testBuy() {
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
        PowerUp health = new PowerUpImpl();
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

        PowerUp speed = new PowerUpImpl();
        speed.setId(SPEED);
        speed.setCost(SPEED_COST);
        speed.setMaxLevel(SPEED_MAXLEVEL);
        stats.replace(Statistic.HP, 0);
        stats.replace(Statistic.SPEED, STAT);
        speed.setStatModifiers(stats);
        pwu.add(speed);

        PowerUp damage = new PowerUpImpl();
        damage.setId(DAMAGE);
        damage.setCost(DAMAGE_COST);
        damage.setMaxLevel(DAMAGE_MAXLEVEL);
        stats.replace(Statistic.SPEED, 0);
        stats.replace(Statistic.DAMAGE, STAT);
        damage.setStatModifiers(stats);
        pwu.add(damage);
    }

    private void delete() {
        File deleteFile = new File(GAME_PATH + SEPARATOR + NICKNAME + YML);
        if (deleteFile.exists()) {
            try {
                deleteFile.delete();
            } catch (Exception e) {
            }
        }
    }

    private void removePassword() {
        try {
            List<String> yamlLines = Files.readAllLines(Paths.get(GAME_PATH + SEPARATOR + YAMLPASSWORD + YML));
            String check = "--- {" + NICKNAME + ": " + PASSWORD + "}";
            if (yamlLines.get(yamlLines.size() - 1).equals(check)) {
                yamlLines.remove(yamlLines.size() - 1);
                Files.write(Paths.get(GAME_PATH + SEPARATOR + YAMLPASSWORD + YML), yamlLines);
            }
        } catch (Exception e) {
        }
    }
}
