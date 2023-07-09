package it.unibo.alienenterprises.view;

//import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import it.unibo.alienenterprises.view.api.ShopView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Implementation fo ShopView.
 */
public class ShopViewImpl implements ShopView {

    // private static final int MAXLENGHT = 4;
    // private static final String SEPARATOR = File.separator;
    // private static final String GAME_PATH = "src/main/resources/examplemvc";
    // System.getProperty("user.home") + SEPARATOR + ".Alien Enterprises";

    private Set<PowerUpRenderer> pwuInfo = new HashSet<>();
    private Map<Button, String> buttons = new HashMap<>();
    private VBox box = new VBox();

    /**
     * {@inheritDoc}
     */
    @Override
    public VBox show() {

        // Group root = new Group(); // creiamo una radice per arrangiare i
        // components/nodi
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
        text.setFill(Color.RED);

        GridPane grid = new GridPane();

        Iterator<PowerUpRenderer> iter = pwuInfo.iterator();

        int j = 0;

        while (iter.hasNext()) {
            PowerUpRenderer curr = iter.next();
            VBox pwuBox = new VBox();
            Image image = new Image(
                    "C:/Users/ginni/Desktop/ProgettoOOP/OOP22-alienent/src/main/resources/examplemvc/"
                            + curr.getImage());
            // GAME_PATH + SEPARATOR + curr.getImage());
            Button button = new Button();
            button.setGraphic(new ImageView(image));
            buttons.put(button, curr.getId());
            Label name = new Label(curr.getName());
            Label desc = new Label(curr.getDescription());
            GridPane checkGrid = new GridPane();
            for (int i = 0; i < curr.getPwu().getMaxLevel(); i++) {
                CheckBox check = new CheckBox();
                check.setDisable(true);
                checkGrid.add(check, i, 0);
            }
            pwuBox.getChildren().addAll(button, name, desc, checkGrid);
            grid.add(pwuBox, j, 0);
            j++;
        }

        ScrollPane scroll = new ScrollPane(grid);

        box.getChildren().addAll(text, scroll);
        box.setAlignment(Pos.TOP_CENTER);

        // ScrollPane scroll = new ScrollPane(grid);
        return this.box;

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
