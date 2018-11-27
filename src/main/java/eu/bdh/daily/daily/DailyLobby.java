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

package eu.bdh.daily.daily;

import eu.bdh.daily.BdHDaily;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * @author René
 * Repräsentiert die Daily-Lobby mit Koordinaten.
 */
public class DailyLobby {

    private BdHDaily plugin;
    private Location spawnLocation;

    /**
     * Konstruktor mit Instanz des Plugins, um auf die Konfiguration zugreifen zu können
     * @param plugin Instanz des Plugins
     */
    public DailyLobby(BdHDaily plugin) {
        this.plugin = plugin;
        // spawnLocation anlegen, geht eigentlich wirklich nur dann gut, wenn man vorher den exakten Weltnamen und die exakten Koordinaten kennt und in die config.yml einträgt, deshalb defaults damit es möglichst keine NPE gibt
        this.spawnLocation = new Location(
                Bukkit.getWorld(plugin.getConfig().getString("world", "world")), // default-Welt "world" just in case der Weltname in der config.yml kann keiner Welt des Servers zugeordnet werden
                Double.parseDouble(plugin.getConfig().getString("lobby.xpos", "0.0")), // default-Koords damit's keine fehlerhafte Location gibt
                Double.parseDouble(plugin.getConfig().getString("lobby.yheight", "80.0")), // Höhe 80 sollte auf einer Normalwelt reichen
                Double.parseDouble(plugin.getConfig().getString("lobby.zpos", "0.0"))
                );

    }

    /**
     * Setzt den Lobby-Spawnpunkt
     * @param loc die neue Spawnlocation
     */
    public void setSpawn(Location loc) {
        this.spawnLocation = loc;
        this.setSpawnConfig(loc);
    }

    /**
     * Speichert die Lobby-Spawnpunktinformation in der config.yml ab
     * @param loc die neue Spawnlocation
     */
    private void setSpawnConfig(Location loc) {
        plugin.getConfig().set("world", loc.getWorld().getName());
        plugin.getConfig().set("lobby.xpos", loc.getX());
        plugin.getConfig().set("lobby.yheight", loc.getY());
        plugin.getConfig().set("lobby.xpos", loc.getZ());
        plugin.saveConfig();
    }

    /**
     * Gibt den Lobby-Spawnpunkt zurück
     * @return spawnLocation den Spawnpunkt der Daily-Lobby
     */
    public Location getSpawn() {
        return this.spawnLocation;
    }

}
