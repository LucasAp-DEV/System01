package com.example.demo.domain.image;

import com.example.demo.domain.local.Local;
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
    @Column(length = 1000000)
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "local_id", referencedColumnName = "id")
    private Local localId;

    public Image(byte[] image, Local localId) {
        this.image = image;
        this.localId = localId;
    }

}
