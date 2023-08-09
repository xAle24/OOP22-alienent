package it.unibo.alienenterprises.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 * ImageLoader.
 * Has the job to link an identifier whit his sprite file
 */
public class ImageLoaderImpl implements ImageLoader {

    private static final String SEPARATOR = File.separator;
    private static final String SPRITE_PATH = SEPARATOR + "sprites" + SEPARATOR;
    private static final String SPRITE_LIST_PATH = "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR
            + "sprites" + SEPARATOR + "spriteList.yml";

    private Set<ImageProp> spriteList = new HashSet<>();

    /**
     * Creates an instance of ImageLoadeImpl.
     * It reads a file to create the images so is shold be created only one instance
     * for class or object.
     */
    public ImageLoaderImpl() {
        try (InputStream inputStream = new FileInputStream(SPRITE_LIST_PATH)) {
            final Constructor constructor = new Constructor(ImageProp.class, new LoaderOptions());
            final Yaml yaml = new Yaml(constructor);
            final var it = yaml.loadAll(inputStream).iterator();
            while (it.hasNext()) {
                this.spriteList.add((ImageProp) it.next());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> getSpriteFilePathOf(final String id) {
        final var s = getImageProp(id);
        if (s.isPresent()) {
            return Optional.of(SPRITE_PATH + s.get().getFile());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getSpriteScaleOf(final String id) {
        final var s = getImageProp(id);
        if (s.isPresent()) {
            return Optional.of(s.get().getScale());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getSpriteHeight(final String id) {
        final var s = getImageProp(id);
        if (s.isPresent()) {
            return Optional.of(s.get().getHeight());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getSpritewidth(final String id) {
        final var s = getImageProp(id);
        if (s.isPresent()) {
            return Optional.of(s.get().getWidth());
        }
        return Optional.empty();
    }

    private Optional<ImageProp> getImageProp(final String id) {
        return this.spriteList.stream().filter((i) -> i.getId().equals(id)).findFirst();
    }

}
