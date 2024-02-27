package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.EntityType;

public class AsteroidControlSystem implements IEntityProcessingService {

    private final int TRAVEL_SPEED = 2;
    private final int AREA_DISTANCE_FROM_BORDER = 100;
    private final int ASTEROID_SIZE_VARIATION = 4;
    private final int SPAWN_INTERVAL = 200;
    private static long lastSpawn = 0;
    private static int MAX_ASTEROIDS = 10;
    private final EntityType[] asteroidEntityType = new EntityType[]{EntityType.PLAYER};
    @Override
    public void process(GameData gameData, World world) {

        if (System.currentTimeMillis() - lastSpawn > SPAWN_INTERVAL && world.getEntities(Asteroid.class).size() < MAX_ASTEROIDS){
            lastSpawn = System.currentTimeMillis();
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
            asteroid.setX(asteroid.getX() + changeX * TRAVEL_SPEED);
            asteroid.setY(asteroid.getY() + changeY * TRAVEL_SPEED);

            int displayWidth = gameData.getDisplayWidth();
            int displayHeight = gameData.getDisplayHeight();

            if (asteroid.getX() < -AREA_DISTANCE_FROM_BORDER) { 
                world.removeEntity(asteroid);
            }

            if (asteroid.getX() > displayWidth + AREA_DISTANCE_FROM_BORDER) {
                world.removeEntity(asteroid);
            }

            if (asteroid.getY() < -AREA_DISTANCE_FROM_BORDER) {
                world.removeEntity(asteroid);
            }

            if (asteroid.getY() > displayHeight + AREA_DISTANCE_FROM_BORDER) {
                world.removeEntity(asteroid);
            }
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Asteroid asteroid = new Asteroid();
        asteroid.setType(EntityType.ASTEROID);
        asteroid.setCollidableWith(asteroidEntityType);
        setShape(asteroid);
        // Randomly spawn asteroid on the border outside the screen
        int displayWidth = gameData.getDisplayWidth();
        int displayHeight = gameData.getDisplayHeight();

        int x = (int) (Math.random() * displayWidth);

        int y = (int) (Math.random() * displayHeight);

        if (x < displayWidth / 2) {
            x = -AREA_DISTANCE_FROM_BORDER;
        } else {
            x = displayWidth + AREA_DISTANCE_FROM_BORDER;
        }

        if (y < displayHeight / 2) {
            y = -AREA_DISTANCE_FROM_BORDER;
        } else {
            y = displayHeight + AREA_DISTANCE_FROM_BORDER;
        }

        asteroid.setX(x);
        asteroid.setY(y);

        float rotation_from_spawn_to_center = (float) Math.toDegrees(Math.atan2(gameData.getDisplayHeight() / (Math.random()*2) - y, gameData.getDisplayWidth() / (Math.random()*2) - x));
        asteroid.setRotation(rotation_from_spawn_to_center);

        return asteroid;
    }

    private void setShape(Entity entity) {
        double[] standardShape = new double[]{-5,-5,10,0,-5,5};
        double[] shape = new double[standardShape.length];
        for (int i = 0; i < standardShape.length; i++) {
            shape[i] = standardShape[i] * (Math.random() * ASTEROID_SIZE_VARIATION);
        }
        entity.setPolygonCoordinates(shape);
    }

}
