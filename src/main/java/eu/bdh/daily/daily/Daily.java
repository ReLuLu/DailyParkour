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

import org.bukkit.Location;

/**
 * @author René
 * Repräsentiert ein Daily mit seinen Eigenschaften wie Start & -Zielkoordinaten,
 * Autor(en), durchschnittliche Länge, Bewertungsscore, Aktivitätsstatus, Anzahl
 * erfolgreich durchgespielt, Datum zuletzt gespielt, und so weiter.
 */
public class Daily {

    private String author;          // Autor(en) das Dailys
    private Location start;         // Startpunkt als Location
    private Location end;           // Endpunkt als Location
    private int averageduration;    // durchschnittliche Zeit in Sekunden
    private float votescore;        // Platzhalter für Bewertungssystem o.Ä.
    private float votefun;          // s.o.
    private float votelook;
    private float votelength;
    private boolean active;         // (In)Aktivitätsstatus
    private int finishcount;        // Anzahl Spieler, die das Daily
    private long lastplayed;        // Zeitpunkt wann das Daily zuletzt dran war in epoch


    public Daily() {

    }

    /**
     * Gibt die Location des Startpunktes zurück
     * @return start die Startlocation
     */
    public Location getDailyStart() {
        return start;
    }

    /**
     * Setzt die Location des Startpunktes, wird für die Einrichtung gebraucht.
     * DB Kommunikation muss noch implementiert werden.
     * @param newstart die neue Startlocation
     */
    public boolean setDailyStart(Location newstart)  {
        this.start = newstart;
        //TODO DB Kommunikation
        return true;
    }

    /**
     * Gibt die Location des Endpunktes zurück
     * @return end die Endlocation
     */
    public Location getDailyEnd() {
        return this.end;
    }

    /**
     * Setzt die Location des Endpunktes, wird für die Einrichtung gebraucht.
     * DB Kommunikation muss noch implementiert werden
     * @param newend die neue Endlocation
     */
    public boolean setDailyEnd(Location newend) {
        this.end = newend;
        //TODO DB Kommunikation
        return true;
    }

    /**
     * Gibt den Autor zurück
     * @return Autoren
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Setzt den Autor eines Dailies, wird für die Einrichtung gebraucht.
     * @param newauthor der 'neue' Autor
     * @return ob die Eigenschaft
     */
    public boolean setAuthor(String newauthor) {
        this.author = newauthor;
        //TODO DB Kommunikation
        return true;
    }

}
