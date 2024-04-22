package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;


public class PositionPart implements EntityPart {

    private double x, y, rotation;

    public PositionPart(double x, double y, double rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public double getX() {
        return x;
    }

    public void setX(double newX) {
        this.x = newX;
    }

    public double getY() {
        return y;
    }

    public void setY(double newY) {
        this.y = newY;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setPosition(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    @Override
    public void process(GameData gameData, Entity entity, World world) {
    }
}