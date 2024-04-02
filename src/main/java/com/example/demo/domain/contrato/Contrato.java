package com.example.demo.domain.contrato;

import com.example.demo.domain.feedback.Feedback;
import com.example.demo.domain.local.Local;
import com.example.demo.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private Local local;

    @JsonIgnore
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();


    public Contrato(String descricao, LocalDate data, User userId, Local local, String status) {
        this.descricao = descricao;
        this.data = data;
        this.userId = userId;
        this.local = local;
        this.status = status;
    }

}
