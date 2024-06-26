package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commoncollision.ICollidable;

/**
 * @author Emil
 */
public class Player extends Entity implements ICollidable {

    @Override
    public void handleCollision(EntityType type, GameData gameData, World world) {
        LifePart lifePart = this.getPart(LifePart.class);
        switch (type) {
            case ENEMY, ASTEROID -> lifePart.setLife(0);
            case ENEMY_BULLET -> {
                lifePart.setLife(lifePart.getLife() - 1);
            }
        }
    }

}
