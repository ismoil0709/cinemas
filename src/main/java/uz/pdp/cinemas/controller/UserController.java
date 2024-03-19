package uz.pdp.cinemas.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemas.dto.JwtDto;
import uz.pdp.cinemas.dto.UserLoginDto;
import uz.pdp.cinemas.dto.UserRegisterDto;
import uz.pdp.cinemas.entity.User;
import uz.pdp.cinemas.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.version}/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<JwtDto> register (@RequestBody @Valid UserRegisterDto registerDto){
        return ResponseEntity.ok(userService.register(registerDto));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody @Valid UserLoginDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto));
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid User user){
        userService.update(user);
        return ResponseEntity.ok(Map.of("message","Successfully"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotNull Long id){
        userService.delete(id);
        return ResponseEntity.ok(Map.of("message","Successfully"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable @NotNull Long id){
        return ResponseEntity.ok(userService.getById(id));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping("/like")
    public void like(){
        //todo like
    }
}
