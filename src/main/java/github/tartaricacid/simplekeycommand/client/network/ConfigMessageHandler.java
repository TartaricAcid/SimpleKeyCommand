package github.tartaricacid.simplekeycommand.client.network;

import github.tartaricacid.simplekeycommand.common.config.ConfigDeserialization;
import github.tartaricacid.simplekeycommand.common.config.ConfigInit;
import github.tartaricacid.simplekeycommand.common.network.ConfigMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ConfigMessageHandler implements IMessageHandler<ConfigMessage, IMessage> {
    @Override
    public IMessage onMessage(ConfigMessage message, MessageContext ctx) {
        // 以防万一，再检查一次上下文，确认为客户端
        if (ctx.side == Side.CLIENT) {
            // 将 Netty IO 线程任务添加到客户端主线程任务处，进行处理
            Minecraft.getMinecraft().addScheduledTask(() -> handle(message));
        }
        return null;
    }

    private void handle(ConfigMessage message) {
        // 从服务端获取得到的字符串
        String configString = message.getConfigString();
        try {
            // 写入客户端配置文件
            FileUtils.writeStringToFile(ConfigInit.configJsonFile, configString, StandardCharsets.UTF_8);
            // 客户端序列化
            ConfigDeserialization.deserialization();
            // 客户端更新语言文件
            ConfigLanguageUpdate.updateLanguageMap();
            Minecraft.getMinecraft().player.sendStatusMessage(
                    new TextComponentString("Simple Key Command Config Handle Succeed."), false);
        } catch (IOException ioe) {
            Minecraft.getMinecraft().player.sendStatusMessage(
                    new TextComponentString("Simple Key Command Config Handle Failed."), false);
        }
    }
}
