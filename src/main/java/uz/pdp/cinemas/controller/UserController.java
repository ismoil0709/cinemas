package uz.pdp.cinemas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemas.dto.JwtDto;
import uz.pdp.cinemas.dto.UserLoginDto;
import uz.pdp.cinemas.dto.UserRegisterDto;
import uz.pdp.cinemas.entity.User;
import uz.pdp.cinemas.exception.NotFoundException;
import uz.pdp.cinemas.repository.UserRepository;
import uz.pdp.cinemas.service.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<JwtDto> register (@RequestBody UserRegisterDto registerDto){
        JwtDto jwtDto = userServiceImpl.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtDto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login (@RequestBody UserLoginDto loginDto){
        JwtDto jwtDto = userServiceImpl.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(jwtDto);
    }

    @GetMapping("/getId {id}")
    public ResponseEntity<User> getUserById (@PathVariable Long id){
        User user = userServiceImpl.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getUserAll (){
        List<User> users = userServiceImpl.getAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update {id}")
    public ResponseEntity<String> updateUser (@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        userServiceImpl.update(user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/delete {id}")
    public ResponseEntity<String> deleteUser (@PathVariable Long id){
        userServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully");
    }


}
