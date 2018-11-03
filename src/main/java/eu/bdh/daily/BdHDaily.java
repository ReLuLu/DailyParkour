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

package eu.bdh.daily;

import eu.bdh.daily.daily.commands.Checkpoint;
import eu.bdh.daily.daily.DailyListener;
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

        // den Listener registrieren
        this.getServer().getPluginManager().registerEvents(new DailyListener(), this);

        // die Befehle registrieren
        this.getCommand("dcheck").setExecutor(new Checkpoint(this));

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
