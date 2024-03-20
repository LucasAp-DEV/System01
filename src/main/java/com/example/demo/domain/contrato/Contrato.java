package com.example.demo.domain.contrato;

import com.example.demo.domain.local.Local;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "contrato")
@Table(name = "contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private LocalDate data;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "local_id", referencedColumnName = "id")
    private Local localId;

    public Contrato(String descricao, LocalDate data, User userId, Local localId, String status) {
        this.descricao = descricao;
        this.data = data;
        this.userId = userId;
        this.localId = localId;
        this.status = status;
    }

}
