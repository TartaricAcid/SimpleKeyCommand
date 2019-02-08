package github.tartaricacid.simplekeycommand.client.network;

import github.tartaricacid.simplekeycommand.common.config.ConfigInit;
import net.minecraft.client.resources.LanguageManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ConfigLanguageUpdate {
    /**
     * 更新客户端键位处的语言文件
     */
    @SideOnly(Side.CLIENT)
    public static void updateLanguageMap() {
        // 为了避免一遍遍写入字符串，干脆用遍历的方式构建字符串
        for (int i = 0; i < ConfigInit.configJson.size(); i++) {
            String key = "key.simple_key_command.number" + i;
            // 将构建好的键值对塞入语言文件中
            LanguageManager.CURRENT_LOCALE.properties.put(key, ConfigInit.configJson.get(i).getName());
        }
    }
}
