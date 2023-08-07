package it.unibo.alienenterprises.view;

//import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.unibo.alienenterprises.controller.api.ShopController;
import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import it.unibo.alienenterprises.view.api.ShopView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;
import javafx.stage.Screen;

/**
 * Implementation fo ShopView.
 */
public class ShopViewImpl extends BorderPane implements ShopView {

    private static final int MAXLENGHT = 4;
    // private static final String SEPARATOR = File.separator;
    // private static final String GAME_PATH = "src/main/resources/examplemvc";
    // System.getProperty("user.home") + SEPARATOR + ".Alien Enterprises";
    private static final double SCREENWIDHT = Screen.getPrimary().getVisualBounds().getWidth();
    private static final double SCREENHEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    private Double widthUnit;
    private Double heightUnit;

    private ShopController controller;
    private BorderPane box = new BorderPane();
    private Button inventory = new Button();
    private HBox userInfo = new HBox();
    private Label score = new Label();
    private ScrollPane scroll = new ScrollPane();
    private BorderPane bottom = new BorderPane();
    private Popup popUp = new Popup();

    private Map<Button, String> pwuButtons = new HashMap<>();
    private List<PowerUpRenderer> pwuInfo = new LinkedList<>();
    private Map<String, List<CheckBox>> checkBoxesMap = new HashMap<>();

    private enum PopUpCondition {
        INVENTORY, ATTENTION;
    }

    private enum ExitCondition {
        SHOP, POPUP, PWUINFO;
    }

