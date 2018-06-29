package AT.MSev.Kitter.Commands;

import AT.MSev.Kitter.Items.ItemCurrency;
import AT.MSev.Kitter.Items.ItemKit;
import AT.MSev.Kitter.Kit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandKitGive implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        Kit affect = Kit.FromTag(strings[1]);
        Player g = null;
        if(strings.length<2)
        {
            p.sendMessage("Wrong command usage");
            return false;
        }
        if((g = Bukkit.getPlayer(strings[0])) == null)
        {
            p.sendMessage(ChatColor.RED + "Player " + strings[0] + " not found");
            return true;
        }
        try {
            double val = Double.parseDouble(strings[1]);
            g.getInventory().addItem(ItemCurrency.FromValue(val));
            return true;
        }
        catch (Exception ex) {
            if (affect == null) {
                p.sendMessage(ChatColor.RED + "Kit " + strings[1] + " not found");
                return true;
            }

            g.getInventory().addItem(ItemKit.FromTag(affect.GetTag()));
            p.sendMessage(ChatColor.GREEN + "Gave " + affect.GetTag() + " to " + p.getDisplayName());

            return true;
        }
    }
}
