package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Item itemFromDb;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            itemFromDb = session.get(Item.class, id);
            if (itemFromDb != null) {
                itemFromDb.setName(item.getName());
                session.update(itemFromDb);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return itemFromDb != null;
    }

    @Override
    public void delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Item item = session.get(Item.class, id);
            if (item != null) {
                session.delete(item);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> result;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery("from ru.job4j.tracker.Item", Item.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery("from ru.job4j.tracker.Item where name = :name", Item.class)
                    .setParameter("name", key).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item item;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            item = session.get(Item.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
