package it.unibo.alienenterprises.model;

//import java.io.File;
import java.util.*;

import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.ShopModel;
//import javafx.application.Application;

public class ShopModelImpl implements ShopModel {

    private Collection<PowerUp> powerUps = new HashSet<>();
    private UserAccountImpl user = new UserAccountImpl(5000);

    @Override
    public Optional<Integer> check(int ID) {

        populate();
        var PWUiterator = powerUps.iterator();
        while (PWUiterator.hasNext()) {
            PowerUp currPWU = PWUiterator.next();

            System.out.println(currPWU.getCost());

            if (currPWU.getID() == ID) {
                return (this.user.getMoney() - currPWU.getCost() > 0) ? Optional.of(-currPWU.getCost())
                        : Optional.empty();
            }
            // se returna i soldi al negativo io posso toglierli con updateMoney
            // non fai il controllo se si può comprare perchè il bottone si disattiva
            // automaticamente
        }
        return Optional.empty();
    }

    private void populate() {
        /*
         * File file = new File("src/main/resources/examplemvc/PowerUps.yml");
         * ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory()
         * ApplicationConfig config = objectMapper.readValue(file,
         * ApplicationConfig.class);
         * System.out.println(config.toString);
         */
        for (int i = 1; i < 6; i++) {
            this.powerUps.add(new PowerUpImpl(i, (int) Math.pow(i, 3), 5));
        }
        this.powerUps.add(new PowerUpImpl(6, 5000, 5));
    }

    @Override
    public void updateInventory(int ID) {
        this.user.updateInventory(ID);
    }

    public void updateMoney(int changeMoney) {
        this.user.setMoney(changeMoney);
    }

    @Override
    public int getMoney() {
        return this.user.getMoney();
    }

    @Override
    public Set<PowerUp> getInventory /* Stats */() {
        Set<PowerUp> copyInventory = new HashSet<>();
        var iterator = this.user.getInventoryID().iterator();
        System.out.println(this.user.getInventoryID());

        while (iterator.hasNext()) {
            var currentInv = iterator.next();
            System.out.println(currentInv);
            var iter = this.powerUps.iterator();

            while (iter.hasNext()) {
                var currentPWU = iter.next();
                System.out.println(currentPWU);

                if (currentInv == currentPWU.getID()) {
                    copyInventory.add(currentPWU);
                    break;
                }
            }
        }

        return copyInventory;
    }

    @Override
    public int getLevel(int ID) {
        return this.user.getCurrLevel(ID);
    }

}
