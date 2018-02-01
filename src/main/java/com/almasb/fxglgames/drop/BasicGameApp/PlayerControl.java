package com.almasb.fxglgames.drop.BasicGameApp;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;

public class PlayerControl extends Control {
    private Vec2 acceleration = new Vec2(3,0);




    @Override
    public void onUpdate(Entity entity, double tpf) {
        acceleration.x += tpf * 0.1;
        acceleration.y += tpf * -10;

        if (acceleration.y < -5)
            acceleration.y = -5;
        if (acceleration.y > 5)
            acceleration.y = 5;
        entity.translate(acceleration.x, acceleration.y);
    }

    public void swimDown(){
        acceleration.addLocal(0,7);
    }
}
