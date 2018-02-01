package com.almasb.fxglgames.drop.BasicGameApp;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.entity.control.KeepOnScreenControl;
import com.almasb.fxgl.entity.view.ScrollingBackgroundView;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.Map;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.stage.Stage;


public class BasicGameApp extends GameApplication {

    private PlayerControl playerControl;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setVersion(String.valueOf(1.0));
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("SwimmingMan");

    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Swim Up") {
            @Override
            protected void onActionBegin() {
                playerControl.swimUp();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Swim Down") {
            @Override
            protected void onActionBegin() {
                playerControl.swimDown();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Swim Forward") {
            @Override
            protected void onActionBegin() {
                playerControl.swimForward();
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Swim Backward") {
            @Override
            protected void onActionBegin() {
                playerControl.swimBackward();
            }
        }, KeyCode.A);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("stageColor", Color.BLACK);
        vars.put("score", 0);
    }


    @Override
    protected void initGame() {
        getGameScene().addGameView(new ScrollingBackgroundView(getAssetLoader().loadTexture("SeamlessUnderwaterBackground.jpg", 800, 600), Orientation.HORIZONTAL));
        //initBackground();
        initPlayer();

    }


    private boolean requestNewGame = false;

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.TOPWALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                requestNewGame = true;
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.BOTTOMWALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                requestNewGame = true;
            }
        });


    }


    @Override
    protected void initUI() {
        Text uiScore = getUIFactory().newText("", 48);
        uiScore.setTranslateX(getWidth() - 120);
        uiScore.setTranslateY(50);
        uiScore.fillProperty().bind(getGameState().objectProperty("stageColor"));
        uiScore.textProperty().bind(getGameState().intProperty("score").asString());


        getGameScene().addUINode(uiScore);

    }

    @Override
    protected void onUpdate(double tpf) {
        getGameState().increment("score", 1);
    }

    @Override
    protected void onPostUpdate(double tpf) {
        if (getGameState().getInt("score") == 30000) {
            showGameOver();
        }
        if (requestNewGame) {
            requestNewGame = false;
            startNewGame();
        }
    }

    //BackgroundImage myBI = new BackgroundImage(new Image("BackgroundUnderwater.jpg", 800, 600, false, true),
            //BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);


    /*private void initBackground() {
        Entity bg = Entities.builder()
                .viewFromTexture("BackgroundUnderwater.jpg")
                .type(EntityType.BACKGROUND)
                .buildAndAttach();

        bg.xProperty().bind(getGameScene().getViewport().xProperty());
        bg.yProperty().bind(getGameScene().getViewport().xProperty());
    }
    */


    private void initPlayer() {

        playerControl = new PlayerControl();

        Texture view = getAssetLoader().loadTexture("diver-1.gif");

        Entity player = Entities.builder()
                .at(200, 300)
                .type(EntityType.PLAYER)
                .bbox(new HitBox("BODY", BoundingShape.box(100, 50)))
                .viewFromNode(view)
                .with(new CollidableComponent(true))
                .with(new KeepOnScreenControl(true, true))
                .with(playerControl, new WallBuildingControl())
                .buildAndAttach();
        player.setScaleX(0.45);
        player.setScaleY(0.45);

        getGameScene().getViewport().setBounds(10, 10, Integer.MAX_VALUE, getHeight());
        getGameScene().getViewport().bindToEntity(player, getWidth() / 10, getHeight() / 2);
    }


    private void showGameOver() {
        getDisplay().showMessageBox("Game Over. Thank you for playing!", this::exit);
    }


    public static void main(String[] args) {
        launch(args);
    }

    /*@Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        root.setId("pane");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("BackGround.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
}
