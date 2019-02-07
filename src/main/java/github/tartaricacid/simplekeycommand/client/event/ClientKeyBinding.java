package github.tartaricacid.simplekeycommand.client.event;

import github.tartaricacid.simplekeycommand.SimpleKeyCommand;
import github.tartaricacid.simplekeycommand.common.CommonProxy;
import github.tartaricacid.simplekeycommand.common.network.ConfigPOJO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = SimpleKeyCommand.MOD_ID)
public class ClientKeyBinding {
    private static KeyBinding number0 = new KeyBinding("key.simple_key_command.number0", Keyboard.KEY_NUMPAD0, "key.categories.simple_key_command");
    private static KeyBinding number1 = new KeyBinding("key.simple_key_command.number1", Keyboard.KEY_NUMPAD1, "key.categories.simple_key_command");
    private static KeyBinding number2 = new KeyBinding("key.simple_key_command.number2", Keyboard.KEY_NUMPAD2, "key.categories.simple_key_command");
    private static KeyBinding number3 = new KeyBinding("key.simple_key_command.number3", Keyboard.KEY_NUMPAD3, "key.categories.simple_key_command");
    private static KeyBinding number4 = new KeyBinding("key.simple_key_command.number4", Keyboard.KEY_NUMPAD4, "key.categories.simple_key_command");
    private static KeyBinding number5 = new KeyBinding("key.simple_key_command.number5", Keyboard.KEY_NUMPAD5, "key.categories.simple_key_command");
    private static KeyBinding number6 = new KeyBinding("key.simple_key_command.number6", Keyboard.KEY_NUMPAD6, "key.categories.simple_key_command");
    private static KeyBinding number7 = new KeyBinding("key.simple_key_command.number7", Keyboard.KEY_NUMPAD7, "key.categories.simple_key_command");
    private static KeyBinding number8 = new KeyBinding("key.simple_key_command.number8", Keyboard.KEY_NUMPAD8, "key.categories.simple_key_command");
    private static KeyBinding number9 = new KeyBinding("key.simple_key_command.number9", Keyboard.KEY_NUMPAD9, "key.categories.simple_key_command");

    // 方便后面遍历的 List
    private static List<KeyBinding> numberKeyList = Arrays.asList(number0, number1, number2, number3, number4, number5, number6, number7, number8, number9);

    // 管控是否触发发送信息
    private static boolean isFire = true;

    @SideOnly(Side.CLIENT)
    public void register() {
        for (KeyBinding numberKey : numberKeyList) {
            ClientRegistry.registerKeyBinding(numberKey);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void keyPressEvent(InputEvent.KeyInputEvent e) {
        // 防止摁下后多次触发的判定
        if (!isFire) {
            // 如果所有检查的键位全释放，才进行触发启动
            if (!(Keyboard.isKeyDown(number0.getKeyCode()) || Keyboard.isKeyDown(number1.getKeyCode())
                    || Keyboard.isKeyDown(number2.getKeyCode()) || Keyboard.isKeyDown(number3.getKeyCode())
                    || Keyboard.isKeyDown(number4.getKeyCode()) || Keyboard.isKeyDown(number5.getKeyCode())
                    || Keyboard.isKeyDown(number6.getKeyCode()) || Keyboard.isKeyDown(number7.getKeyCode())
                    || Keyboard.isKeyDown(number8.getKeyCode()) || Keyboard.isKeyDown(number9.getKeyCode()))) {
                isFire = true;
            }

            // 这一处的 return 至关重要，否则就会触发两至三次
            return;
        }

        // 遍历触发
        for (int i = 0; i < numberKeyList.size(); i++) {
            if (numberKeyList.get(i).isKeyDown() && isFire) {
                // 关闭触发指示
                isFire = false;
                // 获取对应的 ConfigPOJO 对象
                ConfigPOJO key = getKey(i);
                if (key != null) {
                    // 占位符的替换
                    String command = key.getCommand().replaceAll("%player%", Minecraft.getMinecraft().player.getName());
                    Minecraft.getMinecraft().player.sendChatMessage(command);
                }
                return;
            }
        }
    }

    /**
     * 通过传入类名，获取对应的 List 元素
     *
     * @param index 传入的按键索引
     * @return ConfigPOJO 对象
     */
    @Nullable
    @SideOnly(Side.CLIENT)
    private static ConfigPOJO getKey(int index) {
        try {
            return CommonProxy.configJson.get(index);
        } catch (IndexOutOfBoundsException iobe) {
            return null;
        }
    }
}
