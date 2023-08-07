package it.unibo.alienenterprises.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import org.yaml.snakeyaml.Yaml;

public class ImageLoaderImpl implements ImageLoader {

    private static final String SEPARATOR = File.separator;
    private static final String SPRITE_PATH = "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR
            + "sprites" + SEPARATOR;
    private static final String SPRITE_LIST_PATH = SPRITE_PATH + "spriteList.yml";

    private Map<String, String> spriteMap;

    public ImageLoaderImpl() {
        try (final InputStream inputStream = new FileInputStream(SPRITE_LIST_PATH)) {
            final Yaml yaml = new Yaml();
            this.spriteMap = yaml.load(inputStream);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public Optional<File> getSpriteFileOf(final String id) {
        final var s = Optional.ofNullable(spriteMap.get(id));
        if (s.isPresent()) {
            return Optional.of(new File(SPRITE_PATH + s.get()));
        }
        return Optional.empty();
    }

}
