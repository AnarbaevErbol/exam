package peaksoft.house.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import peaksoft.house.models.Address;
import peaksoft.house.models.Customer;
import peaksoft.house.models.Order;
import peaksoft.house.models.Supplier;

import java.util.Properties;

public class Configuration {

    //release hibernate configuration here

    public static SessionFactory createSessionFactory() {
        // write configuration if you want use session factory
        // if you don't use this method then don't remove!
        return null;
    }

    public static EntityManagerFactory createEntityManagerFactory() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER,"org.postgresql.Driver");
        properties.put(Environment.URL,"jdbc:postgresql://localhost:5432/postgres");
        properties.put(Environment.USER,"postgres");
        properties.put(Environment.PASS,"annembenim");
        properties.put(Environment.HBM2DDL_AUTO,"update");
        properties.put(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL,"true");

        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();

        configuration.addProperties(properties);

        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(Supplier.class);

        return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
    }

}
