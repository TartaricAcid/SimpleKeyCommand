package github.tartaricacid.simplekeycommand.client.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SideOnly(Side.CLIENT)
public class URLCommand extends CommandBase {
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getName() {
        return "surl";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/surl <URL>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (args.length > 0 && sender instanceof EntityPlayer) {
            try {
                URI uri = new URI(args[0]);
                Desktop.getDesktop().browse(uri);
            } catch (URISyntaxException e) {
                sender.sendMessage(new TextComponentString("URL Syntax Is Error!"));
            } catch (IOException e) {
                sender.sendMessage(new TextComponentString("Browser Is Not Found Or Fails To Launch!"));
            } catch (UnsupportedOperationException e) {
                sender.sendMessage(new TextComponentString("Current OS Don't Support Open Browse!"));
            } catch (SecurityException e) {
                sender.sendMessage(new TextComponentString("Security Manager Denies The Open Browse Action!"));
            }
        } else {
            sender.sendMessage(new TextComponentString(this.getUsage(sender)));
        }
    }
}
