package it.unibo.alienenterprises.view.sprites;

import it.unibo.alienenterprises.controller.ImageLoader;
import it.unibo.alienenterprises.controller.ImageLoaderImpl;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

/**
 * Sprite class that contains the node that will be drawn on the {@link Canvas}
 */
public final class Sprite {
    private ImageView image;

    public Sprite(String objID, Point2D pos, Vector2D angle) {
        ImageLoader loader = new ImageLoaderImpl();
        this.image = new ImageView(loader.getSpriteFilePathOf(objID).get());
        this.image.setFitHeight(50);
        this.image.setFitWidth(50);
        this.image.setVisible(true);
        this.render(pos, angle);
    }

    public void render(Point2D pos, Vector2D angle) {
        this.image.setX(pos.getX());
        this.image.setY(pos.getY());
        this.image.setRotate(angle.getAngle());
    }

    public ImageView getImageView() {
        return this.image;
    }
}
