package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    private final int BULLET_SPEED = 2;
    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * BULLET_SPEED);
            bullet.setY(bullet.getY() + changeY * BULLET_SPEED);

            if(bullet.getX() < 0) {
                world.removeEntity(bullet);
            }

            if(bullet.getX() > gameData.getDisplayWidth()) {
                world.removeEntity(bullet);
            }

            if(bullet.getY() < 0) {
                world.removeEntity(bullet);
            }

            if(bullet.getY() > gameData.getDisplayHeight()) {
                world.removeEntity(bullet);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();

        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());
        bullet.setRotation(shooter.getRotation());

        setShape(bullet);

        return bullet;
    }

    private void setShape(Entity entity) {
        entity.setPolygonCoordinates(2, 0, 0, 2, -2, 0, 0, -2);
    }

}
