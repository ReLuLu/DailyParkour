package eu.bdh.daily;

import eu.bdh.daily.util.ConfigHelper;
import org.bukkit.plugin.java.JavaPlugin;

public class BdHDaily extends JavaPlugin {

    private static BdHDaily plugin;

    @Override
    public void onEnable() {
        //Define Plugin to Variable
        plugin = this;
        //Erzeugung der ConfigFiles
        ConfigHelper configHelper = new ConfigHelper(plugin,"lang");
        configHelper.createDefaultConfig();
        //TODO Abspeicherung der Configuration
        configHelper.createYamlFile();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


    //Getter and Setter
    public static BdHDaily getPlugin() {
        return plugin;
    }
}
