package com.almasb.fxglgames.drop.BasicGameApp;

import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.component.CollidableComponent;
import javafx.scene.shape.Rectangle;

public class WallBuildingControl extends Control {

    private double lastWall = 1000;
    private int i;

    @Override
    public void onUpdate(Entity entity, double tpf){
        if (lastWall - entity.getX() < FXGL.getApp().getWidth()){
            buildWall();
        }
    }

    private Rectangle wallView(double width, double height){
        Rectangle wall = new Rectangle(width, height);
        wall.setArcWidth(25);
        wall.setArcHeight(25);
        wall.fillProperty().bind(FXGL.getApp().getGameState().objectProperty("stageColor"));
        return wall;
    }


    private void buildWall() {
        double height = FXGL.getApp().getHeight();
        double distance = height / 2;

        for (int i = 1; i <= 10; i++){
            double topHeight = Math.random() * (height - distance);

            Entities.builder()
                    .at(lastWall + i * 500, 0 -25)
                    .type(EntityType.WALL)
                    .viewFromNodeWithBBox(wallView(50, topHeight))
                    .with(new CollidableComponent(true))
                    .buildAndAttach();
            Entities.builder()
                    .at(lastWall + i * 500, 0 + topHeight + distance + 25)
                    .type(EntityType.WALL)
                    .viewFromNodeWithBBox(wallView(50, height - distance - topHeight))
                    .with(new CollidableComponent(true))
                    .buildAndAttach();

        }
        lastWall += 10 * 500;
    }
}
