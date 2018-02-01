package com.almasb.fxglgames.drop.BasicGameApp;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;

public class PlayerControl extends Control {
    private Vec2 acceleration = new Vec2(1,-1);




    @Override
    public void onUpdate(Entity entity, double tpf) {
        acceleration.x += tpf * 0.5;
        acceleration.y += tpf * -2;

        if (acceleration.y < -5)
            acceleration.y = -5;
        if (acceleration.y > 5)
            acceleration.y = 5;
        entity.translate(acceleration.x, acceleration.y);
    }
    public void swimUp(){
        acceleration.addLocal(0, -2);
    }
    public void swimForward(){
        acceleration.addLocal(0,0);
    }
    public void swimBackward() {
        acceleration.addLocal(0, 0);
    }
    public void swimDown(){
        acceleration.addLocal(0,2);
    }
}
