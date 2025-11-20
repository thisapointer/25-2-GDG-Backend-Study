package com.example.shop.product.entity;

import com.example.shop.orderProduct.OrderProduct;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(name = "product_name", length = 100)
    private String name;

    @Column(name = "product_price")
    private int price;

    @Column(name = "manufacturing", length = 255)
    private String manufacturing;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "inventory")
    private int inventory;

    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
        this.manufacturing = "";
        this.productType = "";
        this.inventory = 0;
    }

    public void updateInfo(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
