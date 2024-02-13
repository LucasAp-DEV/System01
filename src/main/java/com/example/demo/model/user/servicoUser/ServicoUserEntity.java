package com.example.demo.model.user.servicoUser;

import com.example.demo.model.user.user.Client;
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
@Table( name = "SERVICOS_USER")
public class ServicoUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    public ServicoUserEntity(ServiceResponserDTO data){
        this.price = data.price();
        this.name = data.name();
    }
}
