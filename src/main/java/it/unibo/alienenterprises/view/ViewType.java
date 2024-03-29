package it.unibo.alienenterprises.view;

/**
 * Different types of scene possible in the game.
 * 
 * @author Giulia Bonifazi
 */
public enum ViewType {
    /**
     * Login screen.
     */
    LOGIN,

    /**
     * Registration screen.
     */
    REGISTRATION,

    /**
     * Main menu.
     */
    MAINMENU,

    /**
     * Tutorial screen.
     */
    TUTORIAL,

    /**
     * Ship selection screen.
     */
    SHIPSELECT,

    /**
     * Game world while a {@link GameSession} has not yet reached the game over
     * status.
     */
    GAMESTAGE,

    /**
     * Screen shown after the player has died.
     */
    GAMEOVER,

    /**
     * Shop for powerups.
     */
    SHOP,

    /**
     * Pause the game during a game session.
     */
    PAUSE
}
