package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * @author corfixen
 */
public interface BulletSPI {

    Entity createBullet(Entity shooter, EntityType entityType, GameData gameData);
}
