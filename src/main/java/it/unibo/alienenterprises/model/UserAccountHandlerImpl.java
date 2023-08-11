package it.unibo.alienenterprises.model;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import it.unibo.alienenterprises.Installer;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.model.api.UserAccountHandler;

/**
 * Implementation of UserAccountHandler.
 * 
 * @author Ginevra Bartolini
 */
public class UserAccountHandlerImpl implements UserAccountHandler {

    private static final String SEP = File.separator;
    private static final String YML = ".yml";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountHandlerImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserAccount> login(final String nickname, final String password) {
        if (Installer.doesFileExist(nickname + YML) && correctPassword(nickname, password)) {
            try (FileInputStream inputStream = new FileInputStream(Installer.DIRECTORY_PATH + SEP + nickname + YML)) {

                final Constructor constructor = new Constructor(UserAccountImpl.class, new LoaderOptions());
                final TypeDescription accountDescription = new TypeDescription(UserAccountImpl.class);
                accountDescription.addPropertyParameters("inventory", String.class, Integer.class);
                accountDescription.addPropertyParameters("toAddPwu", Statistic.class, Integer.class);
                constructor.addTypeDescription(accountDescription);

                final Yaml yaml = new Yaml(constructor);
                final UserAccount userAccount = (UserAccountImpl) yaml.load(inputStream);

                return Optional.of(userAccount);

            } catch (IOException e) {
                LOGGER.error("Could not open user file", e);
            }
        }
        return Optional.empty();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserAccount> registration(final String nickname, final String password) {
        if (!Installer.doesFileExist(nickname + YML)) {
            final File accountFile = new File(Installer.DIRECTORY_PATH + SEP + nickname + YML);
            try (FileWriter writer = new FileWriter(Installer.DIRECTORY_PATH + SEP + Installer.PASSWORD_FILE,
                    StandardCharsets.UTF_8,
                    true);) {

                accountFile.createNewFile();

                final DumperOptions option = new DumperOptions();
                option.setExplicitStart(true);

                final Yaml yaml = new Yaml(option);
                final Map<String, String> map = new HashMap<>();
                map.put(nickname, password);
                yaml.dump(map, writer);

                final Optional<UserAccount> account = Optional.of(new UserAccountImpl(nickname));
                save(account.get());
                return account;

            } catch (IOException e) {
                LOGGER.error("Could not create user file", e);
            }
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final UserAccount account) {
        try (FileWriter writer = new FileWriter(Installer.DIRECTORY_PATH + SEP + account.getNickname() + YML,
                StandardCharsets.UTF_8,
                false)) {

            final Representer representer = new Representer(new DumperOptions());
            representer.addClassTag(UserAccountImpl.class, new Tag("!UserAccountImpl"));

            final Yaml yaml = new Yaml(representer);
            yaml.dump(account, writer);
        } catch (IOException e) {
            LOGGER.error("Could not save user file", e);
        }
    }

    private boolean correctPassword(final String nickname, final String password) {
        try (FileInputStream inputStream = new FileInputStream(
                Installer.DIRECTORY_PATH + SEP + Installer.PASSWORD_FILE)) {

            final Map<String, Object> passwordMap = new HashMap<>();

            final Constructor constructor = new Constructor(new LoaderOptions());
            constructor.addTypeDescription(new TypeDescription(Map.class));

            final Yaml yaml = new Yaml(constructor);

            final Iterable<Object> document = yaml.loadAll(inputStream);

            for (final Object obj : document) {
                if (obj instanceof Map) {
                    final Map<?, ?> map = (Map<?, ?>) obj;
                    for (final Map.Entry<?, ?> entry : map.entrySet()) {
                        if (entry.getKey() instanceof String && entry.getValue() instanceof String) {
                            passwordMap.put((String) entry.getKey(), (String) entry.getValue());
                        }
                    }
                }
            }

            return passwordMap.get(nickname).equals(password);

        } catch (IOException e) {
            LOGGER.error("Could not open password file", e);
        }
        return false;
    }

}
