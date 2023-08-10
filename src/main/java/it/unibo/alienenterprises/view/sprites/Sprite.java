package it.unibo.alienenterprises.view.sprites;

import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.view.javafx.ImageLoader;
import it.unibo.alienenterprises.view.javafx.ImageLoaderImpl;
import javafx.scene.image.ImageView;

/**
 * Sprite class that contains the node that will be drawn on the {@link Canvas}.
 * 
 * @author Giulia Bonifazi
 */
public class Sprite {
    /**
     * Image loader for sprite class.
     */
    public static final ImageLoader IMG_LOADER = new ImageLoaderImpl();
    private ImageView image;

    /**
     * Creates a new instance of this class.
     * 
     * @param objID the object id
     * @param pos   the object position
     * @param vect  the object vector
     */
    public Sprite(final String objID, final Point2D pos, final Vector2D vect) {
        this.image = new ImageView(IMG_LOADER.getSpriteImageOf(objID).get());
        this.image.setVisible(true);
        this.render(pos, vect);
    }

    /**
     * {@inheritDoc}
     * Renders the Sprite at specified position and angle.
     * 
     * @param pos  the position
     * @param vect the vector
     */
    public void render(final Point2D pos, final Vector2D vect) {
        this.image.setX(pos.getX() - this.image.getImage().getRequestedWidth() / 2);
        this.image.setY(pos.getY() - this.image.getImage().getRequestedHeight() / 2);
        this.image.setRotate(vect.getAngle());
    }

    /**
     * {@inheritDoc}
     * Returns the imageView
     * 
     * @return the image view.
     */
    public ImageView getImageView() {
        return this.image;
    }
}
