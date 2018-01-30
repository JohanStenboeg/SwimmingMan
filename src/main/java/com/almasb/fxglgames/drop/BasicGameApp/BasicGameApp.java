package com.almasb.fxglgames.drop.BasicGameApp;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Map;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;

import static javafx.scene.input.KeyCode.SPACE;

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
    protected void initInput(){
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
    protected void initGameVars(Map<String, Object> vars){
        vars.put("stageColor", Color.BLACK);
        vars.put("score", 0);
    }




    @Override
    protected void initGame(){
        initBackground();
        initPlayer();

    }

    private boolean requestNewGame = false;

    @Override
    protected void initPhysics() {
            getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.WALL) {
                @Override
                protected void onCollisionBegin(Entity a, Entity b) {
                    requestNewGame = true;
                }
            });

        }

    @Override
    protected void initUI(){
        Text uiScore = getUIFactory().newText("", 48);
        uiScore.setTranslateX(getWidth() - 120);
        uiScore.setTranslateY(50);
        uiScore.fillProperty().bind(getGameState().objectProperty("stageColor"));
        uiScore.textProperty().bind(getGameState().intProperty("score").asString());


        getGameScene().addUINode(uiScore);

    }

    @Override
    protected void onUpdate(double tpf){
        getGameState().increment("score", 1);
    }

    @Override
    protected void onPostUpdate(double tpf){
        if(getGameState().getInt("score") == 300){
            showGameOver();
        }
        if (requestNewGame){
            requestNewGame = true;
            startNewGame();
        }
    }

    private void initBackground(){
        Entity bg = Entities.builder()
                .viewFromTextureWithBBox("BackgroundUnderwater.jpg")
                .type(EntityType.BACKGROUND)
                .viewFromNode(new Rectangle(getWidth(), getHeight(), Color.WHITE))
                .with(new ColorChangingControl())
                .buildAndAttach();

        bg.xProperty().bind(getGameScene().getViewport().xProperty());
        bg.yProperty().bind(getGameScene().getViewport().xProperty());
    }
    private void initPlayer(){

        playerControl = new PlayerControl();

        Texture view = getAssetLoader().loadTexture("diver-1.gif");

        Entity player = Entities.builder()
                .at(300, 300)
                .type(EntityType.PLAYER)
                .bbox(new HitBox ("BODY", BoundingShape.box(300, 300)))
                .viewFromNode(view)
                .with(new CollidableComponent(true))
                .with(playerControl, new WallBuildingControl())
                .buildAndAttach();

       getGameScene().getViewport().setBounds(0,0, Integer.MAX_VALUE, getHeight());
       getGameScene().getViewport().bindToEntity(player, getWidth()/1, getHeight()/1);
    }


    private void showGameOver() {
        getDisplay().showMessageBox("Game Over. Thank you for playing!", this::exit);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

