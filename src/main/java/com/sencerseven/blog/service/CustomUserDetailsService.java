package com.sencerseven.blog.service;

import com.sencerseven.blog.command.UsersCommand;
import com.sencerseven.blog.converter.UsersToUsersCommandConverter;
import com.sencerseven.blog.domain.Users;
import com.sencerseven.blog.model.CustomUserDetails;
import com.sencerseven.blog.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    UsersRepository usersRepository;
    UsersToUsersCommandConverter usersToUsersCommandConverter;

    public CustomUserDetailsService(UsersRepository usersRepository, UsersToUsersCommandConverter usersToUsersCommandConverter) {
        this.usersRepository = usersRepository;
        this.usersToUsersCommandConverter = usersToUsersCommandConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userOptional = usersRepository.findByUserName(username);

        userOptional.orElseThrow(() ->new UsernameNotFoundException("user not found"));

        Optional<UsersCommand> usersCommandOptional = Optional.of(usersToUsersCommandConverter.convert(userOptional.get()));

        return usersCommandOptional.map(CustomUserDetails::new).get();

    }
}
