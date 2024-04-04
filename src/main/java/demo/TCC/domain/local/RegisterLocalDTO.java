package demo.TCC.domain.local;


import demo.TCC.domain.user.User;

public record RegisterLocalDTO(String descricao, Integer price, User userId, String endereco) {
}
