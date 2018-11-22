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

import com.google.inject.Inject;
import com.google.inject.Injector;
import eu.bdh.daily.daily.DailyListener;
import eu.bdh.daily.daily.DailyLobby;
import eu.bdh.daily.daily.commands.CheckCommand;
import eu.bdh.daily.daily.commands.DailyCommand;
import eu.bdh.daily.database.DatabaseManager;
import eu.bdh.daily.database.HibernateUtil;
import eu.bdh.daily.util.ConfigHelper;
import eu.bdh.daily.util.PluginBinderModule;
import org.bukkit.plugin.java.JavaPlugin;

import eu.bdh.daily.daily.commands.BackCommand;

public class BdHDaily extends JavaPlugin {
    @Inject
    private BdHDaily bdHDaily;

    @Override
    public void onEnable() {
        //Dependency Injection
        PluginBinderModule module = new PluginBinderModule(this);
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        //Erzeugung der ConfigFiles
        ConfigHelper configHelper = new ConfigHelper(bdHDaily,"lang");
        configHelper.createDefaultConfig();
        //TODO Abspeicherung der Configuration
        configHelper.createYamlFile();

        //den Listener registrieren
        this.getServer().getPluginManager().registerEvents(new DailyListener(), this);

        //Lobby erzeugen
        DailyLobby dailyLobby = new DailyLobby(this);

        //die Befehle registrieren
        this.getCommand("daily").setExecutor(new DailyCommand(this));
        this.getCommand("dcheck").setExecutor(new CheckCommand(this));
        this.getCommand("dback").setExecutor(new BackCommand(this, dailyLobby));

        DatabaseManager databaseManager = new DatabaseManager(new HibernateUtil(bdHDaily));
        databaseManager.runDB();

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


    //Getter and Setter
}
