package github.tartaricacid.simplekeycommand.common.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import github.tartaricacid.simplekeycommand.SimpleKeyCommand;
import github.tartaricacid.simplekeycommand.common.CommonProxy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ConfigDeserialization {
    /**
     * 反序列化配置文件
     */
    public static void deserialization() {
        if (CommonProxy.configJsonFile.exists()) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        new FileInputStream(CommonProxy.configJsonFile), StandardCharsets.UTF_8));
                Gson gson = new Gson();

                // 解析配置文件，存入 CommonProxy 中的 configJson 对象
                CommonProxy.configJson = gson.fromJson(in, new TypeToken<List<ConfigPOJO>>() {
                }.getType());
            } catch (FileNotFoundException fnfe) {
                SimpleKeyCommand.logger.warn("Config File Not Found: " + fnfe);
            }
        }
    }
}
