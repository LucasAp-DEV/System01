package demo.TCC.domain.user;

import demo.TCC.domain.cidade.Cidade;

public record RegisterUserDTO(String login, String password, UserRole role, String nome, String email, String telephone, Cidade cidadeId) {
}

