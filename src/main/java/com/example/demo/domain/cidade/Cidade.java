package com.example.demo.domain.cidade;

import com.example.demo.domain.local.Local;
import com.example.demo.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cidade")
@Entity(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME",unique = true)
    private String name;

    @Column(name = "USERS")
    @JsonIgnore
    @OneToMany(mappedBy = "cidadeId")
    private List<User> users = new ArrayList<>();

    public Cidade(String name) {
        this.name = name;
    }
}
