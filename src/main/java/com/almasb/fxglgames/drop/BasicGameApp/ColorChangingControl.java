package com.almasb.fxglgames.drop.BasicGameApp;

import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.time.LocalTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ColorChangingControl extends Control {
    private Rectangle view;
    private LocalTimer timer;
    private Duration interval = Duration.seconds(30);

    @Override
    public void onAdded(Entity entity){
        view = (Rectangle) entity.getView().getNodes().get(0);
        timer = FXGL.newLocalTimer();
    }
    @Override
    public void onUpdate(Entity entity, double tpf){
        if (timer.elapsed(interval)){
            Color nextViewColor = ((Color)view.getFill()).invert();
            Color stageColor = nextViewColor.invert();

            view.setFill(nextViewColor);
            FXGL.getApp().getGameState().setValue("stageColor", stageColor);

            timer.capture();
        }
    }

}
