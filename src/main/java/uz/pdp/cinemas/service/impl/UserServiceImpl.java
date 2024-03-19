package uz.pdp.cinemas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.cinemas.dto.JwtDto;
import uz.pdp.cinemas.dto.UserLoginDto;
import uz.pdp.cinemas.dto.UserRegisterDto;
import uz.pdp.cinemas.entity.User;
import uz.pdp.cinemas.exception.AlreadyExistsException;
import uz.pdp.cinemas.exception.InvalidArgumentException;
import uz.pdp.cinemas.exception.NotFoundException;
import uz.pdp.cinemas.repository.UserRepository;
import uz.pdp.cinemas.security.jwt.JwtTokenProvider;
import uz.pdp.cinemas.service.UserService;
import uz.pdp.cinemas.util.Validations;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final Cache cache;
    public UserServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, CacheManager cacheManager) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.cache = cacheManager.getCache("users");
    }

    @Override
    public JwtDto register(UserRegisterDto userRegisterDto) {
        if (userRepository.findByEmail(userRegisterDto.getEmail()).isPresent())
            throw new AlreadyExistsException("User");
        if (!Objects.equals(userRegisterDto.getPassword(), userRegisterDto.getConfirmPassword()))
            throw new InvalidArgumentException("Password");
        User user = userRepository.save(
                User.builder()
                        .id(userRegisterDto.getId())
                        .isAdmin(false)
                        .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                        .build()
        );
        if (cache != null)
            cache.put(user.getId(),user);
        return new JwtDto(jwtTokenProvider.generateToken(user));
    }

    @Override
    public JwtDto login(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(
                () -> new NotFoundException("User")
        );
        if (!passwordEncoder.matches(userLoginDto.getPassword(),user.getPassword()))
            throw new InvalidArgumentException("Password");
        String accessToken = jwtTokenProvider.generateToken(user);
        if (cache != null)
            cache.put(user.getId(),user);
        return new JwtDto(accessToken);
    }

    @Override
    public void update(User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new NotFoundException("User")
        );
        User updatedUser = User.builder()
                .id(user.getId())
                .name(Validations.requireNonNullElse(user.getName(), existingUser.getName()))
                .password(Validations.requireNonNullElse(user.getPassword(), existingUser.getPassword()))
                .isAdmin(user.isAdmin() != existingUser.isAdmin() ? user.isAdmin() : existingUser.isAdmin())
                .build();
        userRepository.save(updatedUser);
        if (cache != null)
            cache.put(updatedUser.getId(),updatedUser);
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id))
            throw new NotFoundException("User");
        userRepository.deleteById(id);
        if (cache != null)
            cache.evict(id);
    }

    @Override
    public User getById(Long id) {
        if (cache != null){
            if (cache.get(id,User.class) == null)
                cache.put(id,userRepository.findById(id).orElseThrow(
                        ()->new NotFoundException("User")
                ));
            return cache.get(id,User.class);
        }
        throw new NotFoundException("User");
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    @Override
    public void like(Long movieId, Long userId) {
        //todo first added movie repository
    }
}
