package github.tartaricacid.simplekeycommand.common.network;

import github.tartaricacid.simplekeycommand.client.network.ConfigMessageHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ConfigPackHandler {
    private static int packetId = 0;
    public static SimpleNetworkWrapper INSTANCE = null;

    public ConfigPackHandler() {
    }

    /**
     * 此静态方法仅仅为了方便构建不重复的发包 ID
     */
    private static int nextID() {
        return packetId++;
    }

    public static void registerMessages(String channelName) {
        // 通过 NetworkRegistry.INSTANCE.newSimpleChannel 方法创建出 SimpleNetworkWrapper 实例
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);

        // 发包注册
        INSTANCE.registerMessage(ConfigMessageHandler.class, ConfigMessage.class, nextID(), Side.CLIENT);
    }
}
