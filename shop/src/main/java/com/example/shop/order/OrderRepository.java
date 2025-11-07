package com.example.shop.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("SELECT * FROM Order o", Order.class)
                .getResultList();
    }

    public void deleteById(Long id) {
        Order order = em.find(Order.class, id);
        em.remove(order);
    }
}
