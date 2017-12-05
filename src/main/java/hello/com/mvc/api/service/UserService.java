package hello.com.mvc.api.service;

import hello.com.mvc.api.dao.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserService implements UserDetailsService {
    @Autowired
    IUserDAO userRepository;

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        System.out.println("username is " + name);
        return userRepository.getUserByUsername(name);
    }
}
