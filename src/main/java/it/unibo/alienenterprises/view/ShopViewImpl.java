package it.unibo.alienenterprises.view;

import javafx.stage.Stage;
import it.unibo.alienenterprises.view.api.ShopView;
import javafx.application.Application;

/**
 * Implementation fo ShopView.
 */
public class ShopViewImpl extends Application implements ShopView {

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");

        /*
         * Group root = new Group(); // creiamo una radice per arrangiare i
         * components/nodi
         * Scene scene = new Scene(root, Color.BISQUE);
         * 
         * // con image icon si agguge una icona in alto, quella tipo del videogioco
         * stage.setTitle("Caspiam");
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
         * 
         * Text text = new Text();
         * text.setText("Whoooooa!!");
         * text.setX(50);
         * text.setY(50);
         * text.setFont(Font.font("Comic Sans", 60));
         * text.setFill(Color.DARKMAGENTA);
         * 
         * Button button = new Button("Buy");
         * button.setFont(Font.font("Comic Sans", 34));
         * 
         * root.getChildren().add(text);
         * root.getChildren().add(button);
         * 
         * stage.setScene(scene);
         * stage.show();
         */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

}
