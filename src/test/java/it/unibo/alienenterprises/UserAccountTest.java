package it.unibo.alienenterprises;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.model.ShopModelImpl;
import it.unibo.alienenterprises.model.UserAccountHandlerImpl;
import it.unibo.alienenterprises.model.UserAccountImpl;
import it.unibo.alienenterprises.model.api.Statistic;

/**
 * UserAccountTest.
 */
public class UserAccountTest {

    private static final int MONEY = 5000;
    private static final int HIGHSCORE = 80000;
    private static final int HEALTH_COST = -300;
    private static final int SPEED_COST = -200;

    private UserAccountImpl account = new UserAccountImpl();
    private UserAccountHandlerImpl accountHandler = new UserAccountHandlerImpl();
    private String nickname = "PaperaTest";
    private String password = "PaperaPass";

    private ShopModelImpl shop = new ShopModelImpl();

    @Test
    void checkLoadandBuy() {
        try {
            account = accountHandler.load(nickname, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(nickname, account.getNickname());
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

        try {
            account = accountHandler.load(nickname, password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(nickname, account.getNickname());
        assertEquals(MONEY, account.getMoney());
        assertEquals(HIGHSCORE, account.getHighscore());

        assertEquals(Optional.of(HEALTH_COST), shop.check("Health", account));
        assertEquals(Optional.of(SPEED_COST), shop.check("Speed", account));
        assertEquals(Optional.of(SPEED_COST), shop.check("Speed", account));
        shop.updateShop("Health", account, HEALTH_COST);
        shop.updateShop("Speed", account, SPEED_COST);
        shop.updateShop("Speed", account, SPEED_COST);

        Map<String, Integer> map = new HashMap<>();
        map.put("Health", 1);
        map.put("Speed", 2);
        assertEquals(map, account.getInventory());

        assertEquals(MONEY + HEALTH_COST + SPEED_COST + SPEED_COST, account.getMoney());

        System.out.println(account.getToAddPwu());
        Map<Statistic, Integer> map2 = new HashMap<>();
        map2.put((Statistic.HP), 5);
        map2.put((Statistic.DAMAGE), 0);
        map2.put((Statistic.SPEED), 10);
        map2.put((Statistic.DEFENCE), 0);
        map2.put((Statistic.PROJECTILESPEED), 0);
        map2.put((Statistic.COOLDOWN), 0);
        map2.put((Statistic.RECOVERY), 0);
        assertEquals(map2, account.getToAddPwu());
    }
}
