package it.unibo.alienenterprises.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.yaml.snakeyaml.Yaml;

import it.unibo.alienenterprises.controller.api.ShipLoader;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.GameObjectAbs;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * ShipLoaderImpl
 */
public class ShipLoaderImpl implements ShipLoader {

    private static final String SEPARATOR = File.separator;
    private static final String GAME_PATH = "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR + "ships";
    private static final String SHIPLIST_FILE = "shipList";
    private static final String PLAYER_FOLDER = "playerclasses";
    private static final String ENEMY_FOLDER = "enemyclasses";
    private static final String COMPONENT_PAKAGE = "it.unibo.alienenterprises.model.impl.components.";
    private static final String YAML = ".yml";
    private static final String TYPE = "type";
    private static final String VALUE = "value";
    private static final String DELIMITER_EX = "\\.";
    private static final String DELIMITER = ".";

    // TODO Da eliminare
    private class Go extends GameObjectAbs {

        public Go(Point2D pos, Vector2D vector, Map<Statistic, Integer> stat, Component... components) {
            super(pos, vector, stat, components);
        }

        @Override
        public boolean isAlive() {
            return false;
        }

    }

    private List<String> playerList;
    private List<String> enemyList;

    private enum ParameterTypes {
        CLASS,
        METHOD,
        INT,
        DOUBLE,
        BOOLEAN,
        STRING;
    }

    public ShipLoaderImpl() {
        try (final InputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + SHIPLIST_FILE + YAML)) {
            final Yaml yaml = new Yaml();
            final Map<String, List<String>> map = yaml.load(inputStream);
            if (map.get(PLAYER_FOLDER) != null) {
                playerList = map.get(PLAYER_FOLDER);
            } else {
                playerList = List.of();
            }
            if (map.get(ENEMY_FOLDER) != null) {
                enemyList = map.get(ENEMY_FOLDER);
            } else {
                enemyList = List.of();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, GameObject> loadPlayerClasses() {
        final var path = GAME_PATH + SEPARATOR + PLAYER_FOLDER + SEPARATOR;
        final Map<String, GameObject> playerMap = new HashMap<>();
        for (var name : playerList) {
            var obj = loadShip(path + name + YAML);
            if (obj.isPresent()) {
                playerMap.put(name, obj.get());
            }
        }
        return playerMap;
    }

    @Override
    public Map<String, GameObject> loadEnemyClasses() {
        final var path = GAME_PATH + SEPARATOR + ENEMY_FOLDER + SEPARATOR;
        final Map<String, GameObject> enemyMap = new HashMap<>();
        for (var name : enemyList) {
            var obj = loadShip(path + name + YAML);
            if (obj.isPresent()) {
                enemyMap.put(name, obj.get());
            }
        }
        return enemyMap;
    }

    /**
     * {@inhritdoc}
     * To work needs that all the Components specified by the file have one and only
     * one Constructor, the Constructor have to require at least a GameObject as a
     * parameter.
     * 
     * @param shipFileName
     * @return
     */
    @Override
    public Optional<GameObject> loadShip(final String shipFileName) {
        try (final InputStream inputStream = new FileInputStream(shipFileName)) {
            final Yaml yaml = new Yaml();
            final ShipProp obj = yaml.loadAs(inputStream, ShipProp.class);
            final Map<Statistic, Integer> stats = new HashMap<>();
            for (var s : obj.getStats().keySet()) {
                stats.put(Statistic.valueOf(s), obj.getStats().get(s));
            }
            GameObject temp = new Go(Point2D.ORIGIN, Vector2D.NULL_VECTOR, stats);
            // TODO Da modificare con l'addAllComponents
            fetchComponents(obj.getComponents(), temp).stream().forEach((c) -> temp.addComponent(c));
            return Optional.of(temp);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private List<Component> fetchComponents(final Map<String, List<Map<String, String>>> componentMap,
            final GameObject obj) {
        final List<Component> out = new ArrayList<>();
        for (var name : componentMap.keySet()) {
            final var parameters = componentMap.get(name);
            final var fetchedParameters = parameters.stream()
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
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return out;
    }

    private Optional<Object> fetchParameter(final Map<String, String> parameter) {
        final ParameterTypes type = ParameterTypes.valueOf(parameter.get(TYPE));
        switch (type) {
            case CLASS:
                try {
                    Class<?> parameterClass = Class.forName(parameter.get(VALUE));
                    Object obj = parameterClass.getConstructor().newInstance();
                    return Optional.ofNullable(obj);
                } catch (final Exception e) {
                    // TODO
                    e.printStackTrace();
                }
                break;
            case DOUBLE:
                return Optional.ofNullable(Double.parseDouble(parameter.get(VALUE)));
            case INT:
                return Optional.ofNullable(Integer.parseInt(parameter.get(VALUE)));
            case METHOD:
                String[] root = parameter.get(VALUE).split(DELIMITER_EX);
                String methodClassName = List.of(root).stream()
                        .limit(root.length - 1)
                        .reduce((s1, s2) -> s1 + DELIMITER + s2)
                        .get();
                String methodName = root[root.length - 1];
                try {
                    Class<?> methodClass = Class.forName(methodClassName);
                    Method method = methodClass.getMethod(methodName);
                    Object obj = method.invoke(methodClass.getConstructor().newInstance());
                    return Optional.ofNullable(obj);
                } catch (final Exception e) {
                    // TODO
                    e.printStackTrace();
                }
                break;
            case STRING:
                return Optional.ofNullable(parameter.get(VALUE));
            case BOOLEAN:
                return Optional.ofNullable(Boolean.parseBoolean(parameter.get(VALUE)));
            default:
                break;
        }
        return Optional.empty();
    }
}
