package com.example.demo.controller;

import com.example.demo.infra.TokenService;
import com.example.demo.model.user.user.*;
import com.example.demo.repository.UserEntityRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserEntityRepository repository;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Client) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token)); //Retornando o token do login
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();//VERIFICANDO SE EXISTE UM LOGIN NO BANCO

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password()); //Criptografando a senha
        Client newUser = new Client(data.login(), encryptedPassword, data.role(), data.email(), data.sexo(), data.contato(), data.nome()); //Salando as credenciais Usuario no banco

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity getAllUsers() {
        List<UserResponseDTO> userList = this.repository.findAll().stream().map(UserResponseDTO::new).toList();
        return ResponseEntity.ok(userList);
    }

}
