package it.unibo.alienenterprises.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.alienenterprises.controller.PlayerSpawnerControllerImpl;
import it.unibo.alienenterprises.model.GameWorld;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerClassMenu extends BorderPane {

    private static final String QUESTION = "Choose your class";

    //TODO linea temporanea
    private final PlayerSpawnerControllerImpl controller = new PlayerSpawnerControllerImpl(new GameWorld());

    private final VBox left = new VBox();
    private final GridPane center = new GridPane();
    private final VBox bottom = new VBox();

    private TextArea leftTextArea  = new TextArea("Qui ci vanno le statistiche");
    private TextArea bottomTextArea =  new TextArea("Qui ci va la descrizione e l'immagine");

    private final Map<String,Button> buttons = new HashMap<>();

    public BorderPane setUpPlayerClassMenu() {

        final VBox up = new VBox();
        up.getChildren().add(new Text(QUESTION));
        up.setAlignment(Pos.CENTER);
        this.setTop(up);

        this.leftTextArea.setMaxWidth(200);
        this.left.getChildren().add(leftTextArea);
        this.setLeft(this.left);

        this.setCentralPane();
        this.setCenter(this.center);

        this.bottom.getChildren().add(this.bottomTextArea);
        this.setBottom(this.bottom);

        return this;
    }

    private void setCentralPane(){
        final List<String> ids  = new ArrayList<>(controller.getIdSet());
        System.out.println(ids);
        final var numRaws = ids.size()/(double)3;
        for(int i = 0; i<numRaws; i++){
            for(int t = i*3; t<ids.size() && t<i*3+3; t++){
                final var id = ids.get(t); 
                final var info = controller.getInfoOf(id).get();
                final Button button = new Button(info.getName());
                this.buttons.put(id, button);
                this.center.add(button, t-i*3, i);
            }
        }
    }
}
