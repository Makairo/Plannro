package com.andrewhejl.plannro.auth;

import com.andrewhejl.plannro.user.User;
import com.andrewhejl.plannro.user.UserRepo;
import com.andrewhejl.plannro.auth.*;

import javax.print.DocFlavor.READER;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    
    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder)
    {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest req)
    {
        if(userRepo.findByUsername(req.username).isPresent()) { throw new RuntimeException("Username already taken."); }
        if(userRepo.findByEmail(req.email).isPresent()) { throw new RuntimeException("Email already in use."); }

        String hash = passwordEncoder.encode(req.password);

        User newUser = new User(req.username, req.email, hash);
        userRepo.save(newUser);

        return new AuthResponse(newUser.getId(), newUser.getUsername(), newUser.getEmail());
    }

    public AuthResponse login(LoginRequest req)
    {
        User user =    userRepo.findByUsername(req.username)
        .or(() ->      userRepo.findByEmail(req.username))
        .orElseThrow(() -> new RuntimeException("Invalid Credentials."));

        if(!passwordEncoder.matches(req.password, user.getPasswordHash())) { new RuntimeException("Invalid Credentials."); }
        if(!user.getEnabled()) { new RuntimeException("User Disabled. Contact Support."); }

        return new AuthResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
