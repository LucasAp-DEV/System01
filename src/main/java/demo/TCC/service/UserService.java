package demo.TCC.service;

import demo.TCC.domain.user.*;
import demo.TCC.infra.TokenService;
import demo.TCC.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {


    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public ResponseEntity<LoginResponseDTO> loginUser(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authenticate = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) authenticate.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
    }

    public ResponseEntity<String> saveUser(RegisterUserDTO data) {
        if (returnName(data.login()) != null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nome de usuario em uso");
        if (returnemail(data.email()) != null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND). body("Email ja esta em uso");
        var encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role(), data.nome(), data.email(), data.telefone());
        repository.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario Cadastrado");
    }

    public UserDetails returnName(String name) {
        return repository.findByLogin(name);
    }
    public UserDetails returnemail (String email) {return repository.findByEmail(email);}

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public UserResponseDTO returnById(Long id) {
        var userId = findById(id);
        return convertDTO(userId);
    }

    public void dellUser(Long id) {
        var userId = findById(id);
        repository.delete(userId);
    }

    public List<UserResponseDTO> returnAll() {
        List<User> userList = repository.findAll();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (User user : userList) {
            userResponseDTOS.add(convertDTO(user));
        }
        return userResponseDTOS;
    }

    public ResponseEntity<LoginResponseDTO> updateDTO(Long id, User user) {
        var userID = findById(id);
        userID.setLogin(user.getLogin());
        userID.setNome(user.getNome());
        userID.setEmail(user.getEmail());
        userID.setTelefone(user.getTelefone());
        userID.setRole(user.getRole());

        repository.save(userID);
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
        var authenticate = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) authenticate.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
    }

    public UserResponseDTO convertDTO(User user) {
        UserResponseDTO.UserResponseDTOBuilder builder = UserResponseDTO.builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .nome(user.getNome())
                .telefone(user.getTelefone())
                .id(user.getId())
                .role(user.getRole());
        return builder.build();
    }
}