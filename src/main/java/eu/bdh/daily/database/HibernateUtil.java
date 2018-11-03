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

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import eu.bdh.daily.BdHDaily;
import eu.bdh.daily.database.pojo.Player;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Clive/Florian
 * Stellt die Verbindung zur Datenbank über Hibernate mit Hikari als ConnectionPool her.
 * Konkret wird eine SessionFactory bereitegstellt mit der gearbeitet werden kann.
 */
public class HibernateUtil extends AbstractModule {

    private SessionFactory sessionFactory;
    private BdHDaily plugin;

    @Inject
    public HibernateUtil(BdHDaily plugin){
        this.plugin = plugin;
        try {
            sessionFactory = this.configureSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SessionFactory configureSessionFactory() throws IOException {
        Configuration configuration = new Configuration();
        InputStream inputStream = this.getClass().getClassLoader().
                getResourceAsStream("hibernate.properties");
        Properties hibernateProperties = new Properties();
        hibernateProperties.load(inputStream);
        configuration.setProperties(hibernateProperties);
        configuration.addAnnotatedClass(Player.class);

        Map<String, Object> settings = new HashMap<>();
        settings.put(Environment.URL, plugin.getConfig().getString("database.url"));
        settings.put(Environment.USER, plugin.getConfig().getString("database.user"));
        settings.put(Environment.PASS, plugin.getConfig().getString("database.password"));

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).applySettings(settings).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public void destroy(){
        sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
