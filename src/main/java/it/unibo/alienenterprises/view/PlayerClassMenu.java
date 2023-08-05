package it.unibo.alienenterprises.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.alienenterprises.controller.PlayerMenuController;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.view.javafx.SceneLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PlayerClassMenu extends BorderPane {

    private static final String QUESTION = "Choose your class";

    private static final int NUM_BUTTONS_RAW = 3;

    private final PlayerMenuController controller;

    private final VBox left = new VBox();
    private final GridPane center = new GridPane();
    private final VBox bottom = new VBox();

    private Text bottomTextArea = new Text("Qui ci va la descrizione e l'immagine");

    private final List<Button> buttons = new ArrayList<>();

    public PlayerClassMenu(final PlayerMenuController controller, final SceneLoader sceneLoader) {
        super();
        this.controller = controller;

        final VBox up = new VBox();
        up.getChildren().add(new Text(QUESTION));
        up.setAlignment(Pos.CENTER);
        this.setTop(up);

        final BorderPane container = new BorderPane();

        container.setLeft(this.left);

        this.setCentralPane();
        buttons.get(0).fire();
        container.setCenter(this.center);

        this.bottom.getChildren().add(this.bottomTextArea);
        container.setBottom(this.bottom);

        this.setCenter(container);
        final Button confirmButton = new Button("Conferma");
        confirmButton.setOnAction((e)->{
            controller.confirmSelection();
            sceneLoader.setScene(SceneType.GAMESTAGE);
        });
        this.setBottom(confirmButton);
    }

    private void setCentralPane() {
        final List<String> list = controller.getPlayerIds().stream().sorted().toList();
        int j;
        String id;
        for (int i = 0; i < list.size() / (double) NUM_BUTTONS_RAW; i++) {
            j = i * NUM_BUTTONS_RAW;
            for (int t = j; t < list.size() && t < j + NUM_BUTTONS_RAW; t++) {
                id = list.get(t);
                final Button button = new Button(controller.getName(id).get());
                setAction(button, id);
                this.buttons.add(button);
                center.add(button, t-j, i);
            }
        }
    }

    private void setAction(final Button button, final String id){
        button.setOnAction((event)->{
            buttons.forEach((b)->b.setDisable(false));
            button.setDisable(true);
            showStats(controller.getStats(id).get());
            bottomTextArea.setText(controller.getDescription(id).get());
            controller.select(id);
        });
    }

    private void showStats(final Map<Statistic,Integer> stats){
        final GridPane pane = new GridPane();
        pane.getColumnConstraints().add(new ColumnConstraints(120));
        pane.getColumnConstraints().add(new ColumnConstraints(50));
        int t = 0;
        for(final var s : Statistic.values()){
            pane.add(new Text(s.toString()), 0, t);
            pane.add(new Text(stats.get(s).toString()), 1, t);
            t++;
        }
        left.getChildren().clear();
        left.getChildren().add(pane);
    }
}
