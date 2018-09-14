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

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Switch;
import org.bukkit.block.data.type.Switch.Face;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author René
 * Lauscht auf das PlayerInteractEvent, welches bei der Nutzung eines Checkpoints ausgelöst wird
 */
public class DailyListener implements Listener {

    // beherbergt alle Knopfarten, die einen gültigen Checkpoint definieren
    private final List<Material> validbuttons = Arrays.asList(
            Material.OAK_BUTTON, Material.BIRCH_BUTTON, Material.SPRUCE_BUTTON,
            Material.DARK_OAK_BUTTON, Material.JUNGLE_BUTTON, Material.ACACIA_BUTTON
    );

    // beherbergt alle Druckplatten, die einen gültigen Checkpoint definieren
    private final List<Material> validplates = Arrays.asList(
            Material.OAK_PRESSURE_PLATE, Material.BIRCH_PRESSURE_PLATE,
            Material.SPRUCE_PRESSURE_PLATE, Material.DARK_OAK_PRESSURE_PLATE,
            Material.JUNGLE_PRESSURE_PLATE, Material.ACACIA_PRESSURE_PLATE
    );

    private PlayerManager playerman = PlayerManager.getPlayerManager();

    /**
     * Handelt den Vorgang eines ausgelösten Checkpoints ab
     * @param plinev das PlayerInteractEvent
     */
    @EventHandler
    public void onCheckpoint(PlayerInteractEvent plinev) {

        //TODO CP erreicht Meldungen
        // Rechtsklick Action durch Drücken eines Knopfes
        if(plinev.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            Player p = plinev.getPlayer();
            Block bl = plinev.getClickedBlock();

            // wenn der Block als Material in der Knopfliste ist und in die Checkpointdefinition passt
            if(validbuttons.contains(bl.getType())) {

                // den geklickten Block auf Switch casten
                Switch sw = (Switch)bl.getState().getBlockData();

                // Knopf obendrauf aber nicht an der Decke
                if((sw.getFace() == Face.FLOOR) && (bl.getRelative(BlockFace.DOWN).getType() == Material.GOLD_BLOCK)) {
                    playerman.setCheckpoint(p.getDisplayName(), p.getLocation());
                }

                // Knopf drumrum, schaut nach dem Block an dem face wo Knopf dranklebt
                else if(bl.getRelative(sw.getFacing().getOppositeFace()).getType() == Material.GOLD_BLOCK) {
                    playerman.setCheckpoint(p.getDisplayName(), p.getLocation());
                }

                // Knopf an der Decke aber nur wenn dort ein Goldblock ist
                else if((sw.getFace() == Face.CEILING) && (bl.getRelative(BlockFace.UP).getType() == Material.GOLD_BLOCK)) {
                    playerman.setCheckpoint(p.getDisplayName(), p.getLocation());
                }
            }

            // gesondert auf einen Steinknopf lauschen, der ein Ziel definiert
            if(bl.getType() == Material.STONE_BUTTON) {
                //TODO Für Ziel lauschen
            }
        }

        // Physical Action durch Betätigen einer Druckplatte
        else if(plinev.getAction().equals(Action.PHYSICAL)) {

            Player p = plinev.getPlayer();
            Block bl = plinev.getClickedBlock();

            if(validplates.contains(bl.getType())) {

                // Druckplatten sind ja immer auf einem Untergrund, daher DOWN
                if(bl.getRelative(BlockFace.DOWN).getType() == Material.GOLD_BLOCK) {
                    playerman.setCheckpoint(p.getDisplayName(), p.getLocation());
                }
            }
        }
    }
}
