package github.tartaricacid.simplekeycommand.common;

import github.tartaricacid.simplekeycommand.SimpleKeyCommand;
import github.tartaricacid.simplekeycommand.common.command.ReloadCommand;
import github.tartaricacid.simplekeycommand.common.config.ConfigInit;
import github.tartaricacid.simplekeycommand.common.network.ConfigPackHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        ConfigPackHandler.registerMessages(SimpleKeyCommand.MOD_ID);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ConfigInit.createKeyConfigJson();
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new ReloadCommand());
    }


}
