package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.EntityType;

/**
 *
 * @author corfixen
 */
public interface BulletSPI {

    Bullet createBullet(Entity shooter, EntityType entityType, GameData gameData);
}
