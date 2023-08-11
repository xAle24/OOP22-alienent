package it.unibo.alienenterprises.view.javafx;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

import it.unibo.alienenterprises.view.ShipInfoLoader;
import javafx.scene.image.Image;

/**
 * PlayerInfoLoaderImpl.
 * Implementation of ShipInfoLoader that is used to load the information of the
 * players
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class PlayerInfoLoaderImpl implements ShipInfoLoader {
    private static final String GAME_RESOURCES_PATH = "/ships/";
    private static final String DESCRIPTIONS_PATH = GAME_RESOURCES_PATH + "playerInfo/";
    private static final String SHIP_LIST_FILE_PATH = GAME_RESOURCES_PATH + "shipList.yml";
    private static final String FILE_SUFFIX = "Info.yml";
    private static final String PLAYERS = "playerclasses";
    private static final ImageLoader IMAGE_LOADER = new ImageLoaderImpl();

    private boolean isLoaded;
    private Set<String> playerIds;
    private final Map<String, PlayerClassInfo> infoMap = new HashMap<>();

    /**
     * Create a new PlayerInfoLoaderImpl taking the id set from the playerclasses
     * section of the file shipList.
     */
    @SuppressWarnings("PMD.EmptyCatchBlock")
    public PlayerInfoLoaderImpl() {
        try (InputStream inputStream = getClass().getResourceAsStream(SHIP_LIST_FILE_PATH)) {
            final Yaml yaml = new Yaml();
            final Map<String, List<String>> map = yaml.load(inputStream);
            this.playerIds = Set.copyOf(map.get(PLAYERS));
        } catch (final IOException e) {
        }
    }

    /**
     * Create a new PlayerInfoLoaderImpl of the ships contained in the IdSet.
     * 
     * @param idSet Identifiers of the ships
     */
    public PlayerInfoLoaderImpl(final Set<String> idSet) {
        this.playerIds = Set.copyOf(idSet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("PMD.EmptyCatchBlock")
    public void load() {
        if (!this.isLoaded) {
            for (final var name : this.playerIds) {
                try (InputStream inputStream = getClass().getResourceAsStream(DESCRIPTIONS_PATH + name + FILE_SUFFIX)) {
                    final Yaml yaml = new Yaml();
                    final PlayerClassInfo pInfo = yaml.loadAs(inputStream, PlayerClassInfoImpl.class);
                    this.infoMap.put(name, pInfo);
                } catch (final IOException e) {
                }
            }
            this.isLoaded = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getShipIds() {
        return Set.copyOf(playerIds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> getShipName(final String id) {
        this.checkIfLoaded();
        if (infoMap.containsKey(id)) {
            return Optional.of(infoMap.get(id).getName());
        } else {
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> getShipDescription(final String id) {
        this.checkIfLoaded();
        if (infoMap.containsKey(id)) {
            return Optional.of(infoMap.get(id).getDescription());
        } else {
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Image> getShipImage(final String id) {
        this.checkIfLoaded();
        if (infoMap.containsKey(id)) {
            return IMAGE_LOADER.getSpriteImageOf(id);
        } else {
            return Optional.empty();
        }
    }

    private void checkIfLoaded() {
        if (!isLoaded) {
            throw new IllegalStateException("The data has not been loaded");
        }
    }
}
