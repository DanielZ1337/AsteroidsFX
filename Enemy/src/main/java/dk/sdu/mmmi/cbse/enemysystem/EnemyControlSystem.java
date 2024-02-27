package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemyControlSystem implements IEntityProcessingService {

    private final int ROTATION_SPEED = 5;
    private final int TIME_BETWEEN_SHOTS = 300;
    private static long lastShot = 0;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            // true -> forward, false -> stand still
            boolean random_movement_forward = Math.random() < 0.77;
            if (random_movement_forward) {
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX);
                enemy.setY(enemy.getY() + changeY);
            }

            // true -> left, false -> right
            boolean random_rotation_direction = Math.random() < 0.5;
            boolean shouldRotate = Math.random() * Math.random() * Math.random() < 0.5;
            if (shouldRotate) {
                if (random_rotation_direction) {
                    enemy.setRotation(enemy.getRotation() - ROTATION_SPEED);
                } else {
                    enemy.setRotation(enemy.getRotation() + ROTATION_SPEED);
                }
            }

            // true -> shoot, false -> don't shoot
            boolean random_shoot = Math.random() < 0.5;
            if (random_shoot) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastShot > TIME_BETWEEN_SHOTS) {
                    for (BulletSPI bulletSPI : getBulletSPIs()) {
                        Entity bullet = bulletSPI.createBullet(enemy, gameData, enemy.getCollidableWith());
                        world.addEntity(bullet);
                    }
                    lastShot = currentTime;
                }
            }

            if (enemy.getX() < 0) {
                enemy.setX(1);
                enemy.setRotation(enemy.getRotation() + 180);
            }

            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(gameData.getDisplayWidth() - 1);
                enemy.setRotation(enemy.getRotation() + 180);
            }

            if (enemy.getY() < 0) {
                enemy.setY(1);
                enemy.setRotation(enemy.getRotation() + 180);
            }

            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(gameData.getDisplayHeight() - 1);
                enemy.setRotation(enemy.getRotation() + 180);
            }


        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
