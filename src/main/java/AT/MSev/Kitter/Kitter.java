package AT.MSev.Kitter;

import AT.MSev.Kitter.Commands.*;
import AT.MSev.Kitter.Items.ItemCurrency;
import AT.MSev.Kitter.Items.ItemKit;
import AT.MSev.Mango_Core.Mango_Core;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Kitter extends JavaPlugin {
    public static NamespacedKey key;
    public static FileConfiguration config;
    public static File folder;
    @Override
    public void onEnable() {
        key = new NamespacedKey(this, this.getDescription().getName());

        folder = getDataFolder();

        new ItemCurrency();
        new ItemKit();

        this.getCommand("kitteradd").setExecutor(new CommandKitAdd());
        this.getCommand("kittercreate").setExecutor(new CommandKitCreate());
        this.getCommand("kitterremoveitem").setExecutor(new CommandKitItemRemove());
        this.getCommand("kittersee").setExecutor(new CommandKitPrint());
        this.getCommand("kitterremove").setExecutor(new CommandKitRemove());
        this.getCommand("kittergive").setExecutor(new CommandKitGive());

        saveDefaultConfig();
        config = getConfig();
        Kit.LoadFromFile();

        getServer().getPluginManager().registerEvents(new Handler(), this);
    }
    @Override
    public void onDisable() {

    }

    static {
        ConfigurationSerialization.registerClass(Kit.class, "Kit");
    }
}
