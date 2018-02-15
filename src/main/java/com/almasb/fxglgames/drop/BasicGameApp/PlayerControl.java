package com.almasb.fxglgames.drop.BasicGameApp;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;

public class PlayerControl extends Control { //Making a new class.
    private Vec2 acceleration = new Vec2(3, 0); //Making a new vector named acceleration.

    @Override
    public void onUpdate(Entity entity, double tpf) { //When the game updates, the following will happen.

        acceleration.x += tpf * 0.1; //Setting the acceleration for x
        acceleration.y += tpf * -10; //Setting the acceleration for y.
        //When the game updates a new acceleration will be added to the player.
        //making him go faster as the game progress.
        if (acceleration.y < -5)
            acceleration.y = -5;
        if (acceleration.y > 5)
            acceleration.y = 5;
        entity.translate(acceleration.x, acceleration.y);
    }

    public void swimDown() { //Making a new command, to a key, that adds a new acceleration to the player object.
        //As the key only changes the y-coordinate, negating the negative acceleration, the player object will still keep going faster along the x-coordinate.
        //Ultimately making the game faster and harder as time progress.
        acceleration.addLocal(0, 7);
    }
}
