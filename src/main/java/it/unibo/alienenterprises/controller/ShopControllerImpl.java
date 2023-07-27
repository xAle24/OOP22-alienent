package it.unibo.alienenterprises.controller;

import java.io.File;
import java.io.FileInputStream;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import it.unibo.alienenterprises.controller.api.ShopController;
import it.unibo.alienenterprises.model.PowerUpImpl;
import it.unibo.alienenterprises.model.PowerUpRendererImpl;
import it.unibo.alienenterprises.model.UserAccountImpl;
import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import it.unibo.alienenterprises.model.api.ShopModel;
import it.unibo.alienenterprises.model.api.Statistic;

public class ShopControllerImpl implements ShopController {

    private static final String SEPARATOR = File.separator;
    private static final String GAME_PATH = "src/main/resources/examplemvc";
    // System.getProperty("user.home") + SEPARATOR + ".Alien Enterprises";

    private UserAccountImpl account;
    private ShopModel model;
    private List<PowerUp> powerUps = new LinkedList<>();
    private List<PowerUpRenderer> pwuInfo = new LinkedList<>();

    public ShopControllerImpl() {
    }

    public ShopControllerImpl(UserAccountImpl account) {
        this.account = account;
    }

    @Override
    public void setModel(ShopModel model) {
        this.model = model;
    }

    @Override
    public boolean buy(String id) {
        Optional<Integer> changeMoney = this.model.check(id);
        if (!changeMoney.isEmpty()) {
            this.model.updateShop(id, changeMoney.get());
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void loadShopModel() {
        try {
            final Constructor constructor = new Constructor(PowerUpImpl.class, new LoaderOptions());
            final TypeDescription accountDescription = new TypeDescription(PowerUpImpl.class);
            accountDescription.addPropertyParameters("id", String.class);
            accountDescription.addPropertyParameters("cost", Integer.class);
            accountDescription.addPropertyParameters("maxLevel", Integer.class);
            accountDescription.addPropertyParameters("statModifiers", Statistic.class, Integer.class);
            constructor.addTypeDescription(accountDescription);

            final Yaml yaml = new Yaml(constructor);
            final FileInputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + "PowerUps.yml");
            final Iterable<Object> documents = yaml.loadAll(inputStream);

            for (final Object object : documents) {
                powerUps.add((PowerUp) object);
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadShopView() {
        try {
            final Constructor constructor = new Constructor(PowerUpRendererImpl.class, new LoaderOptions());
            final TypeDescription accountDescription = new TypeDescription(PowerUpRendererImpl.class);
            accountDescription.addPropertyParameters("name", String.class);
            accountDescription.addPropertyParameters("id", String.class);
            accountDescription.addPropertyParameters("description", String.class);
            accountDescription.addPropertyParameters("image", String.class);
            constructor.addTypeDescription(accountDescription);

            final Yaml yaml = new Yaml(constructor);
            final FileInputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + "PowerUpsInfo.yml");
            final Iterable<Object> documents = yaml.loadAll(inputStream);

            for (final Object object : documents) {
                pwuInfo.add((PowerUpRenderer) object);
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator<PowerUpRenderer> iterator = pwuInfo.iterator();
        while (iterator.hasNext()) {
            PowerUpRenderer curr = iterator.next();
            List<PowerUp> pwu = powerUps.stream().filter(p -> p.getId().equals(curr.getId())).toList();
            curr.setPwu(pwu.get(0));
        }
    }

    @Override
    public List<PowerUpRenderer> getPwuInfo() {
        return this.pwuInfo;
    }

    @Override
    public List<PowerUp> getPwu() {
        return this.powerUps;
    }

    @Override
    public UserAccountImpl getUserAccount() {
        return this.account;
    }

}
