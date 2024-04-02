package com.example.demo.domain.cidade;

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
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "cidade")
    private List<User> users = new ArrayList<>();

//    @Override
//    public String toString() {
//        return "Cidade{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", users=" + users +
//                '}';
//    }
}
