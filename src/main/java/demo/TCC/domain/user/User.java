package demo.TCC.domain.user;

import demo.TCC.domain.cidade.Cidade;
import demo.TCC.domain.local.Local;
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
    private String telefone;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "locatario", cascade = CascadeType.ALL)
    private List<Local> locais = new ArrayList<>();

    public User(String login, String password, UserRole role, String nome, String email, String telefone){
        this.login = login;
        this.password = password;
        this.role = role;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.LOCATARIO) return List.of(new SimpleGrantedAuthority("ROLE_LOCATARIO"), new SimpleGrantedAuthority("ROLE_LOCADOR"));
        else return List.of(new SimpleGrantedAuthority("ROLE_LOCADOR"));
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return "";
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
