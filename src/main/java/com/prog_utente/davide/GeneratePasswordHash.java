package com.prog_utente.davide;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePasswordHash {
    public static void main (String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password1 = encoder.encode("admin123");
        String password2 = encoder.encode("password123");
        String password3 = encoder.encode("password456");

        System.out.println("admin -> " + password1);
        System.out.println("password123 -> " + password2);
        System.out.println("password456 -> " + password3);
    }
}
