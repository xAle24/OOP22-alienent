package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;
import javafx.stage.Stage;

/**
 * Alien enterprises game jafaFX main.
 */
public final class ViewImpl implements View {
    private Controller controller;
    private Stage primaryStage;

    public ViewImpl(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Alienent");
        this.primaryStage.setMinHeight(500);
        this.primaryStage.setMinWidth(500);
        this.controller.changeScene(ViewType.MAINMENU);
        this.primaryStage.setScene(this.controller.getSceneController().getCurrentScene());
    }

    @Override
    public void init(Controller controller) {
        this.controller = controller;
        this.primaryStage.show();
    }

    @Override
    public void setScene() {
        this.primaryStage.setScene(this.controller.getSceneController().getCurrentScene());
        this.primaryStage.show();
    }

}
