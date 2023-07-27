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

    private ShopController controller;
    private BorderPane box = new BorderPane();
    private ScrollPane scroll = new ScrollPane();
    private BorderPane bottom = new BorderPane();
    private Label shownScore = new Label();
    private Button exitButton = new Button();
    private Popup popUp = new Popup();

    private Map<Button, String> buttons = new HashMap<>();
    private List<PowerUpRenderer> pwuInfo = new LinkedList<>();

    private Map<String, GridPane> checkBoxesMap = new HashMap<>();

    public ShopViewImpl(ShopController controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public BorderPane setUpShopView() {
        /* Setta titolo */
        Label text = new Label("SHOP");
        text.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        text.setTextFill(Color.RED);

        shownScore.setText(String.valueOf(controller.getUserAccount().getMoney()));
        shownScore.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        shownScore.setTextFill(Color.BLACK);

        /* Setta l'exitButton */
        setExitButton();

        /* Setta GridPane */
        setScroll();

        /* Infila tutto nella box */
        box.setTop(text);
        box.setCenter(scroll);
        box.setBottom(bottom);

        VBox rightSide = new VBox();
        rightSide.getChildren().addAll(exitButton, shownScore);
        box.setRight(rightSide);

        setPopUpWindow();

        box.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        this.box.setVisible(false);

        return this.box;

    }

    @Override
    public void show() {

        shownScore.setText(String.valueOf(controller.getUserAccount().getMoney()));
        setCheck();

        this.box.setVisible(true);
    }

    private void setScroll() {
        GridPane grid = new GridPane();

        Iterator<PowerUpRenderer> iter = pwuInfo.iterator();

        int columNum = 0;
        int rowNum = 0;

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
            name.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            name.setTextFill(Color.DARKBLUE);
            name.setWrapText(true);

            addAction(curr, button);

            GridPane checkGrid = new GridPane();
            for (int i = 0; i < curr.getPwu().getMaxLevel(); i++) {
                CheckBox check = new CheckBox();
                check.setDisable(true);
                checkGrid.add(check, i, 0);
            }

            checkBoxesMap.put(curr.getId(), checkGrid);

            pwuBox.getChildren().addAll(button, name, checkGrid);
            pwuBox.setPrefWidth(200);
            pwuBox.setAlignment(Pos.CENTER);

            if (buttons.size() == pwuInfo.size()) {
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

        grid.setAlignment(Pos.CENTER);
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
                        * controller.getUserAccount().getCurrLevel(curr.getPwu().getId());
            }
            buyButton.setText(String.valueOf(cost));
            buyButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));

            buyButton.setOnAction(e -> {
                buyEvent(curr);
            });

            buyButton.setPrefWidth(300); // Imposta la larghezza preferita a 200
            buyButton.setPrefHeight(50);

            VBox bottomRightContainer = new VBox(buyButton);
            bottomRightContainer.setAlignment(Pos.BOTTOM_RIGHT);

            bottom.setRight(bottomRightContainer);

        });

    }

    @Override
    public void loadPwuInfo(List<PowerUpRenderer> pwuInfo) {
        this.pwuInfo.addAll(pwuInfo);
    }

    @Override
    public List<PowerUpRenderer> getInfo() {
        return this.pwuInfo;
    }

    private void buyEvent(PowerUpRenderer curr) {
        if (!controller.buy(curr.getId())) {
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            // Imposta l'anchor point al centro dello schermo
            double anchorX = screenBounds.getWidth() / 2 - 100;
            double anchorY = screenBounds.getHeight() / 2 - 200;

            popUp.show(box, anchorX, anchorY);
        } else {
            shownScore.setText(String.valueOf(controller.getUserAccount().getMoney()));
            CheckBox checkBox = (CheckBox) checkBoxesMap.get(curr.getId()).getChildren()
                    .get((controller.getUserAccount().getCurrLevel(curr.getId()) - 1));
            checkBox.setSelected(true); // HELPPPPPPP

        }
    }

    private void setPopUpWindow() {
        HBox attention = new HBox();
        VBox button = new VBox();

        Button closeButton = new Button("X");
        closeButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        closeButton.setOnAction(closePopup -> {
            popUp.hide();
        });
        button.getChildren().add(closeButton);
        button.setAlignment(Pos.TOP_RIGHT);

        TextArea text = new TextArea("ATTENZIONE!\nStai tentando di comprare un powerup senza avere i soldi necessari");
        text.setWrapText(true);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        text.setEditable(false);

        attention.getChildren().addAll(text, button);

        popUp.getContent().add(attention);

    }

    private void setExitButton() {
        Image image = new Image(
                "C:/Users/ginni/Desktop/ProgettoOOP/OOP22-alienent/src/main/resources/examplemvc/cross.png");
        // GAME_PATH + SEPARATOR + curr.getImage());

        exitButton.setGraphic(new ImageView(image));
        exitButton.setOnAction(closeShop -> {
            this.box.setVisible(false);
        });
    }

    private void setCheck() {
        checkBoxesMap.forEach((s, g) -> {
            if (controller.getUserAccount().getInventory().containsKey(s)) {
                controller.getUserAccount().getCurrLevel(s);
                for (int i = 0; i < controller.getUserAccount().getCurrLevel(s); i++) {
                    CheckBox checkBox = (CheckBox) g.getChildren()
                            .get(i);
                    System.out.println(checkBox.isSelected());
                    checkBox.setSelected(true);
                    System.out.println(checkBox.isSelected());
                }
            }
        });
    }
}
