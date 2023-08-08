package it.unibo.alienenterprises.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.view.api.PlayerClassMenu;
import it.unibo.alienenterprises.view.controllers.PlayerController;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * PlayerClassMenu
 */
public class PlayerClassMenuImpl extends BorderPane implements PlayerClassMenu {

    private static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double SCREEN_WIDTH = SCREEN_DIMENSION.getWidth();
    private static final double SCREEN_HEIGHT = SCREEN_DIMENSION.getHeight();

    private static final String TITLE_TEXT = "Choose your class";
    private static final int NUM_BUTTONS_RAW = 3;
    private static final String BOX = "box";
    private static final String SELECTION_BUTTON = "button_selection";
    private static final String TITLE = "title";
    private static final String EXIT = "exit_button";
    private static final String BUTTONS_GRID = "buttonsGrid";

    private final PlayerController controller;

    private final VBox left = new VBox();
    private final ScrollPane center = new ScrollPane();
    private final BorderPane bottom = new BorderPane();

    private final List<Button> buttons = new ArrayList<>();

    /**
     * @param controller
     * @param sceneLoader
     */
    public PlayerClassMenuImpl(final PlayerController controller) {
        super();
        this.getStylesheets().add(getClass().getResource(
                "/css/ShipSelectMenu.css")
                .toExternalForm());
        this.setId(BOX);
        this.controller = controller;
        this.setMaxHeight(SCREEN_HEIGHT);
        this.setMaxWidth(SCREEN_WIDTH);

        final BorderPane up = new BorderPane();
        up.setMaxHeight(SCREEN_HEIGHT/20);
        up.setId(TITLE);
        this.setTop(up);
        final var title = new Text(TITLE_TEXT);
        title.setStyle("-fx-font-size: " + SCREEN_HEIGHT/30);
        up.setCenter(title);

        final Button exitButton = new Button();
        exitButton.setOnAction((e)->{
            controller.exit();
        });
        exitButton.setId(EXIT);
        exitButton.setMinWidth(50);
        exitButton.setMinHeight(50);
        up.setRight(exitButton);

        final BorderPane container = new BorderPane();
        container.setLeft(this.left);

        this.setCentralPane();
        buttons.get(0).fire();

        this.center.setMaxHeight(SCREEN_HEIGHT/1.5);
        this.center.setMaxWidth(SCREEN_WIDTH/2);
        container.setCenter(this.center);

        container.setBottom(this.bottom);

        this.setCenter(container);
        final Button confirmButton = new Button("Conferma");
        confirmButton.setOnAction((e) -> {
            controller.confirmSelection();
        });
        this.setBottom(confirmButton);
    }

    @Override
    public PlayerController getController() {
        return controller;
    }

    private void setCentralPane() {
        final List<String> list = controller.getPlayerIds().stream().sorted().toList();
        final GridPane grid = new GridPane();
        grid.setPadding(new Insets(2));
        int j;
        String id;
        for (int i = 0; i < list.size() / (double) NUM_BUTTONS_RAW; i++) {
            j = i * NUM_BUTTONS_RAW;
            for (int t = j; t < list.size() && t < j + NUM_BUTTONS_RAW; t++) {
                id = list.get(t);
                final Button button = new Button();
                button.getStyleClass().add(SELECTION_BUTTON);
                button.setMinWidth(125);
                button.setMinHeight(125);
                
                final var img = new ImageView(controller.getSpriteFile(id).get());// TODO
                img.setFitWidth(75);
                img.setFitHeight(75);
                final var graphic = new BorderPane();
                graphic.setTop(img);
                BorderPane.setAlignment(graphic.getTop(), Pos.CENTER);
                
                final Text text = new Text(controller.getName(id).get());//TODO
                text.setWrappingWidth(100);
                text.setTextAlignment(TextAlignment.CENTER);
                graphic.setCenter(text);

                button.setGraphic(graphic);
                setAction(button, id);
                this.buttons.add(button);
                grid.add(button, t - j, i);
            }
        }
        this.center.setContent(grid);
    }

    private void setAction(final Button button, final String id) {
        button.setOnAction((event) -> {
            buttons.forEach((b) -> b.setDisable(false));
            button.setDisable(true);
            showStats(controller.getStats(id).get());
            ImageView img = new ImageView(controller.getSpriteFile(id).get());// TODO
            img.setFitHeight(150);
            img.setFitWidth(150);
            bottom.setLeft(img);

            bottom.setCenter(new Text(controller.getDescription(id).get()));

            controller.select(id);
        });
    }

    private void showStats(final Map<Statistic, Integer> stats) {
        final GridPane pane = new GridPane();
        pane.getColumnConstraints().add(new ColumnConstraints(120));
        pane.getColumnConstraints().add(new ColumnConstraints(50));
        int t = 0;
        for (final var s : Statistic.values()) {
            pane.add(new Text(s.toString()), 0, t);
            pane.add(new Text(stats.get(s).toString()), 1, t);
            t++;
        }
        left.getChildren().clear();
        left.getChildren().add(pane);
    }

}
