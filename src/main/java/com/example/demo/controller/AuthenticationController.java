package com.example.demo.controller;

import com.example.demo.model.user.AuthentucationDTO;
import com.example.demo.model.user.RegisterDTO;
import com.example.demo.model.user.User1;
import com.example.demo.repository.User1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private User1Repository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthentucationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String enccryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User1 newUser = new User1(data.login(), enccryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }


}
