package it.unibo.alienenterprises.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.view.api.PlayerClassMenu;
import it.unibo.alienenterprises.view.controllers.PlayerController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * PlayerClassMenu.
 */
public class PlayerClassMenuImpl extends BorderPane implements PlayerClassMenu {

    private static final int PREF_WIDTH = 867;
    private static final int PREF_HEIGHT = 671;
    private static final int PROP_FONT = 20;
    private static final int PROP_HEIGHT = 20;
    private static final int DESCRIPTION_IMAGE_SIZE = 150;
    private static final int WRAPPING_TEXT_WIDTH = 100;
    private static final int SELECTION_IMAGE_SIZE = 75;
    private static final int SELECTION_BUTTON_SIZE = 125;
    private static final int EXIT_BUTTON_SIZE = 50;
    private static final String CONFIRM_BUTTON_TEXT = "Confirm Selection";
    private static final String FX_FONT_SIZE = "-fx-font-size: ";

    private static final String CSS_FILE = "/css/ShipSelectMenu.css";

    private static final String TITLE_TEXT = "Choose your class";
    private static final int NUM_BUTTONS_RAW = 3;
    private static final String BOX = "box";
    private static final String SELECTION_BUTTON = "button_selection";
    private static final String TITLE = "title";
    private static final String EXIT = "exit_button";
    private static final String CONFIRM_BUTTON = "confirm_button";
    private static final String DESCRIPTION_AREA = "description_area";
    private static final String STAT_BOX = "stat_box";
    private static final String STAT_CELL = "stat_cell";
    private final PlayerController controller;

    private final VBox left = new VBox();
    private final ScrollPane center = new ScrollPane();
    private final BorderPane bottom = new BorderPane();

    private final List<Button> buttons = new ArrayList<>();

    /**
     * @param controller
     */
    public PlayerClassMenuImpl(final PlayerController controller) {
        super();
        this.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());
        this.setId(BOX);
        this.controller = controller;
        this.setPrefWidth(PREF_WIDTH);
        this.setPrefHeight(PREF_HEIGHT);

        final BorderPane up = new BorderPane();
        up.setMaxHeight(PREF_HEIGHT / PROP_HEIGHT);
        up.setId(TITLE);
        this.setTop(up);
        final var title = new Text(TITLE_TEXT);
        title.setStyle(FX_FONT_SIZE + PREF_HEIGHT / PROP_FONT);
        up.setCenter(title);

        final Button exitButton = new Button();
        exitButton.setOnAction((e) -> {
            controller.exit();
        });
        exitButton.setId(EXIT);
        exitButton.setMinWidth(EXIT_BUTTON_SIZE);
        exitButton.setMinHeight(EXIT_BUTTON_SIZE);
        up.setRight(exitButton);

        final BorderPane container = new BorderPane();
        container.setLeft(this.left);

        this.setCentralPane();
        buttons.get(0).fire();

        container.setCenter(this.center);

        container.setBottom(this.bottom);

        this.setCenter(container);
        final var confirmBox = new VBox();
        final Button confirmButton = new Button(CONFIRM_BUTTON_TEXT);
        confirmButton.setId(CONFIRM_BUTTON);
        confirmBox.getChildren().add(confirmButton);
        confirmBox.setAlignment(Pos.CENTER);
        confirmButton.setOnAction((e) -> {
            controller.confirmSelection();
        });
        this.setBottom(confirmBox);
    }

    /**
     * {@inheritDoc}
     */
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
                button.setMinWidth(SELECTION_BUTTON_SIZE);
                button.setMinHeight(SELECTION_BUTTON_SIZE);

                final var img = new ImageView(controller.getSpriteImage(id).get());
                img.setFitWidth(SELECTION_IMAGE_SIZE);
                img.setFitHeight(SELECTION_IMAGE_SIZE);
                final var graphic = new BorderPane();
                graphic.setTop(img);
                BorderPane.setAlignment(graphic.getTop(), Pos.CENTER);

                final Text text = new Text(controller.getName(id).get());
                text.setWrappingWidth(WRAPPING_TEXT_WIDTH);
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
            final ImageView img = new ImageView(controller.getSpriteImage(id).get());
            img.setFitHeight(DESCRIPTION_IMAGE_SIZE);
            img.setFitWidth(DESCRIPTION_IMAGE_SIZE);
            bottom.setLeft(img);

            final VBox txt = new VBox(new Text(controller.getDescription(id).get()));
            txt.setAlignment(Pos.CENTER);
            txt.setId(DESCRIPTION_AREA);
            bottom.setCenter(txt);

            controller.select(id);
        });
    }

    private void showStats(final Map<Statistic, Integer> stats) {
        final GridPane pane = new GridPane();
        pane.setId(STAT_BOX);
        int t = 0;
        for (final var s : Statistic.values()) {
            final VBox cell = new VBox();
            cell.setId(STAT_CELL);
            cell.getChildren().add(new Text(s.toString()));
            cell.getChildren().add(new Text(stats.get(s).toString()));
            pane.add(cell, 0, t);
            t++;
        }
        left.getChildren().clear();
        left.getChildren().add(pane);
    }

}
