package com.progress.test.service.process;

import com.progress.test.dto.UserDto;
import com.progress.test.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    UserDto getUserByEmail(String email);

    User getUserById(String id);


}
