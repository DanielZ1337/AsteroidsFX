package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import javafx.scene.shape.Polygon;

import java.util.Arrays;

public class CollisionDetectionService implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity e1 : world.getEntities()) {
            for (Entity e2 : world.getEntities()) {
                if (e1.getClass().equals(e2.getClass()) || e1.getID().equals(e2.getID()))
                    continue;

                if (intersects(e1, e2)) {
                    EntityType[] e1CollidesWith = e1.getCollidableWith();
                    EntityType[] e2CollidesWith = e2.getCollidableWith();
                    EntityType e1Type = e1.getType();
                    EntityType e2Type = e2.getType();

                    if (e1CollidesWith != null && e2CollidesWith != null && e1CollidesWith.length > 0
                            && e2CollidesWith.length > 0 && e1Type != null && e2Type != null) {
                        System.out.println(e1Type + " " + e2Type);
                        System.out.println(Arrays.toString(e1CollidesWith) + " " + Arrays.toString(e2CollidesWith));
                        for (EntityType e1Collide : e1CollidesWith) {
                            for (EntityType e2Collide : e2CollidesWith) {
                                if (e1Collide.equals(e2Type) || e2Collide.equals(e1Type)) {
                                    if (e1Type.equals(EntityType.ASTEROID) && e2Type.equals(EntityType.BULLET)) {
                                        gameData.increaseGameScore();
                                        world.removeEntity(e1);
                                        world.removeEntity(e2);
                                    }

                                    if (e1Type.equals(EntityType.BULLET) && e2Type.equals(EntityType.ASTEROID)) {
                                        gameData.increaseGameScore();
                                        world.removeEntity(e1);
                                        world.removeEntity(e2);
                                    }

                                    if (e1Type.equals(EntityType.ASTEROID) && e2Type.equals(EntityType.PLAYER)) {
                                        gameData.decreaseLives();
                                        world.removeEntity(e1);
                                    }

                                    if (e1Type.equals(EntityType.PLAYER) && e2Type.equals(EntityType.ASTEROID)) {
                                        gameData.decreaseLives();
                                        world.removeEntity(e2);
                                    }

                                    if (e1Type.equals(EntityType.ENEMY) && e2Type.equals(EntityType.BULLET)) {
                                        gameData.increaseGameScore();
                                        world.removeEntity(e2);
                                        world.removeEntity(e1);
                                    }

                                    if (e1Type.equals(EntityType.BULLET) && e2Type.equals(EntityType.ENEMY)) {
                                        gameData.increaseGameScore();
                                        world.removeEntity(e1);
                                        world.removeEntity(e2);
                                    }

                                    if (e1Type.equals(EntityType.BULLET) && e2Type.equals(EntityType.PLAYER)) {
                                        gameData.decreaseLives();
                                        world.removeEntity(e1);
                                    }

                                    if (e1Type.equals(EntityType.PLAYER) && e2Type.equals(EntityType.BULLET)) {
                                        gameData.decreaseLives();
                                        world.removeEntity(e2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean intersects(Entity e1, Entity e2) {
        Polygon E1 = new Polygon(e1.getPolygonCoordinates());
        Polygon E2 = new Polygon(e2.getPolygonCoordinates());
        E1.setLayoutX(e1.getX());
        E1.setLayoutY(e1.getY());
        E1.setRotate(e1.getRotation());
        E2.setLayoutX(e2.getX());
        E2.setLayoutY(e2.getY());
        E2.setRotate(e2.getRotation());

        return E1.getBoundsInParent().intersects(E2.getBoundsInParent());
    }
}
