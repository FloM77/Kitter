package AT.MSev.Kitter.Commands;

import AT.MSev.Kitter.Kit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CommandKitCreate implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player p = (Player)commandSender;
        if(strings.length<1)
        {
            p.sendMessage("Wrong command usage");
            return false;
        }
        if(Kit.FromTag(strings[0]) == null) {
            Kit.CreateKit(strings[0]);
            p.sendMessage(ChatColor.GREEN + "Created Kit " + strings[0]);
        }
        else
        {
            p.sendMessage(ChatColor.RED + "Kit " + strings[0] + " already exists");
        }
        return false;
    }
}
