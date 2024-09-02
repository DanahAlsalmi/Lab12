package com.example.blog.Controller;

import com.example.blog.Model.User;
import com.example.blog.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getUser() {
        return ResponseEntity.status(200).body(authService.findAll());
    }
    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody User user) {
        authService.register(user);
        return ResponseEntity.status(200).body("User registered successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user) {
        authService.update(id,user);
        return ResponseEntity.status(200).body("User updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        authService.delete(id);
        return ResponseEntity.status(200).body("User deleted successfully");
    }
}
