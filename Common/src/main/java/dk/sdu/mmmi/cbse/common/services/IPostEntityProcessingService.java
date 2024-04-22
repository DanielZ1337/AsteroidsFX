package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * @author jcs
 */
public interface IPostEntityProcessingService {

    /**
     * Method to process the game after the entities have been processed
     *
     * @param gameData The game data
     * @param world    The game world
     */
    void process(GameData gameData, World world);
}
