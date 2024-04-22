package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * Method to process the game entities
     *
     * @param gameData The game data
     * @param world    The game world
     */
    void process(GameData gameData, World world);
}
