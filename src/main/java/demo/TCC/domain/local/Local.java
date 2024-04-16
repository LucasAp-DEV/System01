package demo.TCC.domain.local;

import demo.TCC.domain.cidade.Cidade;
import demo.TCC.domain.image.Image;
import demo.TCC.domain.user.User;
import demo.TCC.domain.contrato.Contrato;
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
    @JoinColumn(name = "locatario_id", referencedColumnName = "id")
    private User locatario;

    @ManyToOne
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    private Cidade cidade;

    @JsonIgnore
    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    private List<Contrato> contratos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();
}
