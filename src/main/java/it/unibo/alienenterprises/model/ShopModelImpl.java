package it.unibo.alienenterprises.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
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

    private final Set<PowerUp> powerUps = new HashSet<>();

    /**
     * Costructor.
     */
    public ShopModelImpl() {
        loadPwu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> check(final String id, final UserAccountImpl user) {
        final Iterator<PowerUp> pwuIterator = powerUps.iterator();
        while (pwuIterator.hasNext()) {
            final PowerUp currPwu = pwuIterator.next();
            if (currPwu.getId().equals(id)) {
                return user.getMoney() - currPwu.getCost() > 0 ? Optional.of(-currPwu.getCost())
                        : Optional.empty();
            }
            // se returna i soldi al negativo io posso toglierli con updateMoney
            // non fai il controllo se si può comprare perchè il bottone si disattiva
            // automaticamente
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateShop(final String id, final UserAccountImpl user, final int changeMoney) {
        user.updateInventory(id);
        user.setMoney(changeMoney);
        user.equals(updateToAddPwu(id, user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadPwu() {
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

    private UserAccountImpl updateToAddPwu(final String id, final UserAccountImpl user) {
        final Iterator<PowerUp> iterator = powerUps.iterator();
        while (iterator.hasNext()) {
            final PowerUp curr = iterator.next();
            if (curr.getId().equals(id)) {
                final Map<Statistic, Integer> map = curr.getStatModifiers();
                user.updateToAddPwu(map);
            }
        }
        return user;
    }
}