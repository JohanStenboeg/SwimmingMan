package com.almasb.fxglgames.drop.BasicGameApp;

import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.component.CollidableComponent;
import java.util.Random;

public class ObstacleBuildingControl extends Control {

    //Setting values for the different objects, later used for when they should spawn.
    //The values are together here for easier changes and overview.
    private double boatWall = 500;
    private double coralWall = 500;
    private double sharkWall = 500;
    private double coinwall = 500;
    private double squidWall = 1000;

    @Override
    public void onUpdate(Entity entity, double tpf) { //When the game updates it checks the different if statements.
        //if the reqirements are met, it will use the buildWall() constructor and build the "wall" or obstacle.
        if (boatWall - entity.getX() < FXGL.getApp().getWidth()) {
            buildWall();
        }
        if (coralWall - entity.getX() < FXGL.getApp().getWidth()) {
            buildWall();
        }
        if (sharkWall - entity.getX() < FXGL.getApp().getWidth()) {
            buildWall();
        }
        if (squidWall - entity.getX() < FXGL.getApp().getWidth()) {
            buildWall();
        }
        if (coinwall - entity.getX() < FXGL.getApp().getWidth()) {
            buildWall();
        }
    }
//Class for
    private void buildWall() {
        double height = FXGL.getApp().getHeight(); //Setting the height to be equal to the height of the game window.
        double distance = height / 2;

        //BOAT CODE STARTS
        for (int l = 1; l <= 1; l++) { //Setting up a for loop
            double topHeight = Math.random() * (height - distance);
            Entities.builder() //Building the different objects depending on the entity type specified in the EntityType class.
                    .at(boatWall + l * 50, 0) //(first x = how fast they spawn, 2nd x is how high/low they spawn)
                    .type(EntityType.TOPWALL) //Specifies which wall/obstacle
                    .viewFromTextureWithBBox("BoatImage200px.png") //Specifics what image
                    .with(new CollidableComponent(true))//boolean for if the object should be collidable or not.
                    .buildAndAttach(); //Builds the object and spawns it into the world
        }
        //CORAL CODE STARTS
        for (int i = 1; i <= 2; i++) {//Setting up a for loop
            double topHeight = Math.random() * (height - distance);
            Entities.builder()//Building the different objects depending on the entity type specified in the EntityType class.
                    .at(coralWall + i, 455) //(first x = how fast they spawn, 2nd x is how high/low they spawn)
                    .type(EntityType.BOTTOMWALL) //Specifies which wall/obstacle
                    .viewFromTextureWithBBox("NewUnderwaterPlant.png") //Specifics what image
                    .with(new CollidableComponent(true))//boolean for if the object should be collidable or not.
                    .buildAndAttach();//Builds the object and spawns it into the world
        }
        //SHARK CODE STARTS
        for (int k = 1; k <= 1; k++) {//Setting up a for loop
            double topHeight = Math.random() * (height - distance);
            Entities.builder()//Building the different objects depending on the entity type specified in the EntityType class.
                    .at(sharkWall + k * 250, 50 + spawnResult())//(first x = how fast they spawn, 2nd x is how high/low they spawn)
                    .type(EntityType.MIDDLEOBSTACLE)//Specifies which wall/obstacle
                    .viewFromTextureWithBBox("Shark75px.png")//Specifics what image
                    .with(new CollidableComponent(true))//boolean for if the object should be collidable or not.
                    .buildAndAttach();
        }
        //SQUID CODE STARTS
        for (int k = 1; k <= 1; k++) {//Setting up a for loop
            double topHeight = Math.random() * (height - distance);
            Entities.builder()//Building the different objects depending on the entity type specified in the EntityType class.
                    .at(squidWall + k * 2000, 150 + spawnResult())//(first x = how fast they spawn, 2nd x is how high/low they spawn)
                    .type(EntityType.MIDDLEOBSTACLE2)//Specifies which wall/obstacle
                    .viewFromTextureWithBBox("SquidPurpel100x.png")//Specifics what image
                    .with(new CollidableComponent(true))//boolean for if the object should be collidable or not.
                    .buildAndAttach();//Builds the object and spawns it into the world
        }

        //COIN CODE STARTS
        for (int m = 1; m <= spawnAmmountCoin(); m++) {//Setting up a for loop
            double topHeight = Math.random() * (height - distance);
            Entities.builder() //Building the different objects depending on the entity type specified in the EntityType class.
                    .at((coinwall) + m * 100, spawnResult2())//(first x = how fast they spawn, 2nd x is how high/low they spawn)
                    .type(EntityType.COIN)//Specifies which wall/obstacle
                    .viewFromTextureWithBBox("CoinSpin50px.gif")//Specifics what image
                    .with(new CollidableComponent(true))//boolean for if the object should be collidable or not.
                    .buildAndAttach();//Builds the object and spawns it into the world

        }
        //Variables for coordinates the different items should spawn at
        //By having it like this, its easier to change settings than if it was inside the different item code.
        boatWall += 10 * 200;
        coralWall += 10 * (150 + spawnOccurencyCoral());
        sharkWall += 10 * (100 + spawnOccurencyCoral());
        coinwall += 10 * 125;
        squidWall += 10 * (150 + spawnOccurencyCoral());
    }


    //Random spawn height for shark.
    private double spawnResult() { //Code for at what y-coordinate the shark spawns at.
        Random sharkHeightSpawnPosition = new Random();//Making a new random class.
        int lowestValueSpawnPosition = 25;//Setting the lowest possible value
        int highestValueSpawnPosition = 250;//Setting the highest possible value
        int spawnResult = sharkHeightSpawnPosition.nextInt(highestValueSpawnPosition - lowestValueSpawnPosition) + lowestValueSpawnPosition;//Joining the random class with the higest and lowest possible ammount.
        return spawnResult;//returning the random value.
    }

    //Random spawn height for coin.
    private double spawnResult2() { //Code for at what y-coordinate the shark spawns at.
        Random coinHeightSpawnPosition = new Random();//Making a new random class.
        int lowestValueSpawnPosition2 = 100;//Setting the lowest possible value
        int highestValueSpawnPosition2 = 500;//Setting the highest possible value
        int spawnResult2 = coinHeightSpawnPosition.nextInt(highestValueSpawnPosition2 - lowestValueSpawnPosition2) + lowestValueSpawnPosition2;//Joining the random class with the higest and lowest possible ammount.
        return spawnResult2;//returning the random value.
    }

    private double spawnAmmountCoin() { //Random spawn ammount for coin
        Random coinAmmount = new Random(); //Making a new random class.
        int lowestSpawnValue = 1; //Setting the lowest possible value
        int highestSpawnValue = 5; //Setting the highest possible value
        int spawnAmmountCoin = coinAmmount.nextInt(highestSpawnValue - lowestSpawnValue) + lowestSpawnValue; //Joining the random class with the higest and lowest possible ammount.
        return spawnAmmountCoin; //returning the random value.
    }

    private double spawnOccurencyCoral() {
        Random coralSpawnOccurency = new Random();//Making a new random class.
        int lowestSpawnValue = 25;//Setting the lowest possible value
        int highestSpawnValue = 75;//Setting the highest possible value
        int spawnOccurencyCoral = coralSpawnOccurency.nextInt(highestSpawnValue - lowestSpawnValue) + lowestSpawnValue;//Joining the random class with the higest and lowest possible ammount.
        return spawnOccurencyCoral;//returning the random value.

    }
}
