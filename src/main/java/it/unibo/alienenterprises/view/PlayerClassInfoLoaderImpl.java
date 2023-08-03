package it.unibo.alienenterprises.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.yaml.snakeyaml.Yaml;

//TODO Da sistemare
public class PlayerClassInfoLoaderImpl implements PlayerClassInfoLoader {
    private static final String SEPARATOR = File.separator;
    private static final String GAME_RESOURCES_PATH = "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR
            + "ships";
    private static final String DESCRIPTIONS_PATH = GAME_RESOURCES_PATH + SEPARATOR + "playerInfo";
    private static final String SHIP_LIST_FILE_PATH = GAME_RESOURCES_PATH + SEPARATOR + "shipList.yml";
    private static final String FILE_SUFFIX = "Info.yml";
    private static final String PLAYERS = "playerClasses";

    private List<String> playerList;
    private final Map<String, PlayerClassInfo> infoMap = new HashMap<>();

    public PlayerClassInfoLoaderImpl() {
        try (final InputStream inputStream = new FileInputStream(SHIP_LIST_FILE_PATH)) {
            final Yaml yaml = new Yaml();
            final Map<String, List<String>> map = yaml.load(inputStream);
            this.playerList = map.get(PLAYERS);
        } catch (final Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public PlayerClassInfoLoaderImpl(final List<String> list) {
        this.playerList = list;
    }

    @Override
    public void load() {
        for (final var name : this.playerList) {
            try (final InputStream inputStream = new FileInputStream(
                    DESCRIPTIONS_PATH + SEPARATOR + name + FILE_SUFFIX)) {
                final Yaml yaml = new Yaml();
                final PlayerClassInfo pInfo = yaml.loadAs(inputStream, PlayerClassInfoImpl.class);
                this.infoMap.put(name, pInfo);
            } catch (final Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<PlayerClassInfo> getPlayerClassInfo(final String name) {
        return Optional.ofNullable(infoMap.get(name));
    }

    @Override
    public Map<String, PlayerClassInfo> getPlayerClassInfos() {
        return this.infoMap;
    }

}
