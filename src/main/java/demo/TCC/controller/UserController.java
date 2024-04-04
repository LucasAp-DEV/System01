package demo.TCC.controller;

import demo.TCC.domain.user.*;
import demo.TCC.infra.TokenService;
import demo.TCC.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid AuthenticationDTO data){
        return service.loginUser(data);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterUserDTO data) {
        return service.saveUser(data);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatLocal(@PathVariable (value = "id")Long id,@RequestBody @Valid User data) {
        service.updateDTO(id, data);
        return ResponseEntity.status(HttpStatus.OK).body("Atualizado com Sucesso");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletUser(@PathVariable (value = "id")Long id) {
        service.dellUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario Deletado");
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.returnAll());
    }

    @GetMapping("/{id}")
    public UserResponseDTO getByIdUser(@PathVariable (value = "id")Long id) {
        return service.returnById(id);
    }

}

