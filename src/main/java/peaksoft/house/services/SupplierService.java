package peaksoft.house.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.house.configuration.Configuration;
import peaksoft.house.enums.OrderStatus;
import peaksoft.house.enums.SupplierStatus;
import peaksoft.house.models.Order;
import peaksoft.house.models.Supplier;

import java.util.List;

/**
 * @author Beksultan
 */
public class SupplierService implements AutoCloseable{
    // create objects of repositories
    private EntityManagerFactory entityManagerFactory = Configuration.createEntityManagerFactory();

    public void save(Supplier newSupplier) {
        // write your code here
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(newSupplier);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void update(Long supplierId, Supplier newSupplier) {
        // write your code here
        // you should find supplier with id = :supplierId
        // and replace with newSupplier
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("update Supplier s set s.fullName= :fullName,s.phoneNumber = :phoneNumber,s.status=:status where s.id = :id")
                .setParameter("date",newSupplier.getFullName())
                .setParameter("price",newSupplier.getPhoneNumber())
                .setParameter("status",newSupplier.getStatus())
                .setParameter("id",supplierId)
                .executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<Supplier> findAllSuppliers() {
        // write your code here
        // you should find all suppliers and return them
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Supplier> suppliers = entityManager.createQuery("select s from Supplier s ", Supplier.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return suppliers;
    }

    public Supplier findById(Long supplierId) {
        // write your code here
        // you should return supplier with id = :supplierId
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Supplier supplier = entityManager.createQuery("select s from Supplier s where s.id=:id", Supplier.class)
                .setParameter("id", supplierId)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return supplier;
    }

    public void getOrder(Long supplierId, Order newOrder) {
        // give free order to supplier with id = :supplierId
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Supplier freeSupplier = entityManager.createQuery("select s from Supplier s where s.status=:free", Supplier.class)
                .setParameter("free", SupplierStatus.FREE).getSingleResult();

        Order freeOrder = entityManager.createQuery("select o from Order o where o.status=:free", Order.class)
                .setParameter("free", OrderStatus.REQUEST).getSingleResult();

        freeSupplier.giveOrder(freeOrder);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void deleteById(Long supplierId) {
        // write your code here
        // you should delete supplier with id = :supplierId
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Supplier.class, supplierId));

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<Order> findAllOrders(Long supplierId) {
        // find all supplier's delivered orders
        return null;
    }

    public List<Supplier> findAllBusySuppliers() {
        // find all busy suppliers
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Supplier> busySuppliers = entityManager.createQuery
                        ("select s from Supplier s where s.status=:busy", Supplier.class)
                            .setParameter("busy", SupplierStatus.BUSY)
                                .getResultList();

        entityManager.getTransaction().commit();

        entityManager.close();

        return busySuppliers;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
