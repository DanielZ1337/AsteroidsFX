package dk.sdu.mmmi.cbse.commoncollision;

import dk.sdu.mmmi.cbse.common.data.EntityType;

public interface ICollidable {
    void handleCollision(EntityType type);
}
