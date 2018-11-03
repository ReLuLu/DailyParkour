/*
 * BdHRDaily, a Minecraft daily event plugin
 * Copyright (C) Clive, ReLuLu
 * Copyright (C) daily team and contributors
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package eu.bdh.daily.util;

import com.google.inject.Inject;
import eu.bdh.daily.BdHDaily;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Florian
 * Config Helper zur Anlage der benötigen Config Files
 * Aktuell: Config und Language
 */
public class ConfigHelper {

    private static final Logger log = Logger.getLogger(ConfigHelper.class.getName());
    private String configName;
    private BdHDaily plugin;

    /**
     * Konsturktur zur Erzeugung des ConfigHelper Klasse
     * @param configName Parameter wird benötigt, wenn nicht die Standard Config File sondern eine andere Config File erzeugt werden soll.
     * @param plugin BdHDaily Objekt zum Zugriff auf das Plugin
     */
    @Inject
    public ConfigHelper(BdHDaily plugin, String configName){
        this.configName = configName;
        this.plugin = plugin;
    }

    /**
     * Nur zur Erstellung der Default Config nutzbar.
     * Konsturktur zur Erzeugung des ConfigHelper Klasse
     * @param plugin BdHDaily Objekt zum Zugriff auf das Plugin
     */
    public ConfigHelper(BdHDaily plugin){
        this.plugin = plugin;
        this.configName = "error";
    }

    /**
     * Anlage der default Config File.
     */
    public void createDefaultConfig() {
        File dataFolder = plugin.getDataFolder();
        try {
            if (!dataFolder.exists()) {
                boolean result = dataFolder.mkdirs();
                if (!result){
                    Bukkit.getPluginManager().disablePlugin(plugin);
                    log.log(Level.SEVERE,"Config file not creatable!");
                }
            }
            File file = new File(dataFolder, "config.yml");
            if (!file.exists()) {
                log.info("Config.yml not found, creating!");
                plugin.saveDefaultConfig();
            } else {
                log.info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Anlage eigener Config Files.
     * @return Gibt die FileConfiguration zurück die zum Zugriff auf die Datei benötigt wird.
     */
    public FileConfiguration createYamlFile(){
        File file = new File(plugin.getDataFolder(), configName + ".yml");

        if (!file.exists()) {
            boolean result = file.getParentFile().mkdirs();
            if (!result){
                Bukkit.getPluginManager().disablePlugin(plugin);
                log.log(Level.SEVERE,"Config file not creatable!");
            }
            plugin.saveResource(configName + ".yml", false);
        }

        FileConfiguration fileConfiguration = new YamlConfiguration();

        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return fileConfiguration;
    }
}
