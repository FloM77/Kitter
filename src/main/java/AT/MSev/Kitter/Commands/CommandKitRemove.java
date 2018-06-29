package AT.MSev.Kitter.Commands;

import AT.MSev.Kitter.Kit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKitRemove implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        if(strings.length<1)
        {
            p.sendMessage("Wrong command usage");
            return false;
        }
        Kit affect = Kit.FromTag(strings[0]);
        if(affect==null)
        {
            p.sendMessage(ChatColor.RED + "Kit " + strings[0] + " not found");
        }
        Kit.RemoveKit(affect);
        p.sendMessage(ChatColor.GREEN + "Removed " + strings[0]);
        return true;
    }
}
