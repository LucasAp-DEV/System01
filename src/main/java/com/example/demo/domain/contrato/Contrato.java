package com.example.demo.domain.contrato;

import com.example.demo.domain.local.Local;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "DATA")
    private LocalDate data;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "USER_ID")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @Column(name = "LOCAL_ID")
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
