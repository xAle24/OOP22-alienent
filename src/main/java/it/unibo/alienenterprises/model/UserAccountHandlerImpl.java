package it.unibo.alienenterprises.model;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import it.unibo.alienenterprises.model.api.UserAccountHandler;

/**
 * Implementation of UserAccountHandler.
 */
public class UserAccountHandlerImpl implements UserAccountHandler {

    private static final String SEPARATOR = File.separator;
    private static final String GAME_PATH = "src/main/resources/examplemvc";
    // System.getProperty("user.home") + SEPARATOR + ".Alien Enterprises";
    private static final String YML = ".yml";

    private boolean existingAccount(final String nickname) {
        return Files.exists(Paths.get(GAME_PATH + SEPARATOR + nickname + YML));
    }

    private boolean correctPassword(final String nickname, final String password) {
        try {
            final Yaml yaml = new Yaml();
            final FileInputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + "passwords.yml");
            final Map<String, String> passwordMap = yaml.load(inputStream);
            inputStream.close();

            return passwordMap.get(nickname).equals(password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // non dovrebbe giungere a questa riga
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserAccountImpl> login(final String nickname, final String password) {
        if (existingAccount(nickname) && correctPassword(nickname, password)) {
            try {
                final Constructor constructor = new Constructor(UserAccountImpl.class, new LoaderOptions());
                final TypeDescription accountDescription = new TypeDescription(UserAccountImpl.class);
                accountDescription.addPropertyParameters("inventory", String.class, Integer.class);
                constructor.addTypeDescription(accountDescription);

                final Yaml yaml = new Yaml(constructor);
                final FileInputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + nickname + YML);
                final UserAccountImpl userAccount = (UserAccountImpl) yaml.load(inputStream);

                inputStream.close();

                return Optional.of(userAccount);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserAccountImpl> registration(final String nickname, final String password) {
        if (!existingAccount(nickname)) {
            final File accountFile = new File(GAME_PATH + SEPARATOR + nickname + YML);
            try {
                accountFile.createNewFile();
                final FileWriter writer = new FileWriter(GAME_PATH + SEPARATOR + "passwords.yml",
                        StandardCharsets.UTF_8,
                        true);
                final Representer representer = new Representer(new DumperOptions());
                representer.addClassTag(Map.class, new Tag("!Password"));
                final Yaml yaml = new Yaml(representer);
                final Map<String, String> map = new HashMap<>();
                map.put(nickname, password);
                final String output = yaml.dump(map);
                writer.append(output);
                writer.close();

                return Optional.of(new UserAccountImpl(nickname));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final UserAccountImpl account) {
        final String accountFile = GAME_PATH + SEPARATOR + account.getNickname() + YML;
        try (FileWriter writer = new FileWriter(accountFile, StandardCharsets.UTF_8, false)) {
            final Representer representer = new Representer(new DumperOptions());
            representer.addClassTag(UserAccountImpl.class, new Tag("!UserAccountImpl"));
            final Yaml yaml = new Yaml(representer);
            final String output = yaml.dump(account);
            writer.append(output);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
