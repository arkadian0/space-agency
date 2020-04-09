package com.example.eversis.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer_order")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    public CustomerOrder() {

    }

    public CustomerOrder(User user) {
        this.user = user;
    }

}
