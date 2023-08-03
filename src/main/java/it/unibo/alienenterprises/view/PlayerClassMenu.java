package it.unibo.alienenterprises.view;

import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerClassMenu extends BorderPane {

    private static final String QUESTION = "Choose your class";

    private BorderPane box = new BorderPane();

    public BorderPane setUpPlayerClassMenu() {

        final VBox up = new VBox();
        up.getChildren().add(new Text(QUESTION));
        up.setAlignment(Pos.CENTER);
        box.setTop(up);

        final VBox left = new VBox();
        left.getChildren().add(new TextArea("Qui ci vanno le statistiche"));
        box.setLeft(left);

        final GridPane center = new GridPane();
        center.add(new TextArea("Qui ci vanno i pulsanti per la selezione"), 0, 0);
        box.setCenter(center);

        final VBox bottom = new VBox();
        bottom.getChildren().add(new TextArea("Qui ci va la descrizione e l'immagine"));
        box.setBottom(bottom);

        return this.box;
    }
}
