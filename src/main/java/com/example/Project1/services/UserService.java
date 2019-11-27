package com.example.Project1.services;

import org.springframework.stereotype.Service;

@Service
public class UserService /*implements UserDetailsService*/ {
    /*@Autowired
    private UserRepository repository;


    public UserDetails loadUserByUsername(String username,String password) throws UsernameNotFoundException {
            Objects.requireNonNull(username);
            Objects.requireNonNull(password);
            try {
                return findUserWithName(username,password);
            }catch(UsernameNotFoundException e){
                throw new UsernameNotFoundException("User not found");
            }

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);

        try {
            return findUserWithName(username);
        }catch(UsernameNotFoundException e){
            throw new UsernameNotFoundException("User not found");
        }

    }



    private User findUserWithName(String username,String password){
        Iterable<User> allUsers=repository.findAll();
        for(User u : allUsers){
            if (u.getUsername().equals(username)&&u.getPassword().equals(password)) return u;
        }
        return null;
    }
    private User findUserWithName(String username){
        Iterable<User> allUsers=repository.findAll();
        for(User u : allUsers){
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }
*/
}
