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
import org.bukkit.Location;

/**
 * @author René
 * Repräsentiert die Daily-Lobby mit Koordinaten.
 */
public class DailyLobby {

    private BdHDaily plugin;

    public DailyLobby(BdHDaily plugin) {
        this.plugin = plugin;
    }

    /**
     * Setzt den Lobby-Spawnpunkt
     * @return
     */
    public boolean setSpawn() {

        return true;
    }

    /**
     * Gibt den Lobby-Spawnpunkt zurück
     * @return
     */
    public Location getSpawn() {

        return new Location(null, 0.0, 20.0, 0.0);
    }

}
