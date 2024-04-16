package demo.TCC.domain.contrato;

import demo.TCC.domain.local.Local;
import demo.TCC.domain.user.User;
import demo.TCC.domain.feedback.Feedback;
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
    private LocalDate data;
    private String status;

    @ManyToOne
    @JoinColumn(name = "locatario_id", referencedColumnName = "id")
    private User locatario;

    @ManyToOne
    @JoinColumn(name = "locador_id", referencedColumnName = "id")
    private User locador;

    @ManyToOne
    @JoinColumn(name = "local_id", referencedColumnName = "id")
    private Local local;

    @JsonIgnore
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();


//    public Contrato(String descricao, LocalDate data, User userId, Local local, String status) {
//        this.descricao = descricao;
//        this.data = data;
//        this.userId = userId;
//        this.local = local;
//        this.status = status;
//    }

}
