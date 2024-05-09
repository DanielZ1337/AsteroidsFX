package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    private final int BULLET_SPEED = 2;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovePart movePart = bullet.getPart(MovePart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);

            if (lifePart != null) {
                lifePart.process(gameData, bullet, world);
            }

            movePart.setUp(true);

            if (positionPart.getX() < 0) {
                world.removeEntity(bullet);
            }

            if (positionPart.getX() > gameData.getDisplayWidth()) {
                world.removeEntity(bullet);
            }

            if (positionPart.getY() < 0) {
                world.removeEntity(bullet);
            }

            if (positionPart.getY() > gameData.getDisplayHeight()) {
                world.removeEntity(bullet);
            }

            positionPart.process(gameData, bullet, world);
            movePart.process(gameData, bullet, world);
        }
    }

    @Override
    public Bullet createBullet(Entity shooter, EntityType entityType, GameData gameData) {
        Bullet bullet = new Bullet();
        bullet.setType(entityType);
        bullet.setRadius(1f);
        PositionPart shooterPos = shooter.getPart(PositionPart.class);
        bullet.add(new PositionPart(shooterPos.getX(), shooterPos.getY(), shooterPos.getRotation()));
        bullet.add(new MovePart(BULLET_SPEED));
        bullet.add(new LifePart(1));

        setShape(bullet);

        return bullet;
    }

    private void setShape(Entity entity) {
        entity.setPolygonCoordinates(2, 0, 0, 2, -2, 0, 0, -2);
    }

}
