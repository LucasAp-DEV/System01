package com.example.demo.domain.local;

import com.example.demo.domain.contrato.Contrato;
import com.example.demo.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "local")
@Table(name = "local")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String endereco;
    private String descricao;
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @JsonIgnore
    @OneToMany(mappedBy = "localId", cascade = CascadeType.ALL)
    private List<Contrato> contratos = new ArrayList<>();

    public Local(String descricao, Integer price, User userId, String endereco) {
        this.descricao = descricao;
        this.price = price;
        this.userId = userId;
        this.endereco = endereco;
    }
}
