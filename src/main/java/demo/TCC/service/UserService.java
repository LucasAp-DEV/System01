package demo.TCC.service;

import demo.TCC.domain.cidade.Cidade;
import demo.TCC.domain.user.*;
import demo.TCC.infra.TokenService;
import demo.TCC.repository.CidadeRepository;
import demo.TCC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CidadeRepository cidadeRepository;

    public ResponseEntity<LoginResponseDTO> loginUser(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authenticate = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) authenticate.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
    }

    public ResponseEntity<String> saveUser(RegisterUserDTO data) {
        if (returnName(data.login()) != null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nome de usuario em uso");
        var encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role(), data.nome(), data.email(), data.telephone());
        repository.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario Cadastrado");
    }

    public UserDetails returnName(String name) {
        return repository.findByLogin(name);
    }

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

    public void updateDTO(Long id, User data) {
        var userID = findById(id);
        userID.setLogin(data.getLogin());
        userID.setNome(data.getNome());
        userID.setEmail(data.getEmail());
        userID.setTelephone(data.getTelephone());
        userID.setRole(data.getRole());

        repository.save(userID);
    }




    public UserResponseDTO convertDTO(User user) {
        UserResponseDTO.UserResponseDTOBuilder builder = UserResponseDTO.builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .nome(user.getNome())
                .telephone(user.getTelephone())
                .id(user.getId())
                .role(user.getRole());

        if (user.getCidade() != null) {
            builder.cidade(user.getCidade().getName());
        } else {
            builder.cidade(null);
        }

        return builder.build();
    }
}