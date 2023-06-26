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

    private boolean existingAccount(String nickname) {
        return Files.exists(Paths.get(GAME_PATH + SEPARATOR + nickname + ".yml"));
    }

    private boolean correctPassword(String nickname, String password) {
        try {
            final Yaml yaml = new Yaml();
            FileInputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + "passwords.yaml");
            Map<String, String> passwordMap = yaml.load(inputStream);
            inputStream.close();

            return passwordMap.get(nickname).equals(password) ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // non dovrebbe giungere a questa riga
    }

    @Override
    public Optional<UserAccountImpl> login(String nickname, String password) {
        if (existingAccount(nickname)) {
            if (correctPassword(nickname, password)) {
                try {
                    Constructor constructor = new Constructor(UserAccountImpl.class, new LoaderOptions());
                    TypeDescription accountDescription = new TypeDescription(UserAccountImpl.class);
                    accountDescription.addPropertyParameters("inventory", String.class, Integer.class);
                    constructor.addTypeDescription(accountDescription);

                    final Yaml yaml = new Yaml(constructor);
                    FileInputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + nickname + ".yml");
                    UserAccountImpl userAccount = (UserAccountImpl) yaml.load(inputStream);

                    inputStream.close();

                    return Optional.of(userAccount);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Couldn't open account yaml file");
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserAccountImpl> registration(String nickname, String password) {
        if (!existingAccount(nickname)) {
            final File accountFile = new File(GAME_PATH + SEPARATOR + nickname + ".yml");
            try {
                accountFile.createNewFile();
                if (!correctPassword(nickname, password)) {
                    FileWriter writer = new FileWriter(GAME_PATH + SEPARATOR + "passwords.yaml", StandardCharsets.UTF_8,
                            true);
                    Representer representer = new Representer(new DumperOptions());
                    representer.addClassTag(Map.class, new Tag("!Password"));
                    Yaml yaml = new Yaml(representer);
                    Map<String, String> map = new HashMap<>();
                    map.put(nickname, password);
                    String output = yaml.dump(map);
                    writer.append(output);
                    writer.close();
                }
                return Optional.of(new UserAccountImpl(nickname));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Couldn't create account yaml file");
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(UserAccountImpl account) {
        final String accountFile = GAME_PATH + SEPARATOR + account.getNickname() + ".yml";
        try (FileWriter writer = new FileWriter(accountFile, StandardCharsets.UTF_8, false)) {
            Representer representer = new Representer(new DumperOptions());
            representer.addClassTag(UserAccountImpl.class, new Tag("!UserAccountImpl"));
            Yaml yaml = new Yaml(representer);
            String output = yaml.dump(account);
            writer.append(output);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
