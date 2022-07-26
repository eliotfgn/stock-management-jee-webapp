package com.example.jeeproject.services;

import com.example.jeeproject.models.Reservation;
import com.example.jeeproject.repos.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ReservationService {
    private final static EntityManager entityManager = DbUtil.getEntityManager("default");

    public static void reserve(Reservation reservation) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(reservation);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    public static List<Reservation> getAllReservations() {
        String query = "SELECT r FROM Reservation r";
        TypedQuery<Reservation> typedQuery = entityManager.createQuery(query, Reservation.class);
        List<Reservation> reservations = null;

        try {
            reservations = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return reservations;
    }

    public static List<Reservation> getReservationByUser(String userEmail) {
        String query = "SELECT r FROM Reservation r WHERE r.user.email = :userEmail";
        TypedQuery<Reservation> typedQuery = entityManager.createQuery(query, Reservation.class);
        typedQuery.setParameter("userEmail", userEmail);
        List<Reservation> reservations = null;

        try {
            reservations = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return reservations;
    }
}
