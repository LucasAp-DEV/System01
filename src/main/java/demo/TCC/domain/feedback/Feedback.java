package demo.TCC.domain.feedback;

import demo.TCC.domain.contrato.Contrato;
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
}
