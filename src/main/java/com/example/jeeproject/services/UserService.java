package com.example.jeeproject.services;

import com.example.jeeproject.models.User;
import com.example.jeeproject.repos.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserService {
    private final static EntityManager entityManager = DbUtil.getEntityManager("default");

    public static void createUser(String name, String password, String email, String position, String role) {
        User user = new User(name, password, email, position, role);
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    public static User getUser(String email) {
        String query = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        typedQuery.setParameter("email", email);
        User user = null;

        try {
            user = typedQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return user;
    }

    public static void deleteUser(String email) {
        User user = getUser(email);
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.remove(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    public static List<User> getAllUsers() {
        String query = "SELECT u FROM User u";
        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        List<User> users = null;

        try {
            users = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return users;
    }
}
