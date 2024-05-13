module SplitPackage {
    requires Common;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
}