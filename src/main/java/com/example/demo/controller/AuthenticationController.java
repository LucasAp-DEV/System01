package com.example.demo.controller;

import com.example.demo.domain.user.AuthenticationDTO;
import com.example.demo.domain.user.LoginResponseDTO;
import com.example.demo.domain.user.RegisterUserDTO;
import com.example.demo.domain.user.User;
import com.example.demo.infra.TokenService;
import com.example.demo.repository.UserEntityRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
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

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterUserDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();//VERIFICANDO SE EXISTE UM LOGIN NO BANCO

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password()); //Criptografando a senha
        User newUser = new User(data.login(), encryptedPassword, data.role(), data.nome(), data.email(), data.telephone(), data.sexo()); //Salando as credenciais Usuario no banco

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

}

