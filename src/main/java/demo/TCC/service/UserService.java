package demo.TCC.service;

import demo.TCC.domain.user.RegisterUserDTO;
import demo.TCC.domain.user.User;
import demo.TCC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void saveUser(RegisterUserDTO data){
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role(), data.nome(), data.email(), data.telephone(), data.cidadeId());
        repository.save(newUser);
    }

    public UserDetails returnName(String name){
        return repository.findByLogin(name);
    }

}


