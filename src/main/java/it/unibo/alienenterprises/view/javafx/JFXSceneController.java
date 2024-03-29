package it.unibo.alienenterprises.view.javafx;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.ShopControllerImpl;
import it.unibo.alienenterprises.controller.api.ShopController;
import it.unibo.alienenterprises.view.PlayerClassMenuImpl;
import it.unibo.alienenterprises.view.ShopViewImpl;
import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.controllers.InitController;
import it.unibo.alienenterprises.view.controllers.PlayerController;
import it.unibo.alienenterprises.view.controllers.PlayerControllerImpl;
import it.unibo.alienenterprises.view.viewstates.BaseState;
import it.unibo.alienenterprises.view.viewstates.IdleState;
import it.unibo.alienenterprises.view.viewstates.PlayingState;
import it.unibo.alienenterprises.view.viewstates.ViewState;
import javafx.scene.Scene;

/**
 * {@link SceneController} implementation.
 * 
 * @author Giulia Bonifazi
 */
public final class JFXSceneController implements SceneController {
    private final JFXSceneLoader loader = new JFXSceneLoader();
    private final Controller controller;

    private Scene currentScene;
    private InitController currentController;
    private ViewState viewState;

    /**
     * Creates a {@link SceneController} for JavaFX.
     * 
     * @param controller the main {@link Controller} of the game.
     */
    public JFXSceneController(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public void setCurrentScene(final ViewType type) {
        switch (type) {
            case SHOP:
                this.currentController = new ShopControllerImpl();
                final var contrShop = (ShopController) this.currentController;
                contrShop.loadPwuYaml();
                contrShop.loadPwuInfoYaml();
                this.currentScene = new Scene(
                        new ShopViewImpl(contrShop, this.controller.getUserAccount()).showShopView());
                break;
            case SHIPSELECT:
                this.currentController = new PlayerControllerImpl();
                final var contrPlayer = (PlayerController) this.currentController;
                this.currentScene = new Scene(new PlayerClassMenuImpl(contrPlayer));
                break;
            default:
                this.currentScene = new Scene(this.loader.getParent(type));
                this.currentController = this.loader.getCurrentController();

        }
        this.setState(type);
        this.currentController.init(controller, this.currentScene);
    }

    @Override
    public Scene getCurrentScene() {
        return this.currentScene;
    }

    @Override
    public InitController getCurrentController() {
        return this.currentController;
    }

    @Override
    public ViewState getViewState() {
        return this.viewState;
    }

    private void setState(final ViewType type) {
        if (type == ViewType.GAMESTAGE) {
            this.viewState = new PlayingState(controller,
                    "Are you sure you want to give up?\n(This will trigger a game over)");
        } else if (type == ViewType.LOGIN || type == ViewType.REGISTRATION) {
            this.viewState = new BaseState(controller, "Giving up so soon?");
        } else {
            this.viewState = new IdleState(controller,
                    "Are you sure you want to quit?\n(Your purchases will be saved)");
        }
    }

}
