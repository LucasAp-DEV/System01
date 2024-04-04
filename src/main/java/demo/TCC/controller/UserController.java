package demo.TCC.controller;

import demo.TCC.domain.user.*;
import demo.TCC.infra.TokenService;
import demo.TCC.repository.UserRepository;
import demo.TCC.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterUserDTO data) {
        if (service.returnName(data.login()) != null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nome de usuario em uso");
        service.saveUser(data);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario Cadastrado");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatLocal(@RequestBody UpdateUserDTO data) {
        User updatUser = repository.getReferenceById(data.id());

        if (data.nome() != null) {updatUser.setNome(data.nome());}
        if (data.email() != null) {updatUser.setEmail(data.email());}
        if (data.login() != null) {updatUser.setLogin(data.login());}
        if (data.telephone() != null) {updatUser.setTelephone(data.telephone());}
        if (data.cidadeId() != null) {updatUser.setCidade(data.cidadeId());}

        this.repository.save(updatUser);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletUser(@RequestBody UpdateUserDTO data) {
        User updatUser = repository.getReferenceById(data.id());

        this.repository.delete(updatUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserResponseDTO> userList = this.repository.findAll().stream().map(UserResponseDTO::new).toList();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/id")
    public ResponseEntity<?> getByIdUser(@RequestBody UpdateUserDTO data) {
        User getUser = repository.getReferenceById(data.id());

        UserResponseDTO userOptional = new UserResponseDTO(getUser);

        return ResponseEntity.ok(userOptional);
    }

}

