package uz.pdp.cinemas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.cinemas.dto.JwtDto;
import uz.pdp.cinemas.dto.UserLoginDto;
import uz.pdp.cinemas.dto.UserRegisterDto;
import uz.pdp.cinemas.entity.User;

import java.util.List;

@Service
public interface UserService {
    JwtDto register(UserRegisterDto userRegisterDto);
    JwtDto login(UserLoginDto userLoginDto);
    void update(User user);
    void delete(Long id);
    User getById(Long id);
    List<User> getAll();
    void like(Long movieId,Long userId);
}
