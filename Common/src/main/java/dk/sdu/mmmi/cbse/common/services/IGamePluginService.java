package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;


public interface IGamePluginService {
    /**
     * Method to start the game and initialize plugins
     *
     * @param gameData The game data
     * @param world    The game world
     */
    void start(GameData gameData, World world);

    /**
     * Method to stop the game and stop plugins
     *
     * @param gameData The game data
     * @param world    The game world
     */
    void stop(GameData gameData, World world);
}
