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

import java.util.HashMap;

import org.bukkit.Location;

/**
 * @author René
 * Beherbergt alle Informationen zu Spielern innerhalb des Dailys
 */
public class PlayerManager {

    private static PlayerManager playerManager;

    // HashMap mit Spielernamen und Locations, Player-Objekte wär zu überladen
    private HashMap<String, Location> playercheckpoints = new HashMap<>();

    /**
     * Setzt die Location zum Checkpoint eines Spielers
     * @param playername der Spielername
     * @param location die Location wo der Checkpoint ausgelöst wurde
     */
    void setCheckpoint(String playername, Location location) {
        playercheckpoints.put(playername, location);
    }

    /**
     * Gibt die Location zum Checkpoint eines Spielers zurück
     * @param playername der Spielername
     * @return die Location wo zuletzt ein Checkpoint ausgelöst wurde
     */
    public Location getCheckpoint(String playername) {
        return playercheckpoints.get(playername);
    }

    /**
     * Statischer Getter für den PlayerManager, erzeugt einen neuen
     * @return das PlayerManager Objekt
     */
    public static PlayerManager getPlayerManager() {
        if(playerManager == null) {
            playerManager = new PlayerManager();
        }
        return playerManager;
    }
}
