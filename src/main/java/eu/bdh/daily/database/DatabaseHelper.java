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
package eu.bdh.daily.database;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Über diese Klasse können SQL Befehle abgesetzt werden.
 * !WICHTIG! Diese Klasse darf ausschließlich aus einem asynchronen Thread
 * aufgerufen werden.
 * @author Florian
 */
public class DatabaseHelper {

    private static final Logger log = Logger.getLogger(DatabaseHelper.class.getName());

    public void noReturnSQL(String sql, String... parameter){
        //Prüft auf eine potentielle Übereinstimmung mit dem Main Thread. Dieser Aufruf ist keine absolute Garantie.
        if (Bukkit.isPrimaryThread()){
            log.log(Level.SEVERE,"!ACHTUNG!: Möglicher Zugriff auf die Datenbank aus einem synchronen Kontext.");
        }
        Connection connection = MySQLConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //TODO Wartet auf setParameters
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<ResultSet> getData(String sql){
        //Prüft auf eine potentielle Übereinstimmung mit dem Main Thread. Dieser Aufruf ist keine absolute Garantie.
        if (Bukkit.isPrimaryThread()){
            log.log(Level.SEVERE,"!ACHTUNG!: Möglicher Zugriff auf die Datenbank aus einem synchronen Kontext.");
        }
        //TODO Wartet auf setParameters
        return Optional.empty();
    }

    private PreparedStatement setParameters(PreparedStatement preparedStatement, String... parameter){
        //TODO Dynamic Parameter Setting
        return preparedStatement;
    }

}
