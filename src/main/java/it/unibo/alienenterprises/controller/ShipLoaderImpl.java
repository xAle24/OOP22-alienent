package it.unibo.alienenterprises.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import it.unibo.alienenterprises.controller.api.ShipLoader;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.GameObjectAbs;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * ShipLoaderImpl.
 * Implementation of ShipLoader
 */
public class ShipLoaderImpl implements ShipLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipLoaderImpl.class);

    private static final String GAME_PATH = "/ships";
    private static final String SHIPLIST_FILE = GAME_PATH + "/shipList.yml";
    private static final String PLAYER_IDS = "playerclasses";
    private static final String PLAYER_FOLDER = "/" + PLAYER_IDS + "/";
    private static final String ENEMY_IDS = "enemyclasses";
    private static final String ENEMY_FOLDER = "/" + ENEMY_IDS + "/";

    private static final String COMPONENT_PAKAGE = "it.unibo.alienenterprises.model.impl.components.";
    private static final String YAML = ".yml";

    private static final String TYPE = "type";
    private static final String VALUE = "value";
    private static final String CALLING_CLASS = "callingClass";

    private List<String> playerList;
    private List<String> enemyList;

    private final List<Object> factories;

    private enum ParameterTypes {
        CLASS,
        METHOD,
        FACTORYMETHOD,
        INT,
        DOUBLE,
        BOOLEAN,
        STRING,
        HITBOXTYPE;
    }

    /**
     * Set Up a ShipLoaderInfo.
     * 
     * @param factories the factories neded to load the ships that are called by the
     *                  Parameter FACTORYMETHOD
     */
    public ShipLoaderImpl(final Object... factories) {
        try (InputStream inputStream = getClass().getResourceAsStream(SHIPLIST_FILE)) {
            final Yaml yaml = new Yaml();
            final Map<String, List<String>> map = yaml.load(inputStream);
            if (map.get(PLAYER_IDS) != null) {
                playerList = map.get(PLAYER_IDS);
            } else {
                playerList = List.of();
            }
            if (map.get(ENEMY_IDS) != null) {
                enemyList = map.get(ENEMY_IDS);
            } else {
                enemyList = List.of();
            }
        } catch (final IOException e) {
            LOGGER.error("Error on the loading of the ids of the ships", e);
        }
        this.factories = List.of(factories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, GameObject> loadPlayerClasses() {
        final var path = GAME_PATH + PLAYER_FOLDER;
        final Map<String, GameObject> playerMap = new HashMap<>();
        for (final var id : playerList) {
            final var obj = loadShip(path, id);
            if (obj.isPresent()) {
                playerMap.put(id, obj.get());
            }
        }
        return playerMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getPlayerIds() {
        return Set.copyOf(playerList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, GameObject> loadEnemyClasses() {
        final var path = GAME_PATH + ENEMY_FOLDER;
        final Map<String, GameObject> enemyMap = new HashMap<>();
        for (final var id : enemyList) {
            final var obj = loadShip(path, id);
            if (obj.isPresent()) {
                enemyMap.put(id, obj.get());
            }
        }
        return enemyMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getEnemyIds() {
        return Set.copyOf(enemyList);
    }

    /**
     * {@inheritDoc}
     * To work needs that all the Components specified by the file have one and only
     * one Constructor, the Constructor have to require at least a GameObject as a
     * parameter.
     * 
     * @param folder the folder in which there is the file
     * @param id     the id of the ship
     * @return An Optional of the ship or an empty Optional if there was any problem
     *         in the loading
     */
    @Override
    public Optional<GameObject> loadShip(final String folder, final String id) {
        try (InputStream inputStream = getClass().getResourceAsStream(folder + id + YAML)) {
            final Yaml yaml = new Yaml();
            final ShipProp obj = yaml.loadAs(inputStream, ShipProp.class);
            final Map<Statistic, Integer> stats = new HashMap<>();
            for (final var s : obj.getStats().keySet()) {
                stats.put(Statistic.valueOf(s), obj.getStats().get(s));
            }
            final GameObject temp = new GameObjectAbs(Point2D.ORIGIN, Vector2D.NULL_VECTOR, stats, id);
            temp.addAllComponent(fetchComponents(obj.getComponents(), temp));
            temp.getAllComponent().stream().forEach((c) -> c.start());
            return Optional.of(temp);
        } catch (final IOException e) {
            LOGGER.error("Error couldn't load the given ship: " + folder + id + YAML, e);
        }
        return Optional.empty();
    }

    private List<Component> fetchComponents(final Map<String, List<Map<String, String>>> componentMap,
            final GameObject obj) {
        final List<Component> out = new ArrayList<>();
        for (final var name : componentMap.keySet()) {
            final var parameters = componentMap.get(name);
            final List<Object> fetchedParameters = parameters == null
                    ? List.of()
                    : parameters.stream()
                            .map((p) -> fetchParameter(p))
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .toList();
            final var constructorParameters = new ArrayList<>(fetchedParameters.size() + 1);
            constructorParameters.add(obj);
            constructorParameters.addAll(fetchedParameters);
            try {
                final Class<?> componentClass = Class.forName(COMPONENT_PAKAGE + name);
                final Constructor<?>[] c = componentClass.getConstructors();
                if (c.length != 1) {
                    throw new IllegalArgumentException("Incompatible Component: " + name);
                }
                final Component component = (Component) c[0].newInstance(constructorParameters.toArray());
                out.add(component);
            } catch (final SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException e) {
                LOGGER.error("Error could load the Component: " + name, e);
            }
        }
        return out;
    }

    private Optional<Object> fetchParameter(final Map<String, String> parameter) {
        final ParameterTypes type = ParameterTypes.valueOf(parameter.get(TYPE));
        switch (type) {
            case CLASS:
                try {
                    final Class<?> parameterClass = Class.forName(parameter.get(VALUE));
                    final Object obj = parameterClass.getConstructor().newInstance();
                    return Optional.ofNullable(obj);
                } catch (final SecurityException | ClassNotFoundException | InstantiationException
                        | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                        | NoSuchMethodException e) {
                    LOGGER.error("Couldn't construct CLASS Component parameter: " + parameter.get(VALUE), e);
                }
                break;
            case DOUBLE:
                return Optional.ofNullable(Double.parseDouble(parameter.get(VALUE)));
            case INT:
                return Optional.ofNullable(Integer.parseInt(parameter.get(VALUE)));
            case METHOD:
                try {
                    final Class<?> methodClass = Class.forName(parameter.get(CALLING_CLASS));
                    final Method method = methodClass.getMethod(parameter.get(VALUE));
                    final Object obj = method.invoke(methodClass.getConstructor().newInstance());
                    return Optional.ofNullable(obj);
                } catch (final ClassNotFoundException | SecurityException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException | InstantiationException
                        | NoSuchMethodException e) {
                    LOGGER.error("Couldn't construct METHOD Component parameter: " + parameter.get(CALLING_CLASS)
                            + parameter.get(VALUE), e);
                }
                break;
            case FACTORYMETHOD:
                try {
                    final Class<?> factoryClass = Class.forName(parameter.get(CALLING_CLASS));
                    final Optional<Object> factory = factories.stream()
                            .filter((p) -> p.getClass().equals(factoryClass))
                            .findFirst();
                    if (factory.isPresent()) {
                        final Method factoryMethod = factoryClass.getMethod(parameter.get(VALUE));
                        return Optional.of(factoryMethod.invoke(factory.get()));
                    } else {
                        throw new IllegalArgumentException();
                    }
                } catch (final ClassNotFoundException | NoSuchMethodException | SecurityException
                        | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    LOGGER.error("Couldn't construct FACTORYMETHOD Component parameter: " + parameter.get(CALLING_CLASS)
                            + parameter.get(VALUE), e);
                }
                break;
            case STRING:
                return Optional.ofNullable(parameter.get(VALUE));
            case BOOLEAN:
                return Optional.ofNullable(Boolean.parseBoolean(parameter.get(VALUE)));
            case HITBOXTYPE:
                return Optional.of(HitboxComponent.Type.valueOf(parameter.get(VALUE)));
            default:
                break;
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Map<Statistic, Integer>> loadStatsOf(final String resource) {
        try (InputStream inputStream = getClass().getResourceAsStream(resource)) {
            final Yaml yaml = new Yaml();
            final ShipProp obj = yaml.loadAs(inputStream, ShipProp.class);
            final Map<Statistic, Integer> stats = new HashMap<>();
            for (final var s : obj.getStats().keySet()) {
                stats.put(Statistic.valueOf(s), obj.getStats().get(s));
            }
            return Optional.of(stats);
        } catch (final IOException e) {
            LOGGER.error("Couldn't load requested stats:" + resource, e);
        }
        return Optional.empty();
    }
}
