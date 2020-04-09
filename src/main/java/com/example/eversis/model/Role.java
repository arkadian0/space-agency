package com.example.eversis.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty
    private String role;

    public Role() {

    }

    public Role(String role) {
        this.role = role;
    }
}