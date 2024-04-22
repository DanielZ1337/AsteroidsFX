package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.commoncollision.ICollidable;

public class CollisionDetectionService implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity e1 : world.getEntities()) {
            for (Entity e2 : world.getEntities()) {
                if (e1.getClass().equals(e2.getClass()) || e1.getID().equals(e2.getID()))
                    continue;

                if (!doesCollide(e1, e2))
                    continue;

                // Handle collision by ICollidable interface
                EntityType e1Type = e1.getType();
                EntityType e2Type = e2.getType();

                if (e1 instanceof ICollidable) {
                    ((ICollidable) e1).handleCollision(e2Type, gameData, world);
                }

                if (e2 instanceof ICollidable) {
                    ((ICollidable) e2).handleCollision(e1Type, gameData, world);
                }
            }
        }
    }

    public boolean doesCollide(Entity entity, Entity entity2) {
        PositionPart ent1PositionPart = entity.getPart(PositionPart.class);
        PositionPart ent2PositionPart = entity2.getPart(PositionPart.class);
        float dx = (float) ent1PositionPart.getX() - (float) ent2PositionPart.getX();
        float dy = (float) ent1PositionPart.getY() - (float) ent2PositionPart.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity.getRadius() + entity2.getRadius());
    }
}
