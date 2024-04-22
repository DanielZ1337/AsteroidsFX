import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonasteroid.AsteroidSplitterSPI;

module Asteroid {
    uses AsteroidSplitterSPI;
    exports dk.sdu.mmmi.cbse.asteroid;
    requires Common;
    requires CommonCollision;
    requires CommonAsteroid;

    provides AsteroidSplitterSPI with dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroid.AsteroidPlugin;

}