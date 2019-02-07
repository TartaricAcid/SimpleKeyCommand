package github.tartaricacid.simplekeycommand;

import github.tartaricacid.simplekeycommand.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = SimpleKeyCommand.MOD_ID, name = SimpleKeyCommand.MOD_NAME,
        version = SimpleKeyCommand.VERSION, acceptedMinecraftVersions = "[1.12]")
public class SimpleKeyCommand {
    public static final String MOD_ID = "simplekeycommand";
    public static final String MOD_NAME = "SimpleKeyCommand";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MOD_ID)
    public static SimpleKeyCommand INSTANCE;

    public static Logger logger;

    @SidedProxy(serverSide = "github.tartaricacid.simplekeycommand.common.CommonProxy",
            clientSide = "github.tartaricacid.simplekeycommand.client.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preinit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postinit(event);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        proxy.serverLoad(event);
    }
}
