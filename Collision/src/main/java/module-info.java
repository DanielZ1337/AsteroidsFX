import dk.sdu.mmmi.cbse.collisionsystem.CollisionDetectionService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires Asteroid;
    requires Player;
    requires Enemy;
    requires CommonCollision;

    opens dk.sdu.mmmi.cbse.collisionsystem to javafx.graphics;
    provides IPostEntityProcessingService with CollisionDetectionService;
}