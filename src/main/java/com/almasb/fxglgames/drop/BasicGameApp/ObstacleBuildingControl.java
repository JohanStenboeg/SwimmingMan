package com.almasb.fxglgames.drop.BasicGameApp;

import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.component.CollidableComponent;
import java.util.Random;

public class ObstacleBuildingControl extends Control {

    private double boatWall = 500;
    private double coralWall = 500;
    private double sharkWall = 500;
    private double coinwall = 500;
    private double squidWall = 500;

    @Override
    public void onUpdate(Entity entity, double tpf) {
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

    private void buildWall() {
        double height = FXGL.getApp().getHeight();
        double distance = height / 2;

        //BOAT CODE STARTS
        for (int l = 1; l <= 1; l++) {
            double topHeight = Math.random() * (height - distance);
            Entities.builder() //Top wall
                    .at(boatWall + l * 50, 0) //(first x = how fast they spawn, 2nd x is how high/low they spawn)
                    .type(EntityType.TOPWALL) //Specifies which wall/obstacle
                    .viewFromTextureWithBBox("BoatImage200px.png") //Specifics what image
                    .with(new CollidableComponent(true))
                    .buildAndAttach();
        }
        //CORAL CODE STARTS
        for (int i = 1; i <= 1; i++) {
            double topHeight = Math.random() * (height - distance);
            Entities.builder()
                    .at(coralWall + i * 0, 455) //(first x = how fast they spawn, 2nd x is how high/low they spawn)
                    .type(EntityType.BOTTOMWALL) //Specifies which wall/obstacle
                    .viewFromTextureWithBBox("NewUnderwaterPlant.png") //Specifics what image
                    .with(new CollidableComponent(true))
                    .buildAndAttach();
        }
        //SHARK CODE STARTS
        for (int k = 1; k <= 1; k++) {
            double topHeight = Math.random() * (height - distance);
            Entities.builder()
                    .at(sharkWall + k * 250, 50 + spawnResult())
                    .type(EntityType.MIDDLEOBSTACLE)
                    .viewFromTextureWithBBox("Shark75px.png")
                    .with(new CollidableComponent(true))
                    .buildAndAttach();
        }
        //SQUID CODE STARTS
        for (int k = 1; k <= 1; k++) {
            double topHeight = Math.random() * (height - distance);
            Entities.builder()
                    .at(squidWall + k * 500, 200 + spawnResult())
                    .type(EntityType.MIDDLEOBSTACLE2)
                    .viewFromTextureWithBBox("SquidPurpel100x.png")
                    .with(new CollidableComponent(true))
                    .buildAndAttach();
        }

        //COIN CODE STARTS
        for (int m = 1; m <= spawnAmmountCoin(); m++) {
            double topHeight = Math.random() * (height - distance);
            Entities.builder()
                    .at((coinwall) + m * 100, spawnResult2())
                    .type(EntityType.COIN)
                    .viewFromTextureWithBBox("CoinSpin50px.gif")
                    .with(new CollidableComponent(true))
                    .buildAndAttach();

        }
        boatWall += 10 * 150;
        coralWall += 10 * (25 + spawnOccurencyCoral());
        sharkWall += 10 * (40 + spawnOccurencyCoral());
        coinwall += 10 * 100;
        squidWall += 10 * (100 + spawnOccurencyCoral());
    }


    //Random spawn height for shark.
    private double spawnResult() { //Code for at what y-coordinate the shark spawns at.
        Random sharkHeightSpawnPosition = new Random();
        int lowestValueSpawnPosition = 0;
        int highestValueSpawnPosition = 250;
        int spawnResult = sharkHeightSpawnPosition.nextInt(highestValueSpawnPosition - lowestValueSpawnPosition) + lowestValueSpawnPosition;
        return spawnResult;
    }

    //Random spawn height for coin.
    private double spawnResult2() { //Code for at what y-coordinate the shark spawns at.
        Random coinHeightSpawnPosition = new Random();
        int lowestValueSpawnPosition2 = 100;
        int highestValueSpawnPosition2 = 500;
        int spawnResult2 = coinHeightSpawnPosition.nextInt(highestValueSpawnPosition2 - lowestValueSpawnPosition2) + lowestValueSpawnPosition2;
        return spawnResult2;
    }

    private double spawnAmmountCoin() {
        Random coinAmmount = new Random();
        int lowestSpawnValue = 0;
        int highestSpawnValue = 5;
        int spawnAmmountCoin = coinAmmount.nextInt(highestSpawnValue - lowestSpawnValue) + lowestSpawnValue;
        return spawnAmmountCoin;
    }

    private double spawnOccurencyCoral() {
        Random coralSpawnOccurency = new Random();
        int lowestSpawnValue = 25;
        int highestSpawnValue = 75;
        int spawnOccurencyCoral = coralSpawnOccurency.nextInt(highestSpawnValue - lowestSpawnValue) + lowestSpawnValue;
        return spawnOccurencyCoral;

    }
}
