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

package eu.bdh.daily.daily.commands;

import eu.bdh.daily.BdHDaily;
import eu.bdh.daily.daily.DailyLobby;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author René
 * Dailybefehl-Klasse zur Abhandlung des Befehls hinter /daily
 */
public class DailyCommand implements CommandExecutor {

    private BdHDaily plugin;
    private DailyLobby lobby;

    public DailyCommand(BdHDaily plugin, DailyLobby lobby) {
        this.plugin = plugin;
        this.lobby = lobby;
    }

    /**
     * Handelt den /daily Befehl ab, der von einem Spieler ausgeht
     * @param sender wer hat den Befehl abgesetzt
     * @param command Befehl als Objekt
     * @param comname Name des Befehls (/daily)
     * @param comparams zugehörige Parameter (/daily (setlobby, skip, ..)
     * @return Befehl korrekt genutzt oder nicht
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String comname, String[] comparams) {
        if(sender instanceof Player) {
            Player player = (Player)sender;

            // Befehl ohne Argumente ungültig
            if(comparams.length < 1) {
                // dann gib die Auflistung an den Spieler aus
                defaultMessage(player);
            }
            // Befehl mit mind. 1 Argument
            switch(comparams[0]) {

                /*
                 *  Lobby setzen
                 */
                case "setlobby":

                    // Lobby Spawnpunkt via getLocation() setzen
                    if(comparams.length == 1) {

                        Location temploc = player.getLocation();
                        lobby.setSpawn(temploc);
                        // TODO schöne Nachricht
                        player.sendMessage("Daily Lobby Spawnpunkt "
                                + temploc.getX() + " / "
                                + temploc.getY() + " / "
                                + temploc.getZ() + "gesetzt!");
                    }

                    // Lobby Spawnpunkt via Parameter setzen
                    else if(comparams.length == 4) {

                        try {
                            // Koordinaten aus den Befehlsparametern auslesen
                            Double x = Double.valueOf(comparams[1]);
                            Double y = Double.valueOf(comparams[2]);
                            Double z = Double.valueOf(comparams[3]);
                            Location temploc = new Location(player.getWorld(), x, y, z);
                            lobby.setSpawn(temploc);
                            // Nachricht an den Spieler
                            // TODO schöne Nachricht
                            player.sendMessage("Daily Lobby Spawnpunkt "
                                    + temploc.getX() + " / "
                                    + temploc.getY() + " / "
                                    + temploc.getZ() + "gesetzt!");
                        } catch(Exception e) {
                            // Hilfsauflistung
                            defaultMessage(player);
                        }
                    }
                    break;

                /*
                 *  Platzhalter für skip
                 */
                case "skip":

                    break;

                /*
                 *  Hilfetext ausgeben wenn nichts passt
                 */
                default:
                    // Hilfsauflistung
                    defaultMessage(player);
                    break;
            }
        }
        return true;
    }

    /**
     * Gibt eine Hilfsauflistung von /daily Befehlen aus
     * @param player der Spieler, der ursprünglich einen Befehl eingegeben hat
     */
    private void defaultMessage(Player player) {
        // TODO schöne Nachrichten
        player.sendMessage("/daily setlobby, /daily setlobby <x y z>");
        player.sendMessage("/daily skip");
    }
}
