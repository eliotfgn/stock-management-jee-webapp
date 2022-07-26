package com.example.jeeproject.services;

import com.example.jeeproject.models.Item;
import com.example.jeeproject.repos.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ItemService {
    private static final EntityManager entityManager = DbUtil.getEntityManager("default");

    public static void addItem(Item item) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(item);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    public static void deleteItem(Item item) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.remove(item);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    public static Item getItem(long id) {
        String query = "SELECT i FROM Item i WHERE i.id = :id";
        TypedQuery<Item> typedQuery = entityManager.createQuery(query, Item.class);
        typedQuery.setParameter("id", id);
        Item item = null;

        try {
            item = typedQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return item;
    }

    public static List<Item> getAllItems() {
        String query = "SELECT i FROM Item i";
        TypedQuery<Item> typedQuery = entityManager.createQuery(query, Item.class);
        List<Item> items = null;

        try {
            items = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return items;
    }

    public static List<Item> getByType(String type) {
        String query = "SELECT i FROM Item i WHERE i.type = :type";
        TypedQuery<Item> typedQuery = entityManager.createQuery(query, Item.class);
        typedQuery.setParameter("type", type);
        List<Item> items = null;

        try {
            items = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return items;
    }

    public static List<Item> getByCategory(String category) {
        String query = "SELECT i FROM Item i WHERE i.category = :category";
        TypedQuery<Item> typedQuery = entityManager.createQuery(query, Item.class);
        typedQuery.setParameter("category", category);
        List<Item> items = null;

        try {
            items = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return items;
    }

}
