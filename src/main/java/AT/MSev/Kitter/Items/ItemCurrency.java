package AT.MSev.Kitter.Items;

import AT.MSev.Mango_Core.Items.Interactable.ItemInteractable;
import AT.MSev.Mango_Core.Items.ItemBase;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Utils.NBTManager;
import net.ess3.api.Economy;
import net.minecraft.server.v1_12_R1.NBTTagDouble;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;

public class ItemCurrency extends ItemInteractable {

    public ItemCurrency() {
        super("Currency", Material.PAPER);
    }

    @Override
    protected Boolean OnRightClick(PlayerInteractEvent e) {
        Boolean res = super.OnRightClick(e);
        if(res)
        {
            double curval = ((NBTTagDouble)NBTManager.GetTag(e.getItem(), "curval")).asDouble();
            try {
                Economy.setMoney(e.getPlayer().getName(), Economy.getMoneyExact(e.getPlayer().getName()).add(new BigDecimal(curval)));
                e.getPlayer().sendMessage(ChatColor.GREEN + "Added " + curval + " to balance");
                e.getItem().setAmount(e.getItem().getAmount()-1);
            } catch(Exception ex) {MangoUtils.Log(ex.getMessage());
            }
        }
        return res;
    }

    public static ItemStack FromValue(double v)
    {
        ItemStack ret = NBTManager.AddItemNBT(ItemBase.Get("Currency").Physical, "curval", new NBTTagDouble(v) );
        MangoUtils.ItemRename(ret, "$" + v);
        return ret;
    }
}
