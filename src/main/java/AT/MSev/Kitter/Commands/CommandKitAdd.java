package AT.MSev.Kitter.Commands;

import AT.MSev.Kitter.Kit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKitAdd implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        Kit affect = Kit.FromTag(strings[0]);
        if(strings.length<1)
        {
            p.sendMessage("Wrong command usage");
            return false;
        }
        if(affect==null)
        {
            p.sendMessage(ChatColor.RED + "Kit " + strings[0] + " not found");
        }
        else
        {
            affect.AddItem(p.getInventory().getItemInMainHand());
            p.sendMessage(ChatColor.GREEN + "Added " + p.getInventory().getItemInMainHand().getType().toString()
                    + " to " + strings[0]);
            return true;
        }
        return false;
    }
}

