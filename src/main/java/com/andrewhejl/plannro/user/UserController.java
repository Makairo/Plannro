package com.andrewhejl.plannro.user;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users")
public class UserController 
{
    private final UserRepo repo;

    public UserController(UserRepo repo) { this.repo = repo; }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id)
    {
        return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
