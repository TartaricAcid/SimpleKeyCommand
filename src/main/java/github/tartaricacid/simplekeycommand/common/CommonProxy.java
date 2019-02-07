package github.tartaricacid.simplekeycommand.common;

import github.tartaricacid.simplekeycommand.SimpleKeyCommand;
import github.tartaricacid.simplekeycommand.common.command.ReloadCommand;
import github.tartaricacid.simplekeycommand.common.network.ConfigDeserialization;
import github.tartaricacid.simplekeycommand.common.network.ConfigPOJO;
import github.tartaricacid.simplekeycommand.common.network.ConfigPackHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CommonProxy {
    // 全局静态字段，存储配置文件
    public static List<ConfigPOJO> configJson = new ArrayList<>();
    // 创建配置 JSON 文件对象
    private static File configFolder = new File("." + File.separator + "config" + File.separator + "simpleKeyCommand");
    public static File configJsonFile = new File(configFolder.getPath() + File.separator + "key_config.json");

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        ConfigPackHandler.registerMessages(SimpleKeyCommand.MOD_ID);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        createKeyConfigJson();
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new ReloadCommand());
    }

    /**
     * 生成文件，并反序列化
     */
    private void createKeyConfigJson() {
        // 利用逻辑判定的短路原理进行相关文件夹生成
        if (!configFolder.exists() && configFolder.mkdirs()) {
            SimpleKeyCommand.logger.info("Config Folder Created!");
        }

        try {
            // 利用逻辑判定的短路原理进行相关文件生成
            if (!configJsonFile.isFile() && configJsonFile.createNewFile()) {
                SimpleKeyCommand.logger.info("Config File Created!");

                // 因为我不知道怎么初始化配置文件，只能这样了
                // 避免网络 IO 阻塞主线程，新建线程
                new Thread(() -> {
                    try {
                        URL url = new URL("http://downloader.meitangdehulu.com/key_config.json");
                        FileUtils.writeLines(configJsonFile, IOUtils.readLines(url.openStream(), StandardCharsets.UTF_8));
                    } catch (Throwable ex) {
                        SimpleKeyCommand.logger.warn("Can't Download Config File: ", ex);
                    }
                }, "CONFIG_DOWNLOADING_THREAD").start();
            }
        } catch (IOException ioe) {
            SimpleKeyCommand.logger.warn(ioe.getMessage());
        }

        // 反序列化配置文件
        ConfigDeserialization.deserialization();
    }
}
