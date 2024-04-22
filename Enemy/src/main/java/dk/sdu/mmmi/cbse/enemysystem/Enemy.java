package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commoncollision.ICollidable;

/**
 * @author Emil
 */
public class Enemy extends Entity implements ICollidable {

    @Override
    public void handleCollision(EntityType type, GameData gameData, World world) {
        LifePart lifePart = this.getPart(LifePart.class);

        switch (type) {
            case PLAYER, PLAYER_BULLET -> lifePart.setLife(0);
        }
    }
}
