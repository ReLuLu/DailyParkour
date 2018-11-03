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

import com.google.inject.Inject;
import eu.bdh.daily.database.pojo.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Clive/Florian
 * WIP
 */
public class DatabaseManager {
    private final HibernateUtil util;

    @Inject
    public DatabaseManager(HibernateUtil util){
        this.util = util;
    }

    public void runDB(){
        Session sessionFactory = util.getSessionFactory().openSession();
        Player player = new Player("Test");
        Transaction transaction = sessionFactory.beginTransaction();
        sessionFactory.save(player);
        transaction.commit();
        sessionFactory.close();
        util.destroy();
    }

}
