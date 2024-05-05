package demo.TCC.domain.user;

import lombok.Builder;

@Builder
public record UserResponseDTO(Long id, String login, UserRole role, String nome, String email, String telefone) {

}
