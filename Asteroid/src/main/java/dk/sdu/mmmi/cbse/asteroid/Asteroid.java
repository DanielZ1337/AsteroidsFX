package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commoncollision.ICollidable;

/**
 *
 * @author corfixen
 */
public class Asteroid extends Entity implements ICollidable {

    @Override
    public void handleCollision(EntityType type) {
        LifePart lifePart = this.getPart(LifePart.class);
        switch (type) {
            case PLAYER_BULLET -> lifePart.setIsHit(true);
        }
    }
}
