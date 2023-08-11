package it.unibo.alienenterprises.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import it.unibo.alienenterprises.controller.api.ShopController;
import it.unibo.alienenterprises.model.PowerUpImpl;
import it.unibo.alienenterprises.model.PowerUpRendererImpl;
import it.unibo.alienenterprises.model.ShopModelImpl;
import it.unibo.alienenterprises.model.UserAccountHandlerImpl;
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

    private static final String YML = ".yml";
    private static final String PWU = "/PowerUps";
    private static final String PWUREN = "/PowerUpsInfo";
    private static final String DIRYML = "/yaml";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountHandlerImpl.class);

    private Controller controller;
    private UserAccount account;
    private ShopModel model;
    private final List<PowerUp> powerUps = new LinkedList<>();
    private final List<PowerUpRenderer> pwuInfo = new LinkedList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final Controller controller, final Scene scene) {
        this.controller = controller;
        this.account = controller.getUserAccount();
        this.model = new ShopModelImpl(this.controller);
        this.model.loadPwu(powerUps);
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
        try (InputStream inputStream = getClass().getResourceAsStream(DIRYML + PWU + YML)) {

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

        } catch (IOException e) {
            LOGGER.error("Could not open power ups file", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadPwuInfoYaml() {
        try (InputStream inputStream = getClass().getResourceAsStream(DIRYML + PWUREN + YML)) {
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

        } catch (IOException e) {
            LOGGER.error("Could not open power up renderers file", e);
        }

        final Iterator<PowerUpRenderer> iterator = pwuInfo.iterator();
        while (iterator.hasNext()) {
            final PowerUpRenderer curr = iterator.next();
            final List<PowerUp> pwu = powerUps.stream().filter(p -> p.getId().equals(curr.getId())).toList();
            curr.setPwu(pwu.get(0));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean buy(final String id) {
        final Optional<Integer> changeMoney = this.model.check(id);
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
