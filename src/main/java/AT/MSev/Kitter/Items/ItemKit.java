package AT.MSev.Kitter.Items;

import AT.MSev.Kitter.Kit;
import AT.MSev.Mango_Core.Items.Interactable.ItemInteractable;
import AT.MSev.Mango_Core.Items.ItemBase;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Utils.NBTManager;
import net.ess3.api.Economy;
import net.minecraft.server.v1_12_R1.NBTTagDouble;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ItemKit extends ItemInteractable {

    public ItemKit() {
        super("Kit", Material.BOOK_AND_QUILL);
    }

    @Override
    protected Boolean OnRightClick(PlayerInteractEvent e) {
        Boolean res = super.OnRightClick(e);
        if(res)
        {
            String kittag = NBTManager.FromNBTString(((NBTTagString)NBTManager.GetTag(e.getItem(), "kittag")));
            Kit.FromTag(kittag).Give(e.getPlayer());
            e.getPlayer().sendMessage(ChatColor.GREEN + "Used kit " + kittag);
            e.getItem().setAmount(e.getItem().getAmount()-1);
        }
        return res;
    }

    public static ItemStack FromTag(final String v)
    {
        ItemStack ret = NBTManager.AddItemNBT(ItemBase.Get("Kit").Physical, "kittag", new NBTTagString(v) );
        MangoUtils.ItemRename(ret, "Kit " + v);
        MangoUtils.ItemRelore(ret, new ArrayList<String>() {{add("Use /kittersee " + v); add("to see what you will get");}});
        return ret;
    }
}
