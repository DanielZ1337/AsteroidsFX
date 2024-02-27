import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
    exports dk.sdu.mmmi.cbse.asteroid;
    requires Common;
    
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroid.AsteroidPlugin;

}