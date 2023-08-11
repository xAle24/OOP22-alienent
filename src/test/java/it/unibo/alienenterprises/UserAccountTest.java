package it.unibo.alienenterprises;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.model.UserAccountHandlerImpl;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.UserAccount;

/**
 * This tests all the methods of UserAccount and UserAccountHandler.
 * 
 * @author Ginevra Bartolini
 */
class UserAccountTest {

  private static final int MONEY = 5000;
  private static final int HIGHSCORE = 80_000;
  private static final String NICKNAME1 = "barboadjas";
  private static final String PASSWORD1 = "T1";
  private static final String NICKNAME2 = "oasadkls";
  private static final String PASSWORD2 = "T2";
  private static final String NICKNAME3 = "awdsde";
  private static final String PASSWORD3 = "T3";
  private static final String NICKNAME4 = "pasljsas";
  private static final String PASSWORD4 = "T4";
  private static final String YAMLPASSWORD = "passwords";
  private static final String HEALTH = "Health";
  private static final String DAMAGE = "Damage";
  private static final String SPEED = "Speed";
  private static final String DEFENCE = "Defence";
  private static final Integer HEALTH_LEVEL = 4;
  private static final Integer DAMAGE_LEVEL = 2;
  private static final Integer SPEED_LEVEL = 3;
  private static final int HEALTH_STAT = 25;
  private static final int DAMAGE_STAT = 10;
  private static final int SPEED_STAT = 15;
  private static final int DEFENCE_STAT = 5;
  private static final String SEPARATOR = "/";
  private static final String GAME_PATH = "src/main/resources/examplemvc";
  private static final String YML = ".yml";

  private final UserAccountHandlerImpl accountHandler = new UserAccountHandlerImpl();
  private UserAccount account;

  /**
   * Tests account registration.
   */
  @Test
  void testAccountRegistration() {
    account = accountHandler.registration(NICKNAME1, PASSWORD1).get();
    delete(NICKNAME1);
    removePassword(NICKNAME1, PASSWORD1);

    assertEquals(NICKNAME1, account.getNickname());
    assertEquals(0, account.getMoney());
    assertEquals(0, account.getHighscore());
    assertEquals(new HashMap<>(), account.getInventory());
    assertEquals(new HashMap<>(), account.getToAddPwu());
    assertEquals(0, account.getCurrLevel(HEALTH));
  }

  /**
   * Tests save and login methods.
   */
  @Test
  void testSaveAndLogin() {
    account = accountHandler.registration(NICKNAME2, PASSWORD2).get();

    accountBuilder();
    accountHandler.save(account);

    final UserAccount secondAaccount = accountHandler.login(NICKNAME2, PASSWORD2).get();

    assertEquals(account.getNickname(), secondAaccount.getNickname());
    assertEquals(account.getMoney(), secondAaccount.getMoney());
    assertEquals(account.getHighscore(), secondAaccount.getHighscore());
    assertEquals(account.getInventory(), secondAaccount.getInventory());
    assertEquals(account.getToAddPwu(), secondAaccount.getToAddPwu());
    assertEquals(account.getCurrLevel(HEALTH), secondAaccount.getCurrLevel(HEALTH));

    delete(NICKNAME2);
    removePassword(NICKNAME2, PASSWORD2);
  }

  /**
   * Tests updateInventory. UpdateInventory should increase by one the level of a
   * pwu, or set it 1 if it is 0.
   */
  @Test
  void testUpdateInventory() {
    account = accountHandler.registration(NICKNAME3, PASSWORD3).get();
    delete(NICKNAME3);
    removePassword(NICKNAME3, PASSWORD3);

    accountBuilder();
    assertEquals(HEALTH_LEVEL, account.getCurrLevel(HEALTH));
    assertEquals(0, account.getCurrLevel(DEFENCE));

    account.updateInventory(HEALTH);
    account.updateInventory(DEFENCE);
    assertEquals(HEALTH_LEVEL + 1, account.getCurrLevel(HEALTH));
    assertEquals(1, account.getCurrLevel(DEFENCE));
  }

  /**
   * Tests UpdateToAddPwu.UpdateToAddPwu should increase the percentage of the
   * added statistic, or simply add the statistic if not present.
   */
  @Test
  void testUpdateToAddPwu() {
    account = accountHandler.registration(NICKNAME4, PASSWORD4).get();
    delete(NICKNAME4);
    removePassword(NICKNAME4, PASSWORD4);
    accountBuilder();
    account.setToAddPwu(new HashMap<Statistic, Integer>());

    final Map<Statistic, Integer> toAddMap = new HashMap<>();
    toAddMap.put(Statistic.HP, HEALTH_STAT);
    toAddMap.put(Statistic.DAMAGE, 0);
    toAddMap.put(Statistic.SPEED, 0);
    toAddMap.put(Statistic.DEFENCE, 0);
    toAddMap.put(Statistic.PROJECTILESPEED, 0);
    toAddMap.put(Statistic.COOLDOWN, 0);
    toAddMap.put(Statistic.RECOVERY, 0);
    account.updateToAddPwu(toAddMap);
    assertEquals(toAddMap, account.getToAddPwu());

    toAddMap.replace(Statistic.DEFENCE, DEFENCE_STAT);
    account.updateToAddPwu(toAddMap);
    toAddMap.replace(Statistic.HP, HEALTH_STAT * 2);
    assertEquals(toAddMap, account.getToAddPwu());

    toAddMap.replace(Statistic.HP, 2);
    account.updateToAddPwu(toAddMap);
    toAddMap.replace(Statistic.HP, HEALTH_STAT * 2 + 2);
    toAddMap.replace(Statistic.DEFENCE, DEFENCE_STAT * 2);
    assertEquals(toAddMap, account.getToAddPwu());

  }

  private void accountBuilder() {
    final Map<String, Integer> inventoryMap = new HashMap<>();
    inventoryMap.put(HEALTH, HEALTH_LEVEL);
    inventoryMap.put(DAMAGE, DAMAGE_LEVEL);
    inventoryMap.put(SPEED, SPEED_LEVEL);

    final Map<Statistic, Integer> toAddMap = new HashMap<>();
    toAddMap.put(Statistic.HP, HEALTH_STAT);
    toAddMap.put(Statistic.DAMAGE, DAMAGE_STAT);
    toAddMap.put(Statistic.SPEED, SPEED_STAT);
    toAddMap.put(Statistic.DEFENCE, 0);
    toAddMap.put(Statistic.PROJECTILESPEED, 0);
    toAddMap.put(Statistic.COOLDOWN, 0);
    toAddMap.put(Statistic.RECOVERY, 0);

    account.setHighscore(HIGHSCORE);
    account.setMoney(MONEY);
    account.setInventory(inventoryMap);
    account.setToAddPwu(toAddMap);

  }

  // CPD-OFF
  @SuppressWarnings("CPD-START")
  private void delete(String nickname) {
    final File deleteFile = new File(GAME_PATH + SEPARATOR + nickname + YML);
    if (deleteFile.exists()) {
      deleteFile.delete();
    }
  }
  // CPD-ON

  @SuppressWarnings("all")
  // CPD-OFF
  private void removePassword(String nickname, String password) {
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
