package com.almasb.fxglgames.drop.BasicGameApp;

import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
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
        wall.setArcWidth(50);
        wall.setArcHeight(50);
        wall.fillProperty().bind(FXGL.getApp().getGameState().objectProperty("stageColor"));
        return wall;
    }
    private Rectangle topWallView(double width, double height){
        Rectangle wall = new Rectangle(width, height);
        wall.fillProperty().bind(FXGL.getApp().getGameState().objectProperty("stageColor"));
        return wall;
    }


    private void buildWall() {
        double height = FXGL.getApp().getHeight();
        double distance = height / 4;
        Entity topWall;
        Entity bottomWall;
        for (int i = 1; i <= 20; i++){
            double topHeight = Math.random() * (height - distance);

            topWall = Entities.builder() //Top wall
                    .at(lastWall + i * 300, -300)
                    .type(EntityType.TOPWALL)
                    .viewFromTexture("BoatImage.png")
                    .bbox(new HitBox("BODY", BoundingShape.box(10, 306)))
                    //.viewFromNodeWithBBox(topWallView(50, topHeight))
                    .with(new CollidableComponent(true))
                    .buildAndAttach();
            bottomWall = Entities.builder() //Bottom wall
                    .at(lastWall + i * 300, 0 + topHeight + distance + 50)
                    .type(EntityType.BOTTOMWALL)
                    .bbox(new HitBox("BODY", BoundingShape.box(100, 50)))
                    .viewFromNodeWithBBox(wallView(50, height - distance - topHeight))
                    .with(new CollidableComponent(false))
                    .buildAndAttach();
            topWall.setScaleX(0.25);
            topWall.setScaleY(0.25);
        }
        lastWall += 10 * 500;
    }
}
