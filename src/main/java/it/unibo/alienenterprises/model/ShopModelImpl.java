package it.unibo.alienenterprises.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.ShopModel;
import it.unibo.alienenterprises.model.api.Statistic;

/**
 * ShopModelImpl.
 */
public class ShopModelImpl implements ShopModel {

    private static final String SEPARATOR = File.separator;
    private static final String GAME_PATH = "src/main/resources/examplemvc";
    // System.getProperty("user.home") + SEPARATOR + ".Alien Enterprises";

    private Set<PowerUp> powerUps = new HashSet<>();

    public ShopModelImpl() {
        loadPwu();
    }

    @Override
    public Optional<Integer> check(String id, UserAccountImpl user) {

        var pwuIterator = powerUps.iterator();
        while (pwuIterator.hasNext()) {
            PowerUp currPwu = pwuIterator.next();

            if (currPwu.getId().equals(id)) {
                return (user.getMoney() - currPwu.getCost() > 0) ? Optional.of(-currPwu.getCost())
                        : Optional.empty();
            }
            // se returna i soldi al negativo io posso toglierli con updateMoney
            // non fai il controllo se si può comprare perchè il bottone si disattiva
            // automaticamente
        }
        return Optional.empty();
    }

    @Override
    public void updateShop(String id, UserAccountImpl user, int changeMoney) {
        user.updateInventory(id);
        user.setMoney(changeMoney);
        user.equals(updateToAddPwu(id, user));
    }

    @Override
    public void loadPwu() {
        try {
            Constructor constructor = new Constructor(PowerUpImpl.class, new LoaderOptions());
            TypeDescription accountDescription = new TypeDescription(PowerUpImpl.class);
            accountDescription.addPropertyParameters("id", String.class);
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

    private UserAccountImpl updateToAddPwu(String id, UserAccountImpl user) {
        var iterator = powerUps.iterator();
        while (iterator.hasNext()) {
            var curr = iterator.next();

            if (curr.getId().equals(id)) {
                Map<Statistic, Integer> map = curr.getStatModifiers();
                user.updateToAddPwu(map);
            }
        }
        return user;
    }

}
