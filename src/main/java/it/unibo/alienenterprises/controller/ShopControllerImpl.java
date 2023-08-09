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
import it.unibo.alienenterprises.model.ShopModelImpl;
import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import it.unibo.alienenterprises.model.api.ShopModel;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.controllers.InitController;
import javafx.scene.Scene;

/**
 * Shop Controller implementation.
 */
public class ShopControllerImpl implements ShopController, InitController {

    private static final String SEPARATOR = File.separator;
    private static final String GAME_PATH = "src/main/resources/examplemvc";
    // System.getProperty("user.home") + SEPARATOR + ".Alien Enterprises";

    private Controller controller;
    private UserAccount account;
    private ShopModel model;
    private List<PowerUp> powerUps = new LinkedList<>();
    private List<PowerUpRenderer> pwuInfo = new LinkedList<>();

    @Override
    public void init(Controller controller, Scene scene) {
        this.controller = controller;
        this.account = controller.getUserAccount();
        this.model = new ShopModelImpl(this.controller);
        this.model.loadPwu(powerUps);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModel(final ShopModel model) {
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserAccount getUserAccount() {
        return this.account;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PowerUp> getPwu() {
        return this.powerUps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PowerUpRenderer> getPwuInfo() {
        return this.pwuInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadPwuYaml() {
        try (FileInputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + "PowerUps.yml")) {

            final Constructor constructor = new Constructor(PowerUpImpl.class, new LoaderOptions());
            final TypeDescription accountDescription = new TypeDescription(PowerUpImpl.class);
            accountDescription.addPropertyParameters("id", String.class);
            accountDescription.addPropertyParameters("cost", Integer.class);
            accountDescription.addPropertyParameters("maxLevel", Integer.class);
            accountDescription.addPropertyParameters("statModifiers", Statistic.class, Integer.class);
            constructor.addTypeDescription(accountDescription);

            final Yaml yaml = new Yaml(constructor);
            final Iterable<Object> documents = yaml.loadAll(inputStream);

            for (final Object object : documents) {
                powerUps.add((PowerUp) object);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadPwuInfoYaml() {
        try (FileInputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + "PowerUpsInfo.yml")) {
            final Constructor constructor = new Constructor(PowerUpRendererImpl.class, new LoaderOptions());
            final TypeDescription accountDescription = new TypeDescription(PowerUpRendererImpl.class);
            accountDescription.addPropertyParameters("name", String.class);
            accountDescription.addPropertyParameters("id", String.class);
            accountDescription.addPropertyParameters("description", String.class);
            accountDescription.addPropertyParameters("image", String.class);
            constructor.addTypeDescription(accountDescription);

            final Yaml yaml = new Yaml(constructor);

            final Iterable<Object> documents = yaml.loadAll(inputStream);

            for (final Object object : documents) {
                pwuInfo.add((PowerUpRenderer) object);
            }

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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean buy(final String id) {
        Optional<Integer> changeMoney = this.model.check(id);
        if (!changeMoney.isEmpty()) {
            this.model.updateShop(id, changeMoney.get());
            return true;
        } else {
            return false;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeShop() {
        this.controller.changeScene(ViewType.MAINMENU);
    }

}
