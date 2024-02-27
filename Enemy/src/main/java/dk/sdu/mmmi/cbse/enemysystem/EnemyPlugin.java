package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.data.EntityType;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;
    private EntityType[] enemyEntityType = new EntityType[]{EntityType.PLAYER};

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData) {

        Enemy enemyShip = new Enemy();
        enemyShip.setType(EntityType.ENEMY);
        enemyShip.setCollidableWith(enemyEntityType);
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);

        //randomize position
        enemyShip.setX((float) Math.random() * gameData.getDisplayWidth());
        enemyShip.setY((float) Math.random() * gameData.getDisplayHeight());
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}
