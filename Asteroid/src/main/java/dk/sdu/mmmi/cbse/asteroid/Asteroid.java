package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.commonasteroid.AsteroidSplitterSPI;
import dk.sdu.mmmi.cbse.commoncollision.ICollidable;

import java.util.Arrays;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

/**
 * @author corfixen
 */
public class Asteroid extends Entity implements ICollidable {

    @Override
    public void handleCollision(EntityType type, GameData gameData, World world) {
        LifePart lifePart = this.getPart(LifePart.class);
        switch (type) {
            case PLAYER_BULLET -> {
                PositionPart positionPart = this.getPart(PositionPart.class);
                double x = positionPart.getX();
                double y = positionPart.getY();
                double rotation = positionPart.getRotation();
                double radius = this.getRadius();
                double[] polygonCoordinates = this.getPolygonCoordinates();
                getAsteroidSplitterSPIs().stream().findFirst().ifPresent(asteroidSplitterSPI -> {
                    Entity[] entities = asteroidSplitterSPI.splitAsteroid(x, y, radius, rotation, polygonCoordinates);

                    if (entities != null) {
                        Arrays.stream(entities).forEach(world::addEntity);
                    }

                    world.removeEntity(this);
                });
                lifePart.setLife(0);
                gameData.setGameScore(gameData.getGameScore() + 1);
            }
        }
    }

    private Collection<? extends AsteroidSplitterSPI> getAsteroidSplitterSPIs() {
        return ServiceLoader.load(AsteroidSplitterSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