    /**
     * This constructor ensures that the view has always a controller to reference.
     * 
     * @param controller
     */
    public ShopViewImpl(final ShopController controller) {
        this.controller = controller;
        pwuInfo.addAll(controller.getPwuInfo());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PowerUpRenderer> getInfo() {
        return this.pwuInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BorderPane showShopView() {

        widthUnit = SCREENWIDHT / 28;
        heightUnit = SCREENHEIGHT / 19.5;

        box.getStylesheets().addAll(getClass().getResource(
                "/css/ShopGui.css")
                .toExternalForm());

        box.setId("box");
        box.setMaxSize(SCREENWIDHT, SCREENHEIGHT);

        // Set upper part of the screen

        // Set the user info
        setUserInfo();

        // Set the title
        Label title = new Label("SHOP");
        title.setId("title");
        title.setLayoutX((SCREENWIDHT / 2) - title.getPrefWidth());

        // Fill the borderPane in the upper screen with all the elements
        BorderPane top = new BorderPane();
        top.setLeft(userInfo);
        top.setCenter(title);
        top.setRight(setExitButton(ExitCondition.SHOP));
        BorderPane.setAlignment(userInfo, Pos.CENTER);
        BorderPane.setAlignment(title, Pos.CENTER);
        // BorderPane.setAlignment(exitButton, Pos.TOP_RIGHT);

        // Set the scroll with a GridPane inside
        setScroll();

        // Fill the borderPane with all the elements
        box.setTop(top);
        BorderPane.setAlignment(scroll, Pos.TOP_CENTER);
        box.setCenter(scroll);
        BorderPane.setAlignment(bottom, Pos.TOP_LEFT);
        box.setBottom(bottom);

        this.box.setVisible(false);

        setCheck();

        this.box.setVisible(true);

        return this.box;

    }

    private void setUserInfo() {
        userInfo.setId("user");
        inventory.setId("inventory");

        userInfo.setPadding(new Insets(widthUnit / 2, widthUnit / 2, widthUnit / 2, widthUnit / 2));

        score.setText(controller.getUserAccount().getMoney() + "$");
        score.setId("score");

        inventory.setPrefSize(widthUnit, widthUnit);
        Insets margins = new Insets(widthUnit / 4, widthUnit / 2, widthUnit / 4, widthUnit / 2);
        inventory.setPadding(margins);

        BorderPane.setMargin(userInfo, new Insets(widthUnit / 2, widthUnit / 2, widthUnit / 2, widthUnit / 2));
        userInfo.setPrefSize(widthUnit * 5, heightUnit * 2);

        inventory.setOnAction(inventory -> {
            setPopUpWindow(PopUpCondition.INVENTORY);
            double anchorX = userInfo.getPrefWidth() + BorderPane.getMargin(userInfo).getBottom();
            double anchorY = userInfo.getPrefHeight() + BorderPane.getMargin(userInfo).getRight();
            popUp.show(box, anchorX, anchorY);
        });

        userInfo.getChildren().addAll(inventory, score);
    }

    private void setScroll() {
        GridPane grid = new GridPane();

        grid.setId("grid");

        Iterator<PowerUpRenderer> iter = pwuInfo.iterator();

        int columNum = 0;
        int rowNum = 0;

        while (iter.hasNext()) {
            PowerUpRenderer curr = iter.next();
            VBox pwuBox = new VBox();
            pwuBox.setId("pwubox");
            Image image = new Image(
                    "C:/Users/ginni/Desktop/ProgettoOOP/OOP22-alienent/src/main/resources/examplemvc/"
                            + curr.getImage());
            // GAME_PATH + SEPARATOR + curr.getImage());

            Button button = new Button();
            button.setGraphic(new ImageView(image));
            pwuButtons.put(button, curr.getId());

            Label name = new Label(curr.getName());
            name.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            name.setTextFill(Color.DARKBLUE);
            name.setWrapText(true);

            addAction(curr, button);

            List<CheckBox> pwuCheckBoxs = new LinkedList<>();

            HBox checkGrid = new HBox();
            for (int i = 0; i < curr.getPwu().getMaxLevel(); i++) {
                CheckBox check = new CheckBox();
                check.setDisable(true);
                checkGrid.getChildren().add(check);
                pwuCheckBoxs.add(check);
            }

            checkBoxesMap.put(curr.getId(), pwuCheckBoxs);

            pwuBox.getChildren().addAll(button, name, checkGrid);
            pwuBox.setPrefWidth(200);
            pwuBox.setAlignment(Pos.CENTER);

            if (pwuButtons.size() == pwuInfo.size()) {
                grid.add(pwuBox, columNum, rowNum);
                break;
            } else {
                if (columNum != MAXLENGHT) {
                    grid.add(pwuBox, columNum, rowNum);
                    columNum++;
                } else {
                    rowNum++;
                    columNum = 0;
                    grid.add(pwuBox, columNum, rowNum);
                    columNum++;
                }
            }

        }

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        scroll.setId("scroll");

        scroll.setMaxSize((screenBounds.getWidth() / 7) * 6, (screenBounds.getHeight() / 6) * 2);
        grid.setHgap((scroll.getMaxWidth() / 12));
        grid.setVgap(scroll.getMaxHeight() / 12);
        this.scroll.setContent(grid);
    }

    private void addAction(PowerUpRenderer curr, Button button) {
        button.setOnAction(event -> {

            ImageView icon = new ImageView(
                    "C:/Users/ginni/Desktop/ProgettoOOP/OOP22-alienent/src/main/resources/examplemvc/"
                            + curr.getImage());

            bottom.setLeft(icon);

            Label newName = new Label(curr.getName());
            newName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
            final StringBuilder stats = new StringBuilder("");
            curr.getPwu().getStatModifiers()
                    .forEach((s, i) -> stats.append(i != 0 ? s + ": " + i + "\n" : ""));
            TextArea description = new TextArea(curr.getDescription() + stats.toString());
            description.setWrapText(true);
            description.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            description.setEditable(false);

            VBox descBox = new VBox();
            descBox.getChildren().add(newName);
            descBox.getChildren().add(description);

            bottom.setCenter(descBox);

            Button buyButton = new Button();
            int cost = 0;
            if (controller.getUserAccount().getCurrLevel(curr.getPwu().getId()) == 0) {
                cost = curr.getPwu().getCost();
            } else {
                cost = curr.getPwu().getCost()
                        * (controller.getUserAccount().getInventory().get(curr.getId()) + 1);
            }
            buyButton.setText(String.valueOf(cost));
            buyButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));

            buyButton.setOnAction(e -> {
                buyEvent(curr, buyButton);
            });

            buyButton.setPrefWidth(300); // Imposta la larghezza preferita a 200
            buyButton.setPrefHeight(50);

            BorderPane rightContainer = new BorderPane();
            rightContainer.setBottom(buyButton);

            rightContainer.setTop(setExitButton(ExitCondition.PWUINFO));

            bottom.setRight(rightContainer);
            bottom.setVisible(true);

        });

    }

