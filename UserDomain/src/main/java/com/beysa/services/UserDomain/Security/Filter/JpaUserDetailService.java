package com.beysa.services.UserDomain.Security.Filter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beysa.services.UserDomain.User.UserEntity;
import com.beysa.services.UserDomain.User.UserRepository;

@Service
public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<UserEntity> userOptinal = userRepository.findByUserName(username);
            if (userOptinal.isEmpty()) {
                throw new UsernameNotFoundException(String.format( "No se ha encontrado el Usuario %s", username));
            } 
            UserEntity user =  userOptinal.orElseThrow();

            List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role-> new SimpleGrantedAuthority(role.getName().toString()))
            .collect(Collectors.toList());

            return new User(user.getUsername(), 
            user.getPassword(),
            true,
            true,
            true, 
            true, 
            authorities);
    }
    
}
