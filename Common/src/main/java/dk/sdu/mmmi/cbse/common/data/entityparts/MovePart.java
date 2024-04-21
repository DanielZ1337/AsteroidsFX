package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class MovePart implements EntityPart{

    private double moveSpeed;
    private boolean left, right, up;

    public MovePart(double moveSpeed){
        this.moveSpeed = moveSpeed;
    }

    public double getMoveSpeed(){
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed){
        this.moveSpeed = moveSpeed;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    @Override
    public void process(GameData gameData, Entity entity, World world) {
        PositionPart positionPart = entity.getPart(PositionPart.class);

        if (left) {
            positionPart.setRotation(positionPart.getRotation() - moveSpeed);
        }
        if (right) {
            positionPart.setRotation(positionPart.getRotation() + moveSpeed);
        }
        if (up) {
            double changeX = Math.cos(Math.toRadians(positionPart.getRotation()));
            double changeY = Math.sin(Math.toRadians(positionPart.getRotation()));
            positionPart.setX(positionPart.getX() + changeX);
            positionPart.setY(positionPart.getY() + changeY);

        }
    }
}
