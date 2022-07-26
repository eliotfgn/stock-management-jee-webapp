package com.example.jeeproject.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class DbUtil {

    public static EntityManager getEntityManager(String schema) {
        return Persistence.createEntityManagerFactory(schema).createEntityManager();
    }
}
