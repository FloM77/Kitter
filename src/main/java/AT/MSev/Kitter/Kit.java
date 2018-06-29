package AT.MSev.Kitter;

import AT.MSev.Mango_Core.Mango_Core;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Kit implements ConfigurationSerializable {
    public static ArrayList<Kit> All = new ArrayList<Kit>();
    public ArrayList<ItemStack> items = new ArrayList<ItemStack>();
    String tag = "undefined";

    public void AddItem(ItemStack is)
    {
        items.add(is);
        SaveToFile();
    }

    public void RemoveItem(ItemStack is)
    {
        items.remove(is);
        SaveToFile();
    }

    public String GetTag()
    {
        return tag;
    }

    public static void CreateKit(String tag)
    {
        new Kit(new ArrayList<ItemStack>(), tag);
        SaveToFile();
    }

    public void Give(Player p)
    {
        for(ItemStack is : items)
        {
            if(p.getInventory().firstEmpty()!=-1) {
                p.getInventory().addItem(is);
            }
            else
            {
                p.getWorld().dropItemNaturally(p.getLocation(), is);
            }
        }
    }

    public static void RemoveKit(Kit kit)
    {
        All.remove(kit);
        SaveToFile();
    }

    public static Kit FromTag(String compare)
    {
        for(Kit k : All)
        {
            if(k.GetTag().equalsIgnoreCase(compare))
            {
                return k;
            }
        }
        return null;
    }

    static void SaveToFile()
    {
        Kitter.config.set("kits", All);
        try {
            Kitter.config.save(new File(Kitter.folder,"config.yml"));
        } catch(Exception ex) {
            MangoUtils.Log(ex.getMessage());
        }
    }

    static Boolean LoadFromFile()
    {
        ArrayList<Kit> temp = (ArrayList<Kit>) Kitter.config.get("kits");
        if(temp!=null)
        All = temp;
        return temp == null;
    }

    public Kit(ArrayList<ItemStack> items, String tag)
    {
        this.items = items;
        this.tag = tag;
        All.add(this);
    }

    public Map<String, Object> serialize() {
        LinkedHashMap ret = new LinkedHashMap();
        ret.put("items", items);
        ret.put("tag", tag);
        return ret;
    }

    public static Kit deserialize(Map<String, Object> from)
    {
        ArrayList<ItemStack> v1 = (ArrayList<ItemStack>) from.get("items");
        String v2 = (String) from.get("tag");
        return new Kit(v1, v2);
    }
}
