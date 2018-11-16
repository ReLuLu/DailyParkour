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
import eu.bdh.daily.daily.PlayerManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author René
 * Checkpoint Klasse zur Abhandlung des Befehls hinter /dcheck
 */
public class CheckCommand implements CommandExecutor {

    private PlayerManager playerman = PlayerManager.getPlayerManager();
    private BdHDaily plugin;

    public CheckCommand(BdHDaily plugin) {
        this.plugin = plugin;
    }

    /**
     * Handelt den /dcheck Befehl ab, der von einem Spieler ausgeht
     * @param sender wer hat den Befehl abgesetzt
     * @param command Befehl als Objekt
     * @param comname Name des Befehls (/dcheck)
     * @param comparams zugehörige Parameter (/dcheck x y z)
     * @return Befehl korrekt genutzt oder nicht
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String comname, String[] comparams) {

        // nur vom Spieler zu benutzen
        if(sender instanceof Player) {
            Player player = (Player)sender;
            if(player.getWorld().getName().equalsIgnoreCase(plugin.getConfig().getString("world"))) {

                if(playerman.hasCheckpoint(player.getUniqueId())) {
                    player.teleport(playerman.getCheckpoint(player.getUniqueId()));
                    // TODO mit MessageHandler irgendwann schöne Ausgaben machen
                    player.sendMessage("Zurück zum CheckCommand");
                } else {
                    player.sendMessage("Noch keinen CheckCommand erreicht");
                }
                return true;
            }
        }
        return false;
    }




}
