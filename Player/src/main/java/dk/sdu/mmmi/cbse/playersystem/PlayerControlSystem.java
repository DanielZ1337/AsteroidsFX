package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class PlayerControlSystem implements IEntityProcessingService {


    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovePart movePart = player.getPart(MovePart.class);
            LifePart lifePart = player.getPart(LifePart.class);

            if (lifePart != null) {
                lifePart.process(gameData, player, world);
                LifePart lifePart1 = player.getPart(LifePart.class);
                gameData.setLives(lifePart1.getLife());
            }

            movePart.setUp(gameData.getKeys().isDown(GameKeys.UP));
            movePart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            movePart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));

            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                getBulletSPIs().stream().findFirst().ifPresent(bulletSPI -> {
                    Entity bullet = bulletSPI.createBullet(player, EntityType.PLAYER_BULLET, gameData);
                    world.addEntity(bullet);
                });
            }

            if (positionPart.getX() < 0) {
                positionPart.setX(1);
            }

            if (positionPart.getX() > gameData.getDisplayWidth()) {
                positionPart.setX(gameData.getDisplayWidth() - 1);
            }

            if (positionPart.getY() < 0) {
                positionPart.setY(1);
            }

            if (positionPart.getY() > gameData.getDisplayHeight()) {
                positionPart.setY(gameData.getDisplayHeight() - 1);
            }

            positionPart.process(gameData, player, world);
            movePart.process(gameData, player, world);

        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
