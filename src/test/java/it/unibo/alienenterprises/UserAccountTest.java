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
 * UserAccountTest.
 */
public class UserAccountTest {

  private static final int MONEY = 5000;
  private static final int HIGHSCORE = 80_000;
  private static final String NICKNAME = "AccountTest";
  private static final String PASSWORD = "AccountPass";
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
  private static final String SEPARATOR = File.separator;
  private static final String GAME_PATH = "src/main/resources/examplemvc";
  private static final String YML = ".yml";

  private final UserAccountHandlerImpl accountHandler = new UserAccountHandlerImpl();
  private UserAccount account;

  public UserAccountTest() {
    account = accountHandler.registration(NICKNAME, PASSWORD).get();
    delete();
    removePassword();
  }

  @Test
  public void testAccountRegistration() {
    /*
     * An account is registered. The only information available should be
     * its nickname, its money, equals 0, and its highscore, equals 0.
     * Other info should be 0 or empty HashMaps.
     */
    assertEquals(NICKNAME, account.getNickname());
    assertEquals(0, account.getMoney());
    assertEquals(0, account.getHighscore());
    assertEquals(new HashMap<>(), account.getInventory());
    assertEquals(new HashMap<>(), account.getToAddPwu());
    assertEquals(0, account.getCurrLevel(HEALTH));
  }

  @Test
  public void testSaveAndLogin() {
    /*
     * We test save and login. If the account is the same before and after
     * login, they work as they should.
     */
    account = accountHandler.registration(NICKNAME, PASSWORD).get();

    account = accountBuilder(account);
    accountHandler.save(account);

    UserAccount secondAaccount = accountHandler.login(NICKNAME, PASSWORD).get();

    assertEquals(account.getNickname(), secondAaccount.getNickname());
    assertEquals(account.getMoney(), secondAaccount.getMoney());
    assertEquals(account.getHighscore(), secondAaccount.getHighscore());
    assertEquals(account.getInventory(), secondAaccount.getInventory());
    assertEquals(account.getToAddPwu(), secondAaccount.getToAddPwu());
    assertEquals(account.getCurrLevel(HEALTH), secondAaccount.getCurrLevel(HEALTH));

    delete();
    removePassword();
  }

  @Test
  public void testUpdateInventory() {
    /*
     * UpdateInventory should increase by one the level of a pwu, or set it 1 if it
     * is 0.
     */
    account = accountBuilder(account);
    assertEquals(HEALTH_LEVEL, account.getCurrLevel(HEALTH));
    assertEquals(0, account.getCurrLevel(DEFENCE));

    account.updateInventory(HEALTH);
    account.updateInventory(DEFENCE);
    assertEquals(HEALTH_LEVEL + 1, account.getCurrLevel(HEALTH));
    assertEquals(1, account.getCurrLevel(DEFENCE));
  }

  @Test
  public void testUpdateToAddPwu() {
    /*
     * UpdateToAddPwu should increase the percentage of the added statistic, or
     * simply add the statistic if not present.
     */
    account = accountBuilder(account);
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

  private UserAccount accountBuilder(UserAccount account) {
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

    return account;
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
    } catch (IOException e) {
    }
  }

  /*
   * account.setMoney(MONEY);
   * account.setHighscore(HIGHSCORE);
   * assertEquals(MONEY, account.getMoney());
   * assertEquals(HIGHSCORE, account.getHighscore());
   * /*
   * L'account dovrebbe essere riempito con i dati passati
   * 
   * 
   * accountHandler.save(account);
   * 
   * account = accountHandler.login(NICKNAME, PASSWORD).get();
   * 
   * assertEquals(NICKNAME, account.getNickname());
   * assertEquals(MONEY, account.getMoney());
   * assertEquals(HIGHSCORE, account.getHighscore());
   * 
   * assertEquals(Optional.of(HEALTH_COST), shop.check(HEALTH));
   * assertEquals(Optional.of(SPEED_COST), shop.check(SPEED));
   * assertEquals(Optional.of(SPEED_COST), shop.check(SPEED));
   * shop.updateShop(HEALTH, HEALTH_COST);
   * shop.updateShop(SPEED, SPEED_COST);
   * shop.updateShop(SPEED, SPEED_COST);
   * 
   * final Map<String, Integer> map = new HashMap<>();
   * map.put(HEALTH, 1);
   * map.put(SPEED, 2);
   * assertEquals(map, account.getInventory());
   * 
   * assertEquals(MONEY + HEALTH_COST + SPEED_COST + SPEED_COST,
   * account.getMoney());
   * 
   * final Map<Statistic, Integer> map2 = new HashMap<>();
   * map2.put(Statistic.HP, HEALTH_STAT);
   * map2.put(Statistic.DAMAGE, 0);
   * map2.put(Statistic.SPEED, SPEED_STAT);
   * map2.put(Statistic.DEFENCE, 0);
   * map2.put(Statistic.PROJECTILESPEED, 0);
   * map2.put(Statistic.COOLDOWN, 0);
   * map2.put(Statistic.RECOVERY, 0);
   * assertEquals(map2, account.getToAddPwu());
   * }
   */
}