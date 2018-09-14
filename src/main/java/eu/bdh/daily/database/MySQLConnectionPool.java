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

import com.zaxxer.hikari.HikariDataSource;
import eu.bdh.daily.BdHDaily;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * MySQLConnectionPool ist die zentrale Klasse zum
 * Aufbau der Datenbank Verbindung.
 * @author Florian
 */
class MySQLConnectionPool {

    private static MySQLConnectionPool mySQL;
    private HikariDataSource dataSource;

    private MySQLConnectionPool() {
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(BdHDaily.getPlugin().getConfig().getString("database.url"));
        dataSource.setUsername(BdHDaily.getPlugin().getConfig().getString("database.user"));
        dataSource.setPassword(BdHDaily.getPlugin().getConfig().getString("database.password"));
    }

    /**
     * Erzeugt bei Bedarf und gibt das ConnectionPool Objekt zurück.
     * @return Gibt das ConnectionPool Objekt zurück.
     */
     static MySQLConnectionPool getInstance() {
        if (mySQL == null) {
            mySQL = new MySQLConnectionPool();
        }
        return mySQL;
    }

    /**
     * Gibt eine Verbindung zur Datenbank zurück.
     * @return Gibt die Verbindung zurück.
     */
    Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
