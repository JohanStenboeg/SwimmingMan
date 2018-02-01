package com.almasb.fxglgames.drop.BasicGameApp;

import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.component.CollidableComponent;
import java.util.Random;

public class ObstacleBuildingControl extends Control {

    private double lastWall = 500;

    @Override
    public void onUpdate(Entity entity, double tpf){
        if (lastWall - entity.getX() < FXGL.getApp().getWidth()){
            buildWall();
        }
    }

    private void buildWall() {
        double height = FXGL.getApp().getHeight();
        double distance = height / 2;

        //BOAT CODE STARTS
        for (int l = 1; l <= 10; l++) {
            double topHeight = Math.random() * (height - distance);
            Entities.builder() //Top wall
                    .at(lastWall + l * 1500, 0) //(first x = how fast they spawn, 2nd x is how high/low they spawn)
                    .type(EntityType.TOPWALL) //Specifies which wall/obstacle
                    .viewFromTextureWithBBox("BoatImage200px.png") //Specifics what image
                    .with(new CollidableComponent(true))
                    .buildAndAttach();
        }
        //CORAL CODE STARTS
        for (int i = 1; i <= 10; i++) {
            double topHeight = Math.random() * (height - distance);
            Entities.builder()
                    .at(lastWall + i * 800, 450) //(first x = how fast they spawn, 2nd x is how high/low they spawn)
                    .type(EntityType.TOPWALL) //Specifies which wall/obstacle
                    .viewFromTextureWithBBox("coral150px.png") //Specifics what image
                    .with(new CollidableComponent(true))
                    .buildAndAttach();
        }
        //SHARK CODE STARTS
        for (int k = 1; k <= 10; k++) {
            double topHeight = Math.random() * (height - distance);
            Entities.builder()
                    .at(lastWall + k * 1200, 200 + spawnResult())
                    .type(EntityType.MIDDLEOBSTACLE)
                    .viewFromTextureWithBBox("Shark75px.png")
                    .with(new CollidableComponent(true))
                    .buildAndAttach();
        }
        lastWall += 10 * 500;
    }


    private double spawnResult() { //Code for at what y-coordinate the shark spawns at.
        Random sharkHeightSpawnPosition = new Random();
        int lowestValueSpawnPosition = 0;
        int highestValueSpawnPosition = 250;
        int spawnResult = sharkHeightSpawnPosition.nextInt(highestValueSpawnPosition - lowestValueSpawnPosition) + lowestValueSpawnPosition;
        return spawnResult;

    }
}
