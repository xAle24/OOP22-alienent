package it.unibo.alienenterprises.view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.unibo.alienenterprises.controller.api.ShopController;
import it.unibo.alienenterprises.model.api.PowerUpRenderer;
import it.unibo.alienenterprises.view.api.ShopView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Implementation fo ShopView.
 */
public class ShopViewImpl implements ShopView {

    private static final int MAXLENGHT = 4;
    private static final double SCREENWIDHT = Screen.getPrimary().getVisualBounds().getWidth();
    private static final double SCREENHEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    private Double widthUnit;
    private Double heightUnit;
    private Double margin;

    private ShopController controller;
    private BorderPane box = new BorderPane();
    private Button inventory = new Button();
    private HBox userInfo = new HBox();
    private Label score = new Label();
    private ScrollPane scroll = new ScrollPane();
    private BorderPane bottom = new BorderPane();
    private Text inventoryText = new Text();
    private Text attentionText = new Text();
    private Stage inventoryStage = new Stage();
    private Stage attentionStage = new Stage();

    private Map<Button, String> pwuButtons = new HashMap<>();

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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BorderPane showShopView() {

        widthUnit = SCREENWIDHT / 28;
        heightUnit = SCREENHEIGHT / 19.5;
        margin = widthUnit / 2;

        box.getStylesheets().addAll(getClass().getResource(
                "/css/ShopGui.css")
                .toExternalForm());

        box.setId("box");

        box.setMaxSize(SCREENWIDHT, SCREENHEIGHT); // forse puoi settarlo su css

        // Set upper part of the screen

        // Set the user info
        setUserInfo();
        setInventoryPopup();
        setAttentionPopup();

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

        // Set the scroll with a GridPane inside
        setScroll();

        // Fill the outer borderPane with all the elements
        box.setTop(top);
        box.setCenter(scroll);
        box.setBottom(bottom);

        BorderPane.setAlignment(scroll, Pos.TOP_CENTER);
        BorderPane.setAlignment(bottom, Pos.CENTER);

        setCheck();

        this.box.setVisible(true);

        return this.box;

    }

    private void setUserInfo() {

        score.setText(controller.getUserAccount().getMoney() + "$");
        score.setId("score");

        inventory.setId("inventory");
        inventory.setPrefSize(widthUnit, widthUnit);
        inventory.setPadding(new Insets(margin));

        inventory.setOnAction(inventory -> {
            this.inventoryStage.show();
        });

        userInfo.setId("user");
        userInfo.setPrefSize(widthUnit * 5, heightUnit * 2);
        userInfo.setPadding(new Insets(margin));
        BorderPane.setMargin(userInfo, new Insets(margin));
        userInfo.getChildren().addAll(inventory, score);
    }

