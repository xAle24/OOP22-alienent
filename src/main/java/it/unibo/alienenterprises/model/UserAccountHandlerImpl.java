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

import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.model.api.UserAccountHandler;

/**
 * Implementation of UserAccountHandler.
 */
public class UserAccountHandlerImpl implements UserAccountHandler {

    private static final String SEPARATOR = File.separator;
    private static final String GAME_PATH = "src/main/resources/examplemvc";
    // System.getProperty("user.home") + SEPARATOR + ".Alien Enterprises";
    private static final String YML = ".yml";

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserAccount> login(final String nickname, final String password) {
        if (existingAccount(nickname) && correctPassword(nickname, password)) {
            try (FileInputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + nickname + YML)) {
                final Constructor constructor = new Constructor(UserAccountImpl.class, new LoaderOptions());
                final TypeDescription accountDescription = new TypeDescription(UserAccountImpl.class);
                accountDescription.addPropertyParameters("inventory", String.class, Integer.class);
                constructor.addTypeDescription(accountDescription);

                final Yaml yaml = new Yaml(constructor);
                final UserAccount userAccount = (UserAccountImpl) yaml.load(inputStream);

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
    public Optional<UserAccount> registration(final String nickname, final String password) {
        if (!existingAccount(nickname)) {
            final File accountFile = new File(GAME_PATH + SEPARATOR + nickname + YML);
            try (FileWriter writer = new FileWriter(GAME_PATH + SEPARATOR + "passwords.yml",
                    StandardCharsets.UTF_8,
                    true);) {
                accountFile.createNewFile();

                final DumperOptions option = new DumperOptions();
                option.setExplicitStart(true);
                /*
                 * final Representer representer = new Representer(option);
                 * representer.addClassTag(Map.class, new Tag("!Password"));
                 */
                final Yaml yaml = new Yaml(option);
                final Map<String, String> map = new HashMap<>();
                map.put(nickname, password);
                final String output = yaml.dump(map);
                writer.append(output);

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
    public void save(final UserAccount account) {
        final String accountFile = GAME_PATH + SEPARATOR + account.getNickname() + YML;
        try (FileWriter writer = new FileWriter(accountFile, StandardCharsets.UTF_8, false)) {
            final Representer representer = new Representer(new DumperOptions());
            representer.addClassTag(UserAccountImpl.class, new Tag("!UserAccountImpl"));
            final Yaml yaml = new Yaml(representer);
            final String output = yaml.dump(account);
            System.out.println(output);
            writer.append(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean existingAccount(final String nickname) {
        return Files.exists(Paths.get(GAME_PATH + SEPARATOR + nickname + YML));
    }

    private boolean correctPassword(final String nickname, final String password) {
        try (final FileInputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + "passwords.yml")) {
            final Yaml yaml = new Yaml();
            final Iterable<Object> document = yaml.loadAll(inputStream);
            Map<String, String> passwordMap = new HashMap<>();

            for (Object obj : document) {
                passwordMap.putAll((Map<? extends String, ? extends String>) obj);
            }

            return passwordMap.get(nickname).equals(password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // non dovrebbe giungere a questa riga
    }

}
