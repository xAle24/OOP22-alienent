package it.unibo.alienenterprises.view.sprites;

import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Sprite class that contains the node that will be drawn on the {@link Canvas}
 */
public final class Sprite {
    private ImageView image;

    public Sprite(Image image, Point2D pos, Vector2D angle) {
        this.image = new ImageView(image);
        this.image.setVisible(true);
        this.image.setFitHeight(50);
        this.image.setFitWidth(50);
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
