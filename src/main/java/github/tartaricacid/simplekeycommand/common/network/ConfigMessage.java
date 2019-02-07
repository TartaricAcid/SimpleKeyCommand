package github.tartaricacid.simplekeycommand.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ConfigMessage implements IMessage {
    private String configString;

    /**
     * 按照文档说明，此默认构造方法必须存在，否则初始化会崩溃
     */
    public ConfigMessage() {
    }

    /**
     * 构造需要发送的信息
     *
     * @param configString 需要发送同步的配置文件字符串
     */
    public ConfigMessage(String configString) {
        this.configString = configString;
    }

    /**
     * 将 ByteBuf 中二进制数据读取到 configString 中<br>
     * 此方法会在 {@code ConfigMessageHandler.onMessage} 方法调用时自动调用
     */
    @Override
    public void fromBytes(ByteBuf buf) {
        configString = ByteBufUtils.readUTF8String(buf);
    }

    /**
     * 将 configString 处理成 ByteBuf 二进制数据<br>
     * 此方法会在发包时自动调用
     */
    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, configString);
    }

    public String getConfigString() {
        return configString;
    }
}
