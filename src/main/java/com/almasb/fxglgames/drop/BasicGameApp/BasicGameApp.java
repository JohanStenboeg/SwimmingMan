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
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.util.Map;
import com.almasb.fxgl.physics.CollisionHandler;

import javax.imageio.ImageIO;

public class BasicGameApp extends GameApplication {

    private PlayerControl playerControl; //Making a new object from the PlayerControl Method.

    @Override
    protected void initSettings(GameSettings settings) { //Overriding the object, initSettings to make new custom settings.
        settings.setVersion(String.valueOf(2.0)); //Changing system version.
        settings.setWidth(800); //Setting width of game window
        settings.setHeight(600); //Setting height of game window.
        settings.setTitle("SwimmingMan"); //Naming the window in left top corner.
        settings.setMenuEnabled(false); //Enableing or disableing the menu.

    }



    @Override
    protected void initInput() { //Method for inputs to the game
        String urlString = new File("C:\\Users\\johan\\IdeaProjects\\SimpleGameApp\\src\\main\\resources\\assets\\Music\\ActualBackGroundMusicFile.mp3").toURI().toString();//Adding a music file with relative path
        MediaPlayer player = new MediaPlayer(new Media(urlString));//Adds the file to the MediaPlayer object.
        player.play();//Plays the file
        player.setAutoPlay(true);//Starts playing the file on startup.
        player.setCycleCount(1111110);//Specifies how many times it should run.
        //String urlString2 = new File("C:\\Users\\johan\\IdeaProjects\\SimpleGameApp\\src\\main\\resources\\assets\\textures\\DiverIcon.ico").toURI().toString();//Henter icon filensplacering og laver den om til et "uri".


        getInput().addAction(new UserAction("Swim Up") { //Adds a new action
            @Override
            protected void onActionBegin() { //An object where if a key is pressed, do this.
                playerControl.swimDown(); //Will move the player down (See PlayerControl)
            }
        }, KeyCode.SPACE); //Will swim down if "space" is pressed.
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) { //Used for displaying the score and coins.
        vars.put("stageColor", Color.BLACK); //Setting up the score color
        vars.put("score", 0); //Setting up what it is and what value it should start at.
        vars.put("StageColor", Color.BLACK); //Same as above
        vars.put("coins", 0); //Same as above
    }

    @Override
    protected void initGame() {//Init game object, adds objects to the game.
        getGameScene().addGameView(new ScrollingBackgroundView(getAssetLoader().loadTexture("SeamlessUnderwaterBackgroundModified.jpg", 800, 600), Orientation.HORIZONTAL)); //adding a background and loops it.
        initPlayer(); //Adds the player to the game.
    }

    private boolean requestNewGame = false; //If this is called again, it will start a new game. Used later.

    @Override
    protected void initPhysics() { //Handles different physics to the game.
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.TOPWALL) { //Adding a collision handler.
            @Override
            protected void onCollisionBegin(Entity a, Entity b) { //If collision with player and topwall(boat), do this:
                String urlString1 = new File("C:\\Users\\johan\\IdeaProjects\\SimpleGameApp\\src\\main\\resources\\assets\\SoundEffects\\Cartoon Metal Hit Sound Effects (mp3cut.net).wav").toURI().toString(); //add a song.
                MediaPlayer player1 = new MediaPlayer(new Media(urlString1)); //add the sound to the mediaplayer.
                player1.play(); //Play the sound
                requestNewGame = true; //Start new game
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.BOTTOMWALL) { //Exactly as above, just with different entities colliding and different songs.
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {

                String urlString2 = new File("C:\\Users\\johan\\IdeaProjects\\SimpleGameApp\\src\\main\\resources\\assets\\SoundEffects\\ManEatingPlant.wav").toURI().toString();//Finder music filen med Relativ path(så alle kan bruge den).
                MediaPlayer player2 = new MediaPlayer(new Media(urlString2));//Laver filen om til en Mediaplayer
                player2.play();//Tillader den at afspille
                requestNewGame = true;
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.MIDDLEOBSTACLE) {//Exactly as above, just with different entities colliding and different songs.
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {

                String urlString3 = new File("C:\\Users\\johan\\IdeaProjects\\SimpleGameApp\\src\\main\\resources\\assets\\SoundEffects\\Cartoon Bite Sound Effects (mp3cut.net).wav").toURI().toString();//Finder music filen med Relativ path(så alle kan bruge den).
                MediaPlayer player3 = new MediaPlayer(new Media(urlString3));//Laver filen om til en Mediaplayer
                player3.play();//Tillader den at afspille
                requestNewGame = true;
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.COIN) {//Exactly as above, just with different entities colliding and different songs.
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                getGameState().increment("coins", +1); //As the whole idea with coins is to collect them, this adds 1+ to the coin score aswell.
                String urlString3 = new File("C:\\Users\\johan\\IdeaProjects\\SimpleGameApp\\src\\main\\resources\\assets\\SoundEffects\\Super Mario Bros. - Coin Sound Effect (mp3cut.net).wav").toURI().toString();//Finder music filen med Relativ path(så alle kan bruge den).
                MediaPlayer player3 = new MediaPlayer(new Media(urlString3));//Laver filen om til en Mediaplayer
                player3.play();//Tillader den at afspille
                coin.removeFromWorld(); //Since coins dont start a new game and they are collected, this makes them disappear upon collision.
                requestNewGame = false;

            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.MIDDLEOBSTACLE2) {//Exactly as above, just with different entities colliding and different songs.
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                String urlString3 = new File("C:\\Users\\johan\\IdeaProjects\\SimpleGameApp\\src\\main\\resources\\assets\\SoundEffects\\Cartoon Bite Sound Effects (mp3cut.net).wav").toURI().toString();//Finder music filen med Relativ path(så alle kan bruge den).
                MediaPlayer player3 = new MediaPlayer(new Media(urlString3));//Laver filen om til en Mediaplayer
                player3.play();//Tillader den at afspille
                requestNewGame = true;


            }
        });
    }