    private void setScroll() {

        GridPane grid = new GridPane();
        grid.setId("grid");

        controller.getPwuInfo().forEach(p -> {

            int columNum = 0;
            int rowNum = 0;

            // Set the box
            BorderPane pwuBox = new BorderPane();
            pwuBox.setId("pwubox");
            pwuBox.setPrefSize(widthUnit * 4, widthUnit * 5);

            // Set the button
            String imageURL = new String("/images/" + p.getImage());
            Button button = new Button();
            button.setStyle("-fx-background-image: url(" + imageURL + ");" +
                    "-fx-background-position: center;" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-size: cover;");
            button.setPrefSize(widthUnit * 3, widthUnit * 3);
            pwuButtons.put(button, p.getId());
            // Add action to the button
            addAction(p, button);

            // Set the name
            Label name = new Label(p.getName());
            name.setFont(Font.font("Times New Roman", FontWeight.BOLD, 19));
            name.setTextFill(Color.DARKBLUE);
            name.setWrapText(true);
            name.setPrefWidth(widthUnit * 4);

            // Set the checkboxes
            List<CheckBox> pwuCheckBoxs = new LinkedList<>();
            HBox checkGrid = new HBox();
            for (int i = 0; i < p.getPwu().getMaxLevel(); i++) {
                CheckBox check = new CheckBox();
                check.setDisable(true);
                checkGrid.getChildren().add(check);
                pwuCheckBoxs.add(check);
            }
            checkBoxesMap.put(p.getId(), pwuCheckBoxs);

            // Add everything to the boz
            pwuBox.setTop(button);
            pwuBox.setCenter(name);
            pwuBox.setBottom(checkGrid);
            BorderPane.setAlignment(button, Pos.CENTER);
            BorderPane.setAlignment(name, Pos.CENTER);
            BorderPane.setAlignment(checkGrid, Pos.CENTER);

            if (columNum == MAXLENGHT) {
                rowNum++;
                columNum = 0;
            }
            grid.add(pwuBox, columNum++, rowNum);
        });

        scroll.setId("scroll");
        scroll.setMaxSize((SCREENWIDHT / 7) * 6, (SCREENHEIGHT / 6) * 2.5);

        grid.setHgap((scroll.getMaxWidth() / 12));
        grid.setVgap(scroll.getMaxHeight() / 12);
        GridPane.setFillWidth(grid, true);
        GridPane.setFillHeight(grid, true);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        grid.getColumnConstraints().add(columnConstraints);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setFillHeight(true);
        grid.getRowConstraints().add(rowConstraints);

        this.scroll.setContent(grid);
    }

    private void addAction(PowerUpRenderer curr, Button button) {
        button.setOnAction(event -> {

            bottom.setId("bottom");
            bottom.setPrefSize(SCREENWIDHT - 40, widthUnit * 4);

            // Set icon
            VBox icon = new VBox();
            icon.setAlignment(Pos.CENTER);
            ImageView image = new ImageView("/images/" + curr.getImage());
            icon.setId("icon");
            icon.getChildren().add(image);
            bottom.setLeft(icon);

            // Set name
            Label name = new Label(curr.getName());
            name.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
            final StringBuilder stats = new StringBuilder("");
            curr.getPwu().getStatModifiers()
                    .forEach((s, i) -> stats.append(i != 0 ? s + ": " + i + "\n" : ""));

            // Set description
            Text description = new Text(curr.getDescription() + "\n" + stats.toString());
            description.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            TextFlow descContenitor = new TextFlow(description);
            VBox descBox = new VBox();
            descBox.setId("descbox");
            descBox.getChildren().add(name);
            descBox.getChildren().add(descContenitor);
            descBox.setFillWidth(true);
            ScrollPane scrollDesc = new ScrollPane();
            scrollDesc.setId("scrollDesc");
            scrollDesc.setContent(descBox);
            scrollDesc.setMaxWidth(widthUnit * 20.3);

            // Set the buybutton
            Button buyButton = new Button();
            buyButton.setId("buybutton");
            buyButton.setPrefSize(widthUnit * 3, heightUnit);
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

            BorderPane rightContainer = new BorderPane();
            rightContainer.setBottom(buyButton);
            rightContainer.setTop(setExitButton(ExitCondition.PWUINFO));

            bottom.setCenter(scrollDesc);
            bottom.setRight(rightContainer);
            bottom.setVisible(true);
            BorderPane.setAlignment(scrollDesc, Pos.CENTER_LEFT);

        });

    }

    private void buyEvent(PowerUpRenderer curr, Button buyButton) {
        if (!controller.buy(curr.getId())) {
            attentionStage.show();
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
        inventoryStage.setMaxWidth(widthUnit * 5);
        inventoryStage.setMaxHeight(heightUnit * 8);
        inventoryStage.setX((SCREENWIDHT / 2) - (inventoryStage.getMaxWidth() / 2));
        inventoryStage.setY((SCREENWIDHT / 2) - (inventoryStage.getMaxHeight() / 2));

        BorderPane pane = new BorderPane();
        TextFlow textBox = new TextFlow();
        updateInventoryPopup();
        inventoryText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        textBox.getChildren().add(inventoryText);
        textBox.setTextAlignment(TextAlignment.LEFT);
        pane.setLeft(textBox);
        pane.setRight(setExitButton(ExitCondition.INVENTORY));

        Scene scene = new Scene(pane);

        inventoryStage.setScene(scene);

    }

    private void updateInventoryPopup() {

        final StringBuilder stats = new StringBuilder("");
        controller.getUserAccount().getToAddPwu()
                .forEach((s, i) -> stats.append(s).append(":\t").append(i).append("\n"));

        inventoryText.setText(
                controller.getUserAccount().getNickname() + "\n" + controller.getUserAccount().getMoney()
                        + "\n" + stats);
    }

    private void setAttentionPopup() {
        TextFlow textBox = new TextFlow();
        attentionText.setText("ATTENTION!\n You don't have enough money for this powerUp");
        attentionText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        textBox.getChildren().add(attentionText);
        textBox.setTextAlignment(TextAlignment.LEFT);

        attentionStage.setMaxWidth(widthUnit * 5);
        attentionStage.setMaxHeight(heightUnit * 8);
        attentionStage.setX((SCREENWIDHT / 2) - (attentionStage.getMaxWidth() / 2));
        attentionStage.setY((SCREENWIDHT / 2) - (attentionStage.getMaxHeight() / 2));

        BorderPane pane = new BorderPane();
        pane.setLeft(textBox);
        pane.setRight(setExitButton(ExitCondition.ATTENTION));

        Scene scene = new Scene(pane);
        attentionStage.setScene(scene);

    }

    private Button setExitButton(ExitCondition condition) {

        Button exitButton = new Button();
        exitButton.setId("exitButton");
        exitButton.setPrefSize(widthUnit, widthUnit);
        exitButton.setPadding(new Insets(margin / 2));

        switch (condition) {
            case ATTENTION:
                exitButton.setOnAction(closePopUp -> {
                    this.attentionStage.hide();
                });
                break;
            case PWUINFO:
                exitButton.setOnAction(closePwuInfo -> {
                    this.bottom.setVisible(false);
                });
                BorderPane.setAlignment(exitButton, Pos.TOP_RIGHT);
                break;
            case SHOP:
                this.controller.closeShop();
                break;
            case INVENTORY:
                exitButton.setOnAction(closePopUp -> {
                    this.inventoryStage.hide();
                });
                break;
            default:
                break;
        }
        return exitButton;
    }

    private void setCheck() {
        checkBoxesMap.forEach((s, l) -> {
            if (controller.getUserAccount().getInventory().containsKey(s)) {
                for (int i = 0; i < controller.getUserAccount().getCurrLevel(s); i++) {
                    l.get(i).setSelected(true);
                }
            }
        });
    }

}
