package sec.project.config;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SignupRepository signupRepository;

    @PostConstruct
    public void init() {
        Signup tester = new Signup("testman", "NoStreet", "123");
        signupRepository.save(tester);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Signup singup = signupRepository.findByName(username);
        if (singup == null) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                username,
                singup.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