    private void buyEvent(PowerUpRenderer curr, Button buyButton) {
        if (!controller.buy(curr.getId())) {
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            // Imposta l'anchor point al centro dello schermo
            double anchorX = screenBounds.getWidth() / 2 - 100;
            double anchorY = screenBounds.getHeight() / 2 - 200;
            setPopUpWindow(PopUpCondition.ATTENTION);
            popUp.show(box, anchorX, anchorY);
        } else {
            score.setText(controller.getUserAccount().getMoney() + "$");
            if (controller.getUserAccount().getCurrLevel(curr.getId()) == (curr.getPwu().getMaxLevel())) {
                buyButton.setDisable(true);
            } else {
                buyButton.setText(String.valueOf(
                        (controller.getUserAccount().getInventory().get(curr.getId()) + 1) * curr.getPwu().getCost()));
            }
            checkBoxesMap.get(curr.getId()).get(controller.getUserAccount().getCurrLevel(curr.getId()) - 1)
                    .setSelected(true);

        }
    }

    private void setPopUpWindow(PopUpCondition condition) {
        HBox popUpContent = new HBox();
        VBox button = new VBox();

        /*
         * popUpContent.setStyle(
         * "-fx-background-image: url('/images/UI_Flat_Frame_Standard.png'); -fx-background-size: cover; -fx-background-position: center;"
         * );
         */
        popUpContent.setId("popup");

        button.getChildren().add(setExitButton(ExitCondition.POPUP));
        button.setAlignment(Pos.TOP_RIGHT);
        Insets margins = new Insets(widthUnit / 4, widthUnit / 2, widthUnit / 4, widthUnit / 2);
        button.setPadding(margins);

        TextFlow textBox = new TextFlow();
        Text text = new Text();

        switch (condition) {
            case INVENTORY:
                Map<String, Integer> inventory = new HashMap<>();

                controller.getUserAccount().getInventory()
                        .forEach((s, l) -> {
                            pwuInfo.stream().filter(p -> p.getId().equals(s))
                                    .findFirst()
                                    .ifPresent(pwu -> pwu.getPwu().getStatModifiers()
                                            .forEach((stat, p) -> {
                                                inventory.compute(s,
                                                        (k, oldValue) -> oldValue == null ? p * l : oldValue + p * l);
                                            }));
                        });
                final StringBuilder stats = new StringBuilder("");
                inventory.forEach((s, i) -> stats.append(s).append(":\t").append(i).append("\n"));

                text.setText(controller.getUserAccount().getNickname() + "\n" + controller.getUserAccount().getMoney()
                        + "\n" + stats);
                break;
            case ATTENTION:
                text.setText("ATTENTION!\n You don't have enough money for this powerUp");
                break;
        }

        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

        textBox.getChildren().add(text);
        textBox.setTextAlignment(TextAlignment.LEFT);

        popUpContent.getChildren().addAll(textBox, button);

        popUp.getContent().add(popUpContent);

    }

    private Button setExitButton(ExitCondition condition) {

        Button exitButton = new Button();
        exitButton.setId("exitButton");
        exitButton.setPrefSize(widthUnit, widthUnit);
        Insets margins = new Insets(widthUnit / 4, widthUnit / 2, widthUnit / 4, widthUnit / 4);
        exitButton.setPadding(margins);

        switch (condition) {
            case POPUP:
                exitButton.setOnAction(closePopUp -> {
                    this.popUp.hide();
                });
                break;
            case PWUINFO:
                exitButton.setOnAction(closePwuInfo -> {
                    this.bottom.setVisible(false);
                });
                break;
            case SHOP:
                exitButton.setOnAction(closeShop -> {
                    this.box.setVisible(false);
                });
                break;
        }
        return exitButton;
    }

    private void setCheck() {
        checkBoxesMap.forEach((s, g) -> {
            if (controller.getUserAccount().getInventory().containsKey(s)) {
                controller.getUserAccount().getCurrLevel(s);
                for (int i = 0; i < controller.getUserAccount().getCurrLevel(s); i++) {
                    g.get(i).setSelected(true);
                }
            }
        });
    }

}
