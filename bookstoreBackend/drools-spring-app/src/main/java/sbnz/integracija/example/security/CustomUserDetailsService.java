package sbnz.integracija.example.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.user.UserRepository;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        var authorities = AuthorityUtils.createAuthorityList("ROLE_" + "BASIC");
        authorities
                .forEach(role -> {
                    grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
                });

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
