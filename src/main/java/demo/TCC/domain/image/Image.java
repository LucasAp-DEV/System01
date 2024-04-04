package demo.TCC.domain.image;

import demo.TCC.domain.local.Local;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
@Entity(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
//    @Column(length = 100000000)
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "local_id", referencedColumnName = "id")
    private Local local;

    public Image(byte[] image, Local local) {
        this.image = image;
        this.local = local;
    }

}
