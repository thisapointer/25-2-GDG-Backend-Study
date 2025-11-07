package com.example.shop.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("SELECT * FROM Product p", Product.class)
                .getResultList();
    }

    public void save(Product product) {
        em.persist(product);
    }

    public void deleteById(Long id) {
        Product product = em.find(Product.class, id);
        em.remove(product);
    }
}
