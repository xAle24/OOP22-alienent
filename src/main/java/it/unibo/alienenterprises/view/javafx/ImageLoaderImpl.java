package it.unibo.alienenterprises.view.javafx;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javafx.scene.image.Image;

/**
 * ImageLoader.
 * Has the job to link an identifier whit his sprite file
 */
public class ImageLoaderImpl implements ImageLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageLoaderImpl.class);

    private static final String SPRITE_PATH = "/sprites/";
    private static final String SPRITE_LIST_PATH = SPRITE_PATH + "spriteList.yml";

    private final Set<ImageProp> spriteList = new HashSet<>();
    private final Map<String, Image> imageMap = new HashMap<>();

    /**
     * Creates an instance of ImageLoadeImpl.
     * It reads a file to create the images so is shold be created only one instance
     * for class or object.
     */
    public ImageLoaderImpl() {
        try (InputStream inputStream = getClass().getResourceAsStream(SPRITE_LIST_PATH)) {
            final Constructor constructor = new Constructor(ImageProp.class, new LoaderOptions());
            final Yaml yaml = new Yaml(constructor);
            final var it = yaml.loadAll(inputStream).iterator();
            while (it.hasNext()) {
                this.spriteList.add((ImageProp) it.next());
            }
        } catch (final IOException e) {
            LOGGER.error("Couldn't load Sprite list", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Image> getSpriteImageOf(final String id) {
        final var s = getImageProp(id);
        Image img;
        if (s.isPresent()) {
            if (imageMap.containsKey(id)) {
                img = imageMap.get(id);
            } else {
                img = new Image(SPRITE_PATH + s.get().getFile(),
                        getSpritewidth(id).get() * getSpriteScaleOf(id).get(),
                        getSpriteHeight(id).get() * getSpriteScaleOf(id).get(),
                        true,
                        true);
            }
            return Optional.of(img);
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
