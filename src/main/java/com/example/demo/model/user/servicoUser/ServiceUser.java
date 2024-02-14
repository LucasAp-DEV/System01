package com.example.demo.model.user.servicoUser;

import com.example.demo.model.user.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "SERVICOS_USER")
public class ServiceUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;

    @Getter
    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users users;

    public void setUsers(Users users) {
        this.users = users;
    }
}
