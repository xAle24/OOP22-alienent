package it.unibo.alienenterprises.view.sprites;

import it.unibo.alienenterprises.controller.ImageLoader;
import it.unibo.alienenterprises.controller.ImageLoaderImpl;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Sprite class that contains the node that will be drawn on the {@link Canvas}
 */
public final class Sprite {
    public static final ImageLoader IMG_LOADER = new ImageLoaderImpl();
    private ImageView image;

    public Sprite(String objID, Point2D pos, Vector2D angle) {
        var scale = IMG_LOADER.getSpriteScaleOf(objID).get();
        this.image = new ImageView(new Image(IMG_LOADER.getSpriteFilePathOf(objID).get(),
                IMG_LOADER.getSpritewidth(objID).get() * scale,
                IMG_LOADER.getSpriteHeight(objID).get() * scale,
                true,
                true));
        this.image.setVisible(true);
        this.render(pos, angle);
    }

    public void render(Point2D pos, Vector2D angle) {
        this.image.setX(pos.getX() - this.image.getImage().getRequestedWidth() / 2);
        this.image.setY(pos.getY() - this.image.getImage().getRequestedHeight() / 2);
        this.image.setRotate(angle.getAngle());
    }

    public ImageView getImageView() {
        return this.image;
    }
}
