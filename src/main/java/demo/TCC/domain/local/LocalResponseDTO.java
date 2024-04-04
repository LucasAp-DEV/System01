package demo.TCC.domain.local;

import demo.TCC.domain.user.User;

public record LocalResponseDTO(Long id, String descricao, User userId) {

//    public LocalResponseDTO(Local local){
//        this(local.getId(), local.getDescricao(), local.getUserId());
//    }
}
