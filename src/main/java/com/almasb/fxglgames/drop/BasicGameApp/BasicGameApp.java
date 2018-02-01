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
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.File;
import java.util.Map;
import com.almasb.fxgl.physics.CollisionHandler;
public class BasicGameApp extends GameApplication {

    private PlayerControl playerControl;

    public void start() {
        start();
    }


    @Override
    protected void initSettings(GameSettings settings) {
        settings.setVersion(String.valueOf(1.0));
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("SwimmingMan");
        settings.setMenuEnabled(false);

    }

    @Override
    protected void initInput() {
        String urlString = new File("C:\\Users\\johan\\IdeaProjects\\SimpleGameApp\\src\\main\\resources\\assets\\Music\\ActualBackGroundMusicFile.mp3").toURI().toString();//Finder music filen med Relativ path(så alle kan bruge den).
        MediaPlayer player = new MediaPlayer(new Media(urlString));//Laver filen om til en Mediaplayer
        player.play();//Tillader den at afspille
        player.setAutoPlay(true);//Afspiller den på startup.
        player.setCycleCount(1111110);//Hvor mange gange sangen skal køre
        String urlString2 = new File("C:\\Users\\johan\\IdeaProjects\\SimpleGameApp\\src\\main\\resources\\assets\\textures\\DiverIcon.ico").toURI().toString();//Henter icon filensplacering og laver den om til et "uri".


        getInput().addAction(new UserAction("Swim Up") {
            @Override
            protected void onActionBegin() {
                playerControl.swimDown();
            }
        }, KeyCode.SPACE);
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
                String urlString1 = new File("C:\\Users\\johan\\IdeaProjects\\SimpleGameApp\\src\\main\\resources\\assets\\SoundEffects\\Cartoon Metal Hit Sound Effects (mp3cut.net).wav").toURI().toString();//Finder music filen med Relativ path(så alle kan bruge den).
                MediaPlayer player1 = new MediaPlayer(new Media(urlString1));//Laver filen om til en Mediaplayer
                player1.play();//Tillader den at afspille
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.BOTTOMWALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                requestNewGame = true;
                String urlString10 = new File("C:\\Users\\johan\\IdeaProjects\\SimpleGameApp\\src\\main\\resources\\assets\\SoundEffects\\ManEatingPlant.wav").toURI().toString();//Finder music filen med Relativ path(så alle kan bruge den).
                MediaPlayer player10 = new MediaPlayer(new Media(urlString10));//Laver filen om til en Mediaplayer
                player10.play();//Tillader den at afspille
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.MIDDLEOBSTACLE) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                requestNewGame = true;
                String urlString3 = new File("C:\\Users\\johan\\IdeaProjects\\SimpleGameApp\\src\\main\\resources\\assets\\SoundEffects\\Cartoon Bite Sound Effects (mp3cut.net).wav").toURI().toString();//Finder music filen med Relativ path(så alle kan bruge den).
                MediaPlayer player3 = new MediaPlayer(new Media(urlString3));//Laver filen om til en Mediaplayer
                player3.play();//Tillader den at afspille
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
        getGameState().increment("score", +1);
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


    private void initPlayer() {

        playerControl = new PlayerControl();
        Entity player = Entities.builder()
                .at(200, 300)
                .type(EntityType.PLAYER)
                .viewFromTextureWithBBox("ResizedDiverGif.gif")
                .with(new CollidableComponent(true))
                .with(new KeepOnScreenControl(true, true))
                .with(playerControl, new ObstacleBuildingControl())
                .buildAndAttach();
        getGameScene().getViewport().setBounds(0, 0, Integer.MAX_VALUE, getHeight());
        getGameScene().getViewport().bindToEntity(player, getWidth() / 10, getHeight() / 100);
    }


    private void showGameOver() {
        getDisplay().showMessageBox("Game Over. Thank you for playing!", this::exit);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
