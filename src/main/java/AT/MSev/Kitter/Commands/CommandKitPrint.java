package AT.MSev.Kitter.Commands;

import AT.MSev.Kitter.Kit;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandKitPrint implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        if(strings.length<1)
        {
            p.sendMessage("Kits");
            for(Kit k : Kit.All)
            {
                p.sendMessage(" - " + k.GetTag());
            }
            return true;
        }
        Kit affect = Kit.FromTag(strings[0]);
        if(affect==null)
        {
            p.sendMessage(ChatColor.RED + "Kit " + strings[0] + " not found");
        }
        else
        {
            p.sendMessage("Items for " + affect.GetTag());
            int lC = 0;
            for(ItemStack is : affect.items)
            {
                TextComponent idC = new net.md_5.bungee.api.chat.TextComponent("Id " + lC + "   ");
                TextComponent phC = new TextComponent(ChatColor.LIGHT_PURPLE + "" + is.getType().toString() + "x" + is.getAmount() + "   ");

                BaseComponent[] hoC = new ComponentBuilder(getItemText(is)).create();
                phC.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoC));

                idC.addExtra(phC);

                p.spigot().sendMessage(idC);
                lC++;
            }
            return true;
        }
        return false;
    }

    public static TextComponent getItemText(ItemStack item) {
        return getItemText(org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack.asNMSCopy(item));
    }

    private static TextComponent getItemText(net.minecraft.server.v1_12_R1.ItemStack item) {
        return new TextComponent(item.save(new net.minecraft.server.v1_12_R1.NBTTagCompound()).toString());
    }
}
