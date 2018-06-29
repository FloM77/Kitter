package AT.MSev.Kitter.Commands;

import AT.MSev.Kitter.Kit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandKitItemRemove implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        Kit affect = Kit.FromTag(strings[0]);
        if(strings.length<2)
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
            try {
                int id = Integer.parseInt(strings[1]);

                ItemStack tR = affect.items.get(id);
                affect.RemoveItem(tR);
                p.sendMessage(ChatColor.GREEN+ "Removed " + strings[1] + " from " + strings[0]);
            } catch(Exception e) {
                p.sendMessage(ChatColor.RED  + "Id " + strings[1] + " does not exist in kit " + strings[0] + ". Make sure it is between 0 and " + (affect.items.size()-1));
            }
            return true;
        }
        return false;
    }
}
