package github.tartaricacid.simplekeycommand.common.command;

import github.tartaricacid.simplekeycommand.common.CommonProxy;
import github.tartaricacid.simplekeycommand.common.network.ConfigMessage;
import github.tartaricacid.simplekeycommand.common.network.ConfigPackHandler;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReloadCommand implements ICommand {
    @Override
    public String getName() {
        return "simplekeycommand";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("skc");
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        List<String> outList = new ArrayList<>();
        if (args.length != 0 && "reload".contains(args[0])) {
            outList.add("reload");
        }
        return outList;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/skc <reload>";
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        // 参数为 reload 时
        if (args.length != 0 && args[0].equals("reload")) {
            try {
                // 将服务端配置文件读取成字符串
                String configString = new String(Files.readAllBytes(Paths.get(CommonProxy.configJsonFile.getPath())), StandardCharsets.UTF_8);
                // 而后发送到服务器所有玩家客户端处
                ConfigPackHandler.INSTANCE.sendToAll(new ConfigMessage(configString));
                sender.sendMessage(new TextComponentString("config file reload success!"));
            } catch (IOException ioe) {
                sender.sendMessage(new TextComponentString("config file reload failed!"));
            }
        } else {
            sender.sendMessage(new TextComponentString("/skc <reload>"));
        }
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}