package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.EntityType;

public class AsteroidControlSystem implements IEntityProcessingService {

    private final int AREA_DISTANCE_FROM_BORDER = 100;
    private static long lastSpawn = 0;

    @Override
    public void process(GameData gameData, World world) {

        int SPAWN_INTERVAL = 200;
        int MAX_ASTEROIDS = 10;
        if (System.currentTimeMillis() - lastSpawn > SPAWN_INTERVAL && world.getEntities(Asteroid.class).size() < MAX_ASTEROIDS){
            lastSpawn = System.currentTimeMillis();
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovePart movePart = asteroid.getPart(MovePart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);

            if (lifePart != null) {
                lifePart.process(gameData, asteroid, world);
            }

            movePart.setUp(true);

            int displayWidth = gameData.getDisplayWidth();
            int displayHeight = gameData.getDisplayHeight();

            if (positionPart.getX() < -AREA_DISTANCE_FROM_BORDER) {
                world.removeEntity(asteroid);
            }

            if (positionPart.getX() > displayWidth + AREA_DISTANCE_FROM_BORDER) {
                world.removeEntity(asteroid);
            }

            if (positionPart.getY() < -AREA_DISTANCE_FROM_BORDER) {
                world.removeEntity(asteroid);
            }

            if (positionPart.getY() > displayHeight + AREA_DISTANCE_FROM_BORDER) {
                world.removeEntity(asteroid);
            }

            positionPart.process(gameData, asteroid,world);
            movePart.process(gameData, asteroid,world);
        }
    }

    private Asteroid createAsteroid(GameData gameData) {
        Asteroid asteroid = new Asteroid();
        asteroid.setType(EntityType.ASTEROID);
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

        asteroid.setRadius(16.0f);

        float rotation_from_spawn_to_center = (float) Math.toDegrees(Math.atan2(gameData.getDisplayHeight() / (Math.random()*2) - y, gameData.getDisplayWidth() / (Math.random()*2) - x));

        asteroid.add(new PositionPart(x, y, rotation_from_spawn_to_center));
        int TRAVEL_SPEED = 2;
        asteroid.add(new MovePart(TRAVEL_SPEED));
        asteroid.add(new LifePart(1));

        return asteroid;
    }

    private void setShape(Entity entity) {
        double[] standardShape = new double[]{-5,-5,10,0,-5,5};
        double[] shape = new double[standardShape.length];
        for (int i = 0; i < standardShape.length; i++) {
            int ASTEROID_SIZE_VARIATION = 4;
            shape[i] = standardShape[i] * (Math.random() * ASTEROID_SIZE_VARIATION);
        }
        entity.setPolygonCoordinates(shape);
    }

}
