package it.unibo.alienenterprises.view;

import java.util.HashSet;
import java.util.Set;

import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import it.unibo.alienenterprises.view.api.ShopView;
import javafx.scene.Group;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Implementation fo ShopView.
 */
public class ShopViewImpl implements ShopView {

    // private GridPane grid = new GridPane();
    private Set<PowerUpRenderer> pwuInfo = new HashSet<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {

        Group root = new Group(); // creiamo una radice per arrangiare i components/nodi
        // Scene scene = new Scene(root, Color.BLUE);

        /*
         * // con image icon si agguge una icona in alto, quella tipo del videogioco
         * stage.setTitle("ShopView");
         * // con set heigh o width metti grandezze ma è comunque resizable
         * /*
         * Se setti x o y lo stage appare in varie posizione
         * setFullScreen usa tutto lo screen
         * Per cambiare le key di esc per uscire dal Full, fai
         * setFullScreenExitHint("stringa che showa")
         * setFullScreenExitKeyCombination(keycombination.valueof("letter")
         * )
         * 
         * 
         */
        Text text = new Text();
        text.setText("SHOP");
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 60));
        text.setFill(Color.YELLOW);

        Button button = new Button("Buy");
        button.setFont(Font.font("Comic Sans", 34));

        root.getChildren().add(text);
        root.getChildren().add(button);

        // ScrollPane scroll = new ScrollPane(grid);

    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    public void loadPwuInfo(Set<PowerUpRenderer> pwuInfo) {
        this.pwuInfo.addAll(pwuInfo);
    }

    @Override
    public Set<PowerUpRenderer> getInfo() {
        return this.pwuInfo;
    }

}
