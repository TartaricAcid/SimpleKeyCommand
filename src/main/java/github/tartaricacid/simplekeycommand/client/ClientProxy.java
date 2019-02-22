package github.tartaricacid.simplekeycommand.client;

import github.tartaricacid.simplekeycommand.client.command.URLCommand;
import github.tartaricacid.simplekeycommand.client.event.ClientKeyBinding;
import github.tartaricacid.simplekeycommand.client.network.ConfigLanguageUpdate;
import github.tartaricacid.simplekeycommand.common.CommonProxy;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        super.preinit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ClientKeyBinding clientKeyBinding = new ClientKeyBinding();
        clientKeyBinding.register();

        ClientCommandHandler.instance.registerCommand(new URLCommand());
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        super.postinit(event);
        ConfigLanguageUpdate.updateLanguageMap();
    }
}
