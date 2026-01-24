package com.andrewhejl.plannro.auth;

public class AuthResponse 
{
    public long id;
    public String username;
    public String email;
    
    public AuthResponse(long id, String username, String email)
    {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
