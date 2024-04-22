package dk.sdu.mmmi.cbse.commoncollision;

import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface ICollidable {
    void handleCollision(EntityType type, GameData gameData, World world);
}
