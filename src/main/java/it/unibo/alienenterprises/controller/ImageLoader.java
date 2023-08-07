package it.unibo.alienenterprises.controller;

import java.io.File;
import java.util.Optional;

/**
 * ImageLoader
 * Has the job to link an identifier whit his sprite file
 */
public interface ImageLoader {

    /**
     * @param id the identifier of the GameObject
     * @return an Optional of the sprite relative to the given identifier if present
     *         otherwise an empty Optional
     */
    Optional<File> getSpriteFileOf(String id);
}
