package com.example.demo.domain.user;

import com.example.demo.domain.cidade.Cidade;
import com.example.demo.domain.local.Local;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "usuario")
@Table(name = "usuario")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String nome;
    private String email;
    private String telephone;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @JsonIgnore
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Local> locais = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cidadeId", referencedColumnName = "id")
    private Cidade cidadeId;

    public User(String login, String password, UserRole role, String nome, String email, String telephone, Cidade cidadeId){
        this.login = login;
        this.password = password;
        this.role = role;
        this.nome = nome;
        this.email = email;
        this.telephone = telephone;
        this.cidadeId = cidadeId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
