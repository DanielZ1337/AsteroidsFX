package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commoncollision.ICollidable;

/**
 * @author corfixen
 */
public class Bullet extends Entity implements ICollidable {

    @Override
    public void handleCollision(EntityType type, GameData gameData, World world) {
        EntityType entityType = this.getType();
        LifePart lifePart = this.getPart(LifePart.class);
        switch (entityType) {
            case PLAYER_BULLET -> {
                switch (type) {
                    case ENEMY -> lifePart.setIsHit(true);
                    case ASTEROID -> lifePart.setLife(0);
                }
            }
            case ENEMY_BULLET -> {
                switch (type) {
                    case PLAYER -> lifePart.setIsHit(true);
                }
            }
        }
    }
}
