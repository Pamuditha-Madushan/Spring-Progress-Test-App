package com.progress.test.service.process;

import com.progress.test.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    UserDto getUserByEmail(String email);

   /* UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    */
}
