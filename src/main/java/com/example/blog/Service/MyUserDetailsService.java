package com.example.blog.Service;

import com.example.blog.Api.ApiException;
import com.example.blog.Model.User;
import com.example.blog.Repository.AuthRepository;
import com.example.blog.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=authRepository.findUserByUsername(username);
        if(user==null){
            throw new ApiException("wrong username or password");
        }
        return user;
    }
}
