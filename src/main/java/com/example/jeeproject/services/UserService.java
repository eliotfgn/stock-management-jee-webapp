package com.example.jeeproject.services;

import com.example.jeeproject.models.User;
import com.example.jeeproject.repos.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
}
