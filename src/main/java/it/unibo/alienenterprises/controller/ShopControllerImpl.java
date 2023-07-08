package it.unibo.alienenterprises.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import it.unibo.alienenterprises.controller.api.ShopController;
import it.unibo.alienenterprises.model.PowerUpImpl;
import it.unibo.alienenterprises.model.PowerUpRendererImpl;
import it.unibo.alienenterprises.model.ShopModelImpl;
import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import it.unibo.alienenterprises.model.api.ShopModel;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.view.ShopViewImpl;
import it.unibo.alienenterprises.view.api.ShopView;

public class ShopControllerImpl implements ShopController {

    private static final String SEPARATOR = File.separator;
    private static final String GAME_PATH = "src/main/resources/examplemvc";
    // System.getProperty("user.home") + SEPARATOR + ".Alien Enterprises";

    private ShopView view = new ShopViewImpl();
    private ShopModel model = new ShopModelImpl();
    private Set<PowerUp> powerUps = new HashSet<>();
    private Set<PowerUpRenderer> pwuInfo = new HashSet<>();

    @Override
    public void buy(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buy'");
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
        this.model.loadPwu(powerUps);
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
            PowerUp pwu = powerUps.stream().filter(p -> p.getId() == curr.getId()).findFirst()
                    .orElse(null);
            curr.setPwu(pwu);
        }
        this.view.loadPwuInfo(pwuInfo);
    }

    @Override
    public void showShop(UserAccount user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showShop'");
    }

    @Override
    public Set<PowerUp> getModelPwu() {
        return this.model.getPwu();
    }

    @Override
    public Set<PowerUpRenderer> getViewInfo() {
        return this.view.getInfo();
    }

}
