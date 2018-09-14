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

import eu.bdh.daily.daily.PlayerManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



/**
 * @author René
 * Checkpoint Klasse zur Abhandlung des Befehls hinter /dcheck
 */
public class Checkpoint implements CommandExecutor {

    private PlayerManager playerman = PlayerManager.getPlayerManager();

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
            player.sendMessage("Zurück zum Checkpoint");
            //TODO zurück zum CP Meldung
            //TODO case noch kein CP erreicht bzw. CP am Dailystart setzen
            player.teleport(playerman.getCheckpoint(player.getUniqueId()));
            return true;
        }

        return false;
    }




}