    @Override
    protected void initUI() {
        //Game Describtion text
        Text text = new Text("Reach 5000 points or collect 20 coins to win"); //Adds a new text.
        text.setTranslateX(15); //Displays where to place it on x
        text.setTranslateY(20); //Displays where to place it on y
        text.setFont(Font.font(20)); //Displays font (how big it is)
        getGameScene().addUINode(text); //adds it to the game
        //Writes coin above the coin counting.
        Text text2 = new Text("Coins:"); //Adds a new text.
        text2.setTranslateX(600);//Displays where to place it on x
        text2.setTranslateY(20);//Displays where to place it on y
        text2.setFont(Font.font(20));//Displays font (how big it is)
        getGameScene().addUINode(text2);//Adds it to the game
        //Writes score above the score counting
        Text text3 = new Text("Score:");//Adds a new text.
        text3.setTranslateX(680);//Displays where to place it on x
        text3.setTranslateY(20);//Displays where to place it on y
        text3.setFont(Font.font(20));//Displays font (how big it is)
        getGameScene().addUINode(text3); //Adds it to the game
        //Shows the score
        Text uiScore = getUIFactory().newText("Score: ", 48);//Adds a new text.
        uiScore.setTranslateX(getWidth() - 120);//Displays where to place it on x
        uiScore.setTranslateY(60);//Displays where to place it on y
        uiScore.fillProperty().bind(getGameState().objectProperty("stageColor")); //Collects data from the InitGameVars object.
        uiScore.textProperty().bind(getGameState().intProperty("score").asString()); //Collects data from the InitGameVars object.
        getGameScene().addUINode(uiScore); //Adds it to the game
        //Shows the ammount of coins collected
        Text uicoins = getUIFactory().newText("Coins: ", 48);//Adds a new text. Including fontsize.
        uicoins.setTranslateX(getWidth() - 200);//Displays where to place it on x
        uicoins.setTranslateY(60);//Displays where to place it on y
        uicoins.fillProperty().bind(getGameState().objectProperty("stageColor")); //Collects data from the InitGameVars object.
        uicoins.textProperty().bind(getGameState().intProperty("coins").asString());//Collects data from the InitGameVars object.
        getGameScene().addUINode(uicoins); //Adds it to the game

    }


    @Override
    protected void onUpdate(double tpf) { //Everything in here will happen each time the game updates (10 times every second or so).
        getGameState().increment("score", +1); //Adds 1 to the score.
    }

    @Override
    protected void onPostUpdate(double tpf) { //After the game updated, it checks for the following.
        if (getGameState().getInt("score") == 5000) { //Is the score == 5000?
            showGameWon(); //If yes show that the game is won and start a new game.
            startNewGame();
        }
        if (getGameState().getInt("coins") == 20) { //Is the ammount of coins collected == 20?
            showGameWon(); //If yes show game won
            startNewGame(); //Start a new game
        }
        if (requestNewGame) { //If something has requested a new game, then start a new game.
            requestNewGame = false;
            startNewGame();
        }
    }

    private void initPlayer() { //The player object properties.

        playerControl = new PlayerControl(); //adds a new object from the playerControl constructor.
        Entity player = Entities.builder() //Using the entity builder.
                .at(200, 300) //The player spawns at this position.
                .type(EntityType.PLAYER) //The entity type (see class EntityType.java)
                .viewFromTextureWithBBox("ResizedDiverGif02.gif") //What picture should be displayed.
                .with(new CollidableComponent(true)) //If its a collideable component or not.
                .with(new KeepOnScreenControl(true, true)) //Can it move out of screen border or no.
                .with(playerControl, new ObstacleBuildingControl()) //As the player moves, obstacles will spawn.
                .buildAndAttach(); //Makes the object and places it in the game.
        getGameScene().getViewport().setBounds(0, 0, Integer.MAX_VALUE, getHeight()); //Setting some game bounds.
        getGameScene().getViewport().bindToEntity(player, getWidth() / 10, getHeight() / 100); //Setting some bounds for the player.
    }

    private void showGameWon() { //Message showing when the game is won.
        getDisplay().showMessageBox("Game Won. Congratulations and thank you for playing!", this::exit);
    }
    public static void main(String[] args) { //The game launches, this is the psvm.
        launch(args);
    }
}
