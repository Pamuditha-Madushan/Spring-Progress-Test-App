package com.progress.test.service.impl;

import com.progress.test.dto.UserDto;
import com.progress.test.entity.User;
import com.progress.test.repo.UserRepository;
import com.progress.test.service.process.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new IllegalArgumentException("Email is already in use");
        }

        String hashedPassword = passwordEncoder.encode(userDto.getPassword());

        User newUser = new User();

        newUser.setUserId(generateRandomId());
        newUser.setEmail(userDto.getEmail());
        newUser.setName(userDto.getName());
        newUser.setPassword(hashedPassword);

        newUser = userRepository.save(newUser);


        return  convertToDto(newUser);
    }

    private String generateRandomId () {
        return UUID.randomUUID().toString();
    }


    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());

        return userDto;
    }

   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with this email : " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), new ArrayList<>()
        );
    }

    */
}
