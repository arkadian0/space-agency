package com.example.eversis.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private CustomerOrder orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Integer quantity;

    public OrderItem() {
    }


    public OrderItem(CustomerOrder order, Product product, Integer quantity) {
        this.orders = order;
        this.product = product;
        this.quantity = quantity;
    }
}
