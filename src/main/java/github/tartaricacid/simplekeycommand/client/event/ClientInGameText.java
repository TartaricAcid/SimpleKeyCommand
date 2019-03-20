package github.tartaricacid.simplekeycommand.client.event;

import github.tartaricacid.simplekeycommand.SimpleKeyCommand;
import github.tartaricacid.simplekeycommand.common.config.ConfigPOJO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

@Mod.EventBusSubscriber(modid = SimpleKeyCommand.MOD_ID)
public class ClientInGameText {
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void inGameEvent(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;

            for (int i = 0; i < ClientKeyBinding.numberKeyList.size(); i++) {
                ConfigPOJO configPOJO = ClientKeyBinding.getKey(i);
                KeyBinding keyBinding = ClientKeyBinding.numberKeyList.get(i);
                if (configPOJO == null) {
                    return;
                } else if (configPOJO.getText().isShow()) {
                    List<Float> pos = configPOJO.getText().getPos();
                    StringBuilder text = new StringBuilder(configPOJO.getName())
                            .append(configPOJO.getText().getHyphen())
                            .append(Keyboard.getKeyName(keyBinding.getKeyCode()));
                    fontRenderer.drawString(text.toString(), pos.get(0), pos.get(1),
                            configPOJO.getText().getColor(), false);
                }
            }
        }
    }
}
