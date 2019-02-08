package github.tartaricacid.simplekeycommand.common.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import github.tartaricacid.simplekeycommand.SimpleKeyCommand;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigInit {
    // 全局静态字段，存储配置文件
    public static List<ConfigPOJO> configJson = new ArrayList<>();
    // 创建配置 JSON 文件对象
    private static File configFolder = new File("." + File.separator + "config" + File.separator + "simpleKeyCommand");
    public static File configJsonFile = new File(configFolder.getPath() + File.separator + "key_config.json");

    /**
     * 生成文件，并反序列化
     */
    public static void createKeyConfigJson() {
        // 利用逻辑判定的短路原理进行相关文件夹生成
        if (!configFolder.exists() && configFolder.mkdirs()) {
            SimpleKeyCommand.logger.info("Config Folder Created!");
        }

        try {
            // 利用逻辑判定的短路原理进行相关文件生成
            if (!configJsonFile.isFile() && configJsonFile.createNewFile()) {
                SimpleKeyCommand.logger.info("Config File Created!");

                // 构建 GsonBuilder，并对其进行格式化
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                // 从 init 处获取得到初始化好的数据
                configJson = init();

                // 写成配置文件
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(configJsonFile), StandardCharsets.UTF_8);
                out.write(gson.toJson(configJson));
                out.close();
            } else {
                // 反序列化配置文件
                ConfigDeserialization.deserialization();
            }
        } catch (IOException ioe) {
            SimpleKeyCommand.logger.warn(ioe.getMessage());
        }
    }

    /**
     * List<ConfigPOJO> 的初始化
     *
     * @return 初始化好的 List<ConfigPOJO>
     */
    private static List<ConfigPOJO> init() {
        List<ConfigPOJO> initConfig = new ArrayList<>();
        ConfigPOJO configPOJO = new ConfigPOJO();
        ConfigPOJO.Text text = configPOJO.new Text();
        ConfigPOJO.Sound sound = configPOJO.new Sound();

        text.setShow(false);
        text.setHyphen(" -> ");
        text.setColor(0xffffff);
        text.setPos(Arrays.asList(1.0f, 1.0f));

        sound.setPlay(true);
        sound.setName("minecraft:entity.experience_orb.pickup");
        sound.setVolume(1.0f);
        sound.setPitch(1.0f);

        configPOJO.setCommand("这一块输入你需要触发的指令，支持 %player% 占位符");
        configPOJO.setDesc("你可以在此处写下你对键位的详细描述");
        configPOJO.setText(text);
        configPOJO.setSound(sound);

        for (int i = 0; i <= 9; i++) {
            configPOJO.setName("功能键位" + i);
            initConfig.add(configPOJO.clone());
        }

        return initConfig;
    }
}
