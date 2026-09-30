package com.matt1479.securingwebitems;

import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service 
public class UserService {
    
    public Map<String, UserDetails> getUsers(PasswordEncoder encoder) {
        return Map.of(
            "sampleuser",
            User.withUsername("sampleuser")
                .password(encoder.encode("sampleuserpassword"))
                .roles("USER")
                .build(),
            
            "sampleadmin",
            User.withUsername("sampleadmin")
                .password(encoder.encode("sampleadminpassword"))
                .roles("ADMIN")
                .build()
        );
    }

}
