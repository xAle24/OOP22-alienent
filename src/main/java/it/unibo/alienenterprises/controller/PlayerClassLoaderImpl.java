package it.unibo.alienenterprises.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

import org.yaml.snakeyaml.Yaml;
import it.unibo.alienenterprises.model.api.GameObject;

public class PlayerClassLoaderImpl {

    private static final String SEPARATOR = File.separator;
    private static final String GAME_PATH = "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR
            + "playerclasses";

    public Optional<GameObject> loadStandardPlayer() {
        try (InputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + "standard.yml")){
            Yaml yaml = new Yaml();
            var obj = yaml.loadAs(inputStream,PlayerProp.class);
            System.out.println(obj.getStats().get("HP"));
            GameObject playerClass = null;
            return Optional.ofNullable(playerClass);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
