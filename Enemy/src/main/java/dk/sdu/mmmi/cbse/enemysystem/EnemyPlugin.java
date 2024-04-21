package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.data.EntityType;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Enemy createEnemyShip(GameData gameData) {

        Enemy enemyShip = new Enemy();
        enemyShip.setType(EntityType.ENEMY);
        enemyShip.setRadius(10);
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);

        double x = (double) gameData.getDisplayWidth() / 2;
        double y = (double) gameData.getDisplayHeight() / 2;

        enemyShip.add(new PositionPart(x, y, 0));

        int ROTATION_SPEED = 5;

        enemyShip.add(new MovePart(ROTATION_SPEED));
        enemyShip.add(new LifePart(3));

        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}
