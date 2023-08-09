package it.unibo.alienenterprises.controller;

import java.util.Optional;

/**
 * ImageLoader
 * Has the job to link an identifier whit his sprite file
 */
public interface ImageLoader {

    /**
     * @param id the identifier of the GameObject
     * @return an Optional of the path to the sprite relative to the given
     *         identifier if present
     *         otherwise an empty Optional
     */
    Optional<String> getSpriteFilePathOf(String id);

    /**
     * @param id the identifier of the GameObject
     * @return an Optional containing the scale of the sprite or an empty Optional
     *         if the identifier is not present
     */
    Optional<Double> getSpriteScaleOf(String id);

    /**
     * @param id the identifier of the GameObject
     * @return the image height
     */
    Optional<Double> getSpriteHeight(String id);

    /**
     * @param id the identifier of the GameObject
     * @return the image width
     */
    Optional<Double> getSpritewidth(String id);
}
