package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.EntityType;

/**
 *
 * @author corfixen
 */
public interface BulletSPI {
//    Entity createBullet(Entity e, GameData gameData);

    Entity createBullet(Entity shooter, GameData gameData, EntityType[] collidableWith);
}
