package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CENTRO")
public class Centro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

}
