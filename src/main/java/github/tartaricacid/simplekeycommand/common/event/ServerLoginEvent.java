package github.tartaricacid.simplekeycommand.common.event;

import github.tartaricacid.simplekeycommand.SimpleKeyCommand;
import github.tartaricacid.simplekeycommand.common.config.ConfigInit;
import github.tartaricacid.simplekeycommand.common.network.ConfigMessage;
import github.tartaricacid.simplekeycommand.common.network.ConfigPackHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Mod.EventBusSubscriber(modid = SimpleKeyCommand.MOD_ID)
public class ServerLoginEvent {
    @SideOnly(Side.SERVER)
    @SubscribeEvent
    public static void serverLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        try {
            // 将配置文件读取成字符串
            String configString = new String(Files.readAllBytes(Paths.get(ConfigInit.configJsonFile.getPath())), StandardCharsets.UTF_8);
            // 向登陆的指定玩家发包
            ConfigPackHandler.INSTANCE.sendTo(new ConfigMessage(configString), (EntityPlayerMP) event.player);
        } catch (IOException ioe) {
            event.player.sendStatusMessage(new TextComponentString("Simple Key Command Config Handle Failed."), false);
        }
    }
}
