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
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author René
 * Checkpoint Klasse zur Abhandlung des Befehls hinter /dcheck
 */
public class Dback implements CommandExecutor {

    private BdHDaily plugin;

    public Dback(BdHDaily plugin) {
        this.plugin = plugin;
    }

    /**
     * Handhabt den /dback Befehl
     * @param sender wer hat den Befehl abgesetzt
     * @param command Befehl als Objekt
     * @param comname Name des Befehls (/dback)
     * @param comparams zugehörige Parameter (/dback x y z), die hier nicht beachtet werden
     * @return Befehl korrekt genutzt oder nicht
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String comname, String[] comparams) {

        // nur vom Spieler zu benutzen
        if(sender instanceof Player) {
            Player player = (Player)sender;

            // der Befehl soll nur in der Dailywelt behandelt werden
            if(player.getWorld().getName().equalsIgnoreCase(plugin.getConfig().getString("world"))) {
                // TODO schöne Ausgabe unso
                player.sendMessage("Zurück zur Lobby!");
                player.getInventory().clear(); // Level bleiben unberührt
                // TODO Location für die Lobby holen, temporär erzeugen
                player.teleport(new Location(player.getWorld(), 0.0, 20.0, 0.0));
            }

        }
        return true; // diese usage-Ausgaben aus der plugin.yml braucht keiner im Chat
    }

}
