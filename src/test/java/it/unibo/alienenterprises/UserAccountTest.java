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

public class UserAccountTest {

    private static final int MONEY = 5000;
    private static final int HIGHSCORE = 80000;
    private static final int HEALTH_COST = -300;

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
        shop.updateInventory("Health", account);
        Map<String, Integer> map = new HashMap<>();
        map.put("Health", 1);
        assertEquals(map, account.getInventory());

        shop.updateMoney(HEALTH_COST, account);
        assertEquals(5000 + HEALTH_COST, account.getMoney());

    }
}
