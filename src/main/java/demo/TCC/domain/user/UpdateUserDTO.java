package demo.TCC.domain.user;

import demo.TCC.domain.cidade.Cidade;

public record UpdateUserDTO(Long id, String login, String password, String nome, String email, String telefone) {
}
