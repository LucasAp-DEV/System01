package com.example.demo.domain.feedback;

import com.example.demo.domain.cidade.Cidade;
import com.example.demo.domain.contrato.Contrato;
import com.example.demo.domain.local.Local;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "FEEDBACK")
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private int nota;

    @ManyToOne
    @JoinColumn(name = "contratoId", referencedColumnName = "id")
    private Contrato contrato;


    public Feedback(String descricao, int nota, Contrato contrato) {
        this.descricao = descricao;
        this.nota = nota;
        this.contrato = contrato;
    }
}
