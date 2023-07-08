package it.unibo.alienenterprises;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.ShopControllerImpl;
import it.unibo.alienenterprises.controller.api.ShopController;
import it.unibo.alienenterprises.model.ShopModelImpl;
import it.unibo.alienenterprises.model.UserAccountHandlerImpl;
import it.unibo.alienenterprises.model.UserAccountImpl;
import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import it.unibo.alienenterprises.model.api.Statistic;

/**
 * UserAccountTest.
 */
public class UserAccountTest {

    private static final int MONEY = 5000;
    private static final int HIGHSCORE = 80_000;
    private static final int HEALTH_COST = -300;
    private static final int SPEED_COST = -200;
    private static final int HEALTH_STAT = 5;
    private static final int SPEED_STAT = 10;
    private static final String HEALTH = "Health";
    private static final String SPEED = "Speed";
    private static final String NICKNAME = "PaperaTest";
    private static final String PASSWORD = "PaperaPass";

    private final UserAccountHandlerImpl accountHandler = new UserAccountHandlerImpl();

    private final ShopModelImpl shop = new ShopModelImpl();
    private final ShopController controller = new ShopControllerImpl();

    @Test
    void checkLoadandBuy() {

        UserAccountImpl account = accountHandler.registration(NICKNAME, PASSWORD).get();

        assertEquals(NICKNAME, account.getNickname());
        assertEquals(0, account.getMoney());
        assertEquals(0, account.getHighscore());
        /*
         * Fino a qui l'account dovrebbe essere vuoto a meno del nickname, impostato
         * come passato al load
         */

        account.setMoney(MONEY);
        account.setHighscore(HIGHSCORE);
        assertEquals(MONEY, account.getMoney());
        assertEquals(HIGHSCORE, account.getHighscore());
        /*
         * L'account dovrebbe essere riempito con i dati passati
         */

        accountHandler.save(account);

        account = accountHandler.login(NICKNAME, PASSWORD).get();

        assertEquals(NICKNAME, account.getNickname());
        assertEquals(MONEY, account.getMoney());
        assertEquals(HIGHSCORE, account.getHighscore());

        assertEquals(Optional.of(HEALTH_COST), shop.check(HEALTH, account));
        assertEquals(Optional.of(SPEED_COST), shop.check(SPEED, account));
        assertEquals(Optional.of(SPEED_COST), shop.check(SPEED, account));
        shop.updateShop(HEALTH, account, HEALTH_COST);
        shop.updateShop(SPEED, account, SPEED_COST);
        shop.updateShop(SPEED, account, SPEED_COST);

        final Map<String, Integer> map = new HashMap<>();
        map.put(HEALTH, 1);
        map.put(SPEED, 2);
        assertEquals(map, account.getInventory());

        assertEquals(MONEY + HEALTH_COST + SPEED_COST + SPEED_COST, account.getMoney());

        final Map<Statistic, Integer> map2 = new HashMap<>();
        map2.put(Statistic.HP, HEALTH_STAT);
        map2.put(Statistic.DAMAGE, 0);
        map2.put(Statistic.SPEED, SPEED_STAT);
        map2.put(Statistic.DEFENCE, 0);
        map2.put(Statistic.PROJECTILESPEED, 0);
        map2.put(Statistic.COOLDOWN, 0);
        map2.put(Statistic.RECOVERY, 0);
        assertEquals(map2, account.getToAddPwu());
    }

    @Test
    void checkLoadPwu() {
        controller.loadShopModel();
        Set<PowerUp> pwu = controller.getModelPwu();
        Iterator<PowerUp> iter = pwu.iterator();
        while (iter.hasNext()) {
            PowerUp curr = iter.next();
            System.out.println(curr.getId());
        }
    }

    @Test
    void checkLoadInfo() {
        controller.loadShopView();
        Set<PowerUpRenderer> pwuInfo = controller.getViewInfo();
        Iterator<PowerUpRenderer> iter = pwuInfo.iterator();
        while (iter.hasNext()) {
            PowerUpRenderer curr = iter.next();
            System.out.println(curr.getName());
        }
    }
}
