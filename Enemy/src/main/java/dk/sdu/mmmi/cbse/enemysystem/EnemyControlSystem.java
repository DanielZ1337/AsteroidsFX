package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemyControlSystem implements IEntityProcessingService {

    private static long lastShot = 0;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovePart movePart = enemy.getPart(MovePart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);

            if (lifePart != null) {
                lifePart.process(gameData, enemy, world);
            }

            // true -> forward, false -> stand still
            boolean random_movement_forward = Math.random() < 0.77;
            movePart.setUp(random_movement_forward);

            // true -> left, false -> right
            boolean random_rotation_direction = Math.random() < 0.5;
            boolean shouldRotate = Math.random() * Math.random() * Math.random() < 0.5;
            if (shouldRotate) {
                movePart.setLeft(random_rotation_direction);
                movePart.setRight(!random_rotation_direction);
            }

            // true -> shoot, false -> don't shoot
            boolean random_shoot = Math.random() < 0.5;
            if (random_shoot) {
                long currentTime = System.currentTimeMillis();
                int TIME_BETWEEN_SHOTS = 300;
                if (currentTime - lastShot > TIME_BETWEEN_SHOTS) {
                    getBulletSPIs().stream().findFirst().ifPresent(bulletSPI -> {
                        Bullet bullet = bulletSPI.createBullet(enemy, EntityType.ENEMY_BULLET, gameData);
                        world.addEntity(bullet);
                    });
                    lastShot = currentTime;
                }
            }

            if(positionPart.getX() < 0) {
                positionPart.setX(1);
                positionPart.setRotation(positionPart.getRotation() + 180);
            }

            if(positionPart.getX() > gameData.getDisplayWidth()) {
                positionPart.setX(gameData.getDisplayWidth() - 1);
                positionPart.setRotation(positionPart.getRotation() + 180);
            }

            if(positionPart.getY() < 0) {
                positionPart.setY(1);
                positionPart.setRotation(positionPart.getRotation() + 180);
            }

            if(positionPart.getY() > gameData.getDisplayHeight()) {
                positionPart.setY(gameData.getDisplayHeight() - 1);
                positionPart.setRotation(positionPart.getRotation() + 180);
            }

            positionPart.process(gameData, enemy, world);
            movePart.process(gameData, enemy,world);
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
