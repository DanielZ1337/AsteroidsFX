package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionDetectionServiceTest {

    @Test
    public void testEntitiesCollide() {
        // Create the CollisionDetectionService instance to test
        CollisionDetectionService service = new CollisionDetectionService();
        // Setup world and game data
        World world = new World();
        GameData gameData = new GameData();

        // Create entities
        Entity entity1 = new Entity();
        entity1.setType(EntityType.PLAYER); // Assume PLAYER is a valid type
        entity1.add(new PositionPart(0, 0, 0)); // Entity at origin
        entity1.setRadius(5); // Set a radius of 5

        Entity entity2 = new Entity();
        entity2.setType(EntityType.ENEMY); // Assume ENEMY is a valid type
        entity2.add(new PositionPart(5, 0, 0)); // Entity 5 units right from origin
        entity2.setRadius(5); // Set a radius of 5

        // Add entities to world
        world.addEntity(entity1);
        world.addEntity(entity2);

        // Check collision
        assertTrue(service.doesCollide(entity1, entity2), "Entities should collide");
    }

    @Test
    public void testEntitiesDoNotCollide() {
        // Create the CollisionDetectionService instance to test
        CollisionDetectionService service = new CollisionDetectionService();
        // Setup world and game data
        World world = new World();
        GameData gameData = new GameData();

        // Create entities
        Entity entity1 = new Entity();
        entity1.setType(EntityType.PLAYER); // Assume PLAYER is a valid type
        entity1.add(new PositionPart(0, 0, 0)); // Entity at origin
        entity1.setRadius(5); // Set a radius of 5

        Entity entity2 = new Entity();
        entity2.setType(EntityType.ENEMY); // Assume ENEMY is a valid type
        entity2.add(new PositionPart(11, 0, 0)); // Entity 11 units right from origin
        entity2.setRadius(5); // Set a radius of 5

        // Add entities to world
        world.addEntity(entity1);
        world.addEntity(entity2);

        // Check collision
        assertFalse(service.doesCollide(entity1, entity2), "Entities should not collide");
    }

    @Test
    public void testCollisionWithZeroRadius() {
        CollisionDetectionService service = new CollisionDetectionService();
        World world = new World();

        Entity entity1 = new Entity();
        entity1.add(new PositionPart(0, 0, 0));
        entity1.setRadius(0);

        Entity entity2 = new Entity();
        entity2.add(new PositionPart(1, 0, 0));
        entity2.setRadius(5);

        assertFalse(service.doesCollide(entity1, entity2), "Entity with zero radius should not collide");
    }

    @Test
    public void testDiagonalCollision() {
        CollisionDetectionService service = new CollisionDetectionService();
        World world = new World();

        Entity entity1 = new Entity();
        entity1.add(new PositionPart(0, 0, 0));
        entity1.setRadius(5);

        Entity entity2 = new Entity();
        entity2.add(new PositionPart(3, 4, 0)); // Pythagorean triplet, distance is 5
        entity2.setRadius(5);

        assertTrue(service.doesCollide(entity1, entity2), "Entities should collide diagonally");
    }
}
