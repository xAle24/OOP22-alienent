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

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private BorderPane inventoryPopup = new BorderPane();
    private Popup attentionPopup = new Popup();
    private Text inventoryText = new Text();
    private Text attentionText = new Text();

    private Map<Button, String> pwuButtons = new HashMap<>();
    private List<PowerUpRenderer> pwuInfo = new LinkedList<>();
    private Map<String, List<CheckBox>> checkBoxesMap = new HashMap<>();

    private enum ExitCondition {
        SHOP, ATTENTION, INVENTORY, PWUINFO;
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
        setInventoryPopup();

        // Set the title
        Label title = new Label("SHOP");
        title.setId("title");

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
        BorderPane.setAlignment(bottom, Pos.CENTER);
        box.setBottom(bottom);

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
            double anchorX = userInfo.getPrefWidth() + 10;
            double anchorY = userInfo.getPrefHeight() + 10;
            AnchorPane.setTopAnchor(inventoryPopup, anchorY);
            AnchorPane.setLeftAnchor(inventoryPopup, anchorX);
            inventoryPopup.setVisible(true);
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
            BorderPane pwuBox = new BorderPane();

            pwuBox.setPrefSize(widthUnit * 4, widthUnit * 5);
            pwuBox.setId("pwubox");

            String imageURL = new String("/images/" + curr.getImage());
            // GAME_PATH + SEPARATOR + curr.getImage());

            Button button = new Button();
            button.setStyle("-fx-background-image: url(" + imageURL + ");" +
                    "-fx-background-position: center;" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-size: cover;");
            button.setPrefSize(widthUnit * 3, widthUnit * 3);

            pwuButtons.put(button, curr.getId());

            Label name = new Label(curr.getName());
            name.setFont(Font.font("Times New Roman", FontWeight.BOLD, 19));
            name.setTextFill(Color.DARKBLUE);
            name.setWrapText(true);
            name.setPrefWidth(widthUnit * 4);

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

            pwuBox.setTop(button);
            pwuBox.setCenter(name);
            pwuBox.setBottom(checkGrid);
            BorderPane.setAlignment(button, Pos.CENTER);
            BorderPane.setAlignment(name, Pos.CENTER);
            BorderPane.setAlignment(checkGrid, Pos.CENTER);

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

        scroll.setId("scroll");

        scroll.setMaxSize((SCREENWIDHT / 7) * 6, (SCREENHEIGHT / 6) * 2.5);
        grid.setHgap((scroll.getMaxWidth() / 12));
        grid.setVgap(scroll.getMaxHeight() / 12);
        grid.setPrefSize(scroll.getPrefWidth(), scroll.getPrefHeight());
        this.scroll.setContent(grid);
    }

    private void addAction(PowerUpRenderer curr, Button button) {
        button.setOnAction(event -> {

            bottom.setId("bottom");
            bottom.setPrefSize(SCREENWIDHT - 40, widthUnit * 4);

            VBox icon = new VBox();
            icon.setAlignment(Pos.CENTER);
            ImageView image = new ImageView("/images/" + curr.getImage());

            icon.setId("icon");
            icon.getChildren().add(image);
            bottom.setLeft(icon);

            Label newName = new Label(curr.getName());
            newName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
            final StringBuilder stats = new StringBuilder("");
            curr.getPwu().getStatModifiers()
                    .forEach((s, i) -> stats.append(i != 0 ? s + ": " + i + "\n" : ""));

            Text description = new Text(curr.getDescription() + "\n" + stats.toString());
            description.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            TextFlow descContenitor = new TextFlow(description);

            ScrollPane scrollDesc = new ScrollPane();
            scrollDesc.setId("scrollDesc");
            VBox descBox = new VBox();
            descBox.setId("descbox");
            descBox.getChildren().add(newName);
            descBox.getChildren().add(descContenitor);
            descBox.setMaxWidth(widthUnit * 20);
            scrollDesc.setContent(descBox);
            scrollDesc.setMaxWidth(widthUnit * 20.3);

            bottom.setCenter(scrollDesc);
            BorderPane.setAlignment(scrollDesc, Pos.CENTER_LEFT);

            Button buyButton = new Button();
            buyButton.setId("buybutton");
            int cost = 0;
            if (controller.getUserAccount().getCurrLevel(curr.getId()) == 0) {
                cost = curr.getPwu().getCost();
            } else {
                cost = curr.getPwu().getCost()
                        * (controller.getUserAccount().getInventory().get(curr.getId()).intValue() + 1);
            }
            buyButton.setText(String.valueOf(cost));
            buyButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));

            buyButton.setOnAction(e -> {
                buyEvent(curr, buyButton);
            });

            buyButton.setPrefSize(widthUnit * 3, heightUnit);

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
            setAttentionPopup();
            attentionPopup.show(box, anchorX, anchorY);
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
            updateInventoryPopup();

        }
    }

    private void setInventoryPopup() {
        VBox button = new VBox();

        /*
         * popUpContent.setStyle(
         * "-fx-background-image: url('/images/UI_Flat_Frame_Standard.png'); -fx-background-size: cover; -fx-background-position: center;"
         * );
         */

        inventoryPopup.setId("inventorypopup");

        inventoryPopup.setMaxSize(widthUnit * 5, heightUnit * 8);

        button.getChildren().add(setExitButton(ExitCondition.INVENTORY));
        button.setAlignment(Pos.TOP_RIGHT);
        Insets margins = new Insets(widthUnit / 4, widthUnit / 2, widthUnit / 4, widthUnit / 2);
        button.setPadding(margins);

        TextFlow textBox = new TextFlow();

        updateInventoryPopup();

        inventoryText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

        textBox.getChildren().add(inventoryText);
        textBox.setTextAlignment(TextAlignment.LEFT);

        AnchorPane.setLeftAnchor(textBox, 10.0);
        AnchorPane.setRightAnchor(button, 10.0);

        inventoryPopup.getChildren().addAll(textBox, button);

    }

    private void updateInventoryPopup() {
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

        inventoryText.setText(
                controller.getUserAccount().getNickname() + "\n" + controller.getUserAccount().getMoney()
                        + "\n" + stats);
    }

    private void setAttentionPopup() {
        HBox popUpContent = new HBox();
        VBox button = new VBox();

        /*
         * popUpContent.setStyle(
         * "-fx-background-image: url('/images/UI_Flat_Frame_Standard.png'); -fx-background-size: cover; -fx-background-position: center;"
         * );
         */
        // popUpContent.setId("popup");

        button.getChildren().add(setExitButton(ExitCondition.ATTENTION));
        button.setAlignment(Pos.TOP_RIGHT);
        Insets margins = new Insets(widthUnit / 4, widthUnit / 2, widthUnit / 4, widthUnit / 2);
        button.setPadding(margins);

        TextFlow textBox = new TextFlow();

        attentionText.setText("ATTENTION!\n You don't have enough money for this powerUp");
        attentionText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

        textBox.getChildren().add(attentionText);
        textBox.setTextAlignment(TextAlignment.LEFT);

        popUpContent.getChildren().addAll(textBox, button);

        attentionPopup.getContent().add(popUpContent);

    }

    private Button setExitButton(ExitCondition condition) {

        Button exitButton = new Button();
        exitButton.setId("exitButton");
        exitButton.setPrefSize(widthUnit, widthUnit);
        Insets margins = new Insets(widthUnit / 4, widthUnit / 2, widthUnit / 4, widthUnit / 4);
        exitButton.setPadding(margins);

        switch (condition) {
            case ATTENTION:
                exitButton.setOnAction(closePopUp -> {
                    this.attentionPopup.hide();
                });
                break;
            case PWUINFO:
                exitButton.setOnAction(closePwuInfo -> {
                    this.bottom.setVisible(false);
                });
                BorderPane.setAlignment(exitButton, Pos.TOP_RIGHT);
                break;
            case SHOP:
                exitButton.setOnAction(closeShop -> {
                    this.box.setVisible(false);
                });
                break;
            case INVENTORY:
                exitButton.setOnAction(closePopUp -> {
                    this.inventoryPopup.setVisible(false);
                });
                break;
            default:
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
