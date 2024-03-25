package com.example.demo.domain.feedback;

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
    @JoinColumn(name = "local_id", referencedColumnName = "id")
    private Local localId;

    public Feedback(String descricao, int nota, Local localId) {
        this.descricao = descricao;
        this.nota = nota;
        this.localId = localId;
    }
}
