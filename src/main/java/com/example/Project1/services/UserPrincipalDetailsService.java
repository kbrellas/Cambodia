package com.example.Project1.services;

import com.example.Project1.models.Error;
import com.example.Project1.models.CustomUser;
import com.example.Project1.models.UserPrincipal;
import com.example.Project1.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Service
    public class UserPrincipalDetailsService implements UserDetailsService {
        private UserRepository userRepository;



        public UserPrincipalDetailsService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }


        @Override
        public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

            CustomUser user = this.userRepository.findByUsername(s);
            UserPrincipal userPrincipal = new UserPrincipal(user);

            return userPrincipal;
        }


    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity handleUsernameNotFoundException(){
        return new ResponseEntity<>(new Error(0,"Not Found","User does not exist"),null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

