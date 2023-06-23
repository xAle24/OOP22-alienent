package it.unibo.alienenterprises.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.ShopModel;
import it.unibo.alienenterprises.model.api.Statistic;

public class ShopModelImpl implements ShopModel {

    private static final String SEPARATOR = File.separator;
    private static final String GAME_PATH = "src/main/resources/examplemvc";
    // System.getProperty("user.home") + SEPARATOR + ".Alien Enterprises";

    private Collection<PowerUp> powerUps = new HashSet<>();

    public ShopModelImpl() {
        loadPWU();
    }

    @Override
    public Optional<Integer> check(String ID, UserAccountImpl user) {

        var PWUiterator = powerUps.iterator();
        while (PWUiterator.hasNext()) {
            PowerUp currPWU = PWUiterator.next();

            if (currPWU.getID().equals(ID)) {
                return (user.getMoney() - currPWU.getCost() > 0) ? Optional.of(-currPWU.getCost())
                        : Optional.empty();
            }
            // se returna i soldi al negativo io posso toglierli con updateMoney
            // non fai il controllo se si può comprare perchè il bottone si disattiva
            // automaticamente
        }
        return Optional.empty();
    }

    @Override
    public void updateInventory(String ID, UserAccountImpl user) {
        user.updateInventory(ID);
    }

    public void updateMoney(int changeMoney, UserAccountImpl user) {
        user.setMoney(changeMoney);
    }

    @Override
    public int getMoney(UserAccountImpl user) {
        return user.getMoney();
    }

    @Override
    public Set<PowerUp> getInventory /* Stats */(UserAccountImpl user) {
        Set<PowerUp> copyInventory = new HashSet<>();
        var iterator = user.getInventoryID().iterator();
        System.out.println(user.getInventoryID());

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
    public int getLevel(String ID, UserAccountImpl user) {
        return user.getCurrLevel(ID);
    }

    @Override
    public void loadPWU() {
        try {
            Constructor constructor = new Constructor(PowerUpImpl.class, new LoaderOptions());
            TypeDescription accountDescription = new TypeDescription(PowerUpImpl.class);
            accountDescription.addPropertyParameters("ID", String.class);
            accountDescription.addPropertyParameters("cost", Integer.class);
            accountDescription.addPropertyParameters("maxLevel", Integer.class);
            accountDescription.addPropertyParameters("statModifiers", Statistic.class, Integer.class);
            constructor.addTypeDescription(accountDescription);

            final Yaml yaml = new Yaml(constructor);
            FileInputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + "PowerUps.yml");
            Iterable<Object> documents = yaml.loadAll(inputStream);
            System.out.println(documents.toString());
            for (Object object : documents) {
                System.out.println(object.toString());
                powerUps.add((PowerUp) object);
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
