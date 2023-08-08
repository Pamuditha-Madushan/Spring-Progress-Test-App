package com.progress.test.api;

//import com.progress.test.dto.LoginRequestDto;

import com.progress.test.dto.UserDto;
import com.progress.test.dto.response.custom.CustomResponse;
import com.progress.test.dto.response.jwt.JwtResponse;
import com.progress.test.entity.User;
//import com.progress.test.service.process.AuthService;
import com.progress.test.service.impl.JwtUserDetailsServiceImpl;
import com.progress.test.service.process.UserService;
import com.progress.test.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/auth")
public class UserController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    private final JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    public UserController(UserService userService,
                          JwtTokenUtil jwtTokenUtil,
                          AuthenticationManager authenticationManager,
                          JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl
    ) {

        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsServiceImpl = jwtUserDetailsServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomResponse<UserDto>> registerUser(@RequestBody UserDto userDto) {

        try {
            UserDto createdUser = userService.registerUser(userDto);
            CustomResponse<UserDto> customResponse = createSuccessCustomResponse(createdUser, HttpStatus.CREATED, "User created successfully ...");
            return ResponseEntity.status(HttpStatus.CREATED).body(customResponse);

        } catch (IllegalArgumentException illegalArgumentException) {
            CustomResponse<UserDto> customResponse = createErrorCustomResponse(HttpStatus.BAD_REQUEST, illegalArgumentException.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customResponse);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse<String>> loginUser(@RequestBody UserDto userDto) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDto existingUserDto = userService.getUserByEmail(userDto.getEmail());

            if (existingUserDto == null) {
                JwtResponse<String> jwtResponse = createErrorJwtResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials !!");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jwtResponse);
            }

            UserDetails userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(userDto.getEmail());
            String token = jwtTokenUtil.generateToken(userDetails, existingUserDto);


            JwtResponse<String> jwtResponse = createSuccessJwtResponse(token, HttpStatus.OK, "User Login Successful ...");
            return ResponseEntity.ok(jwtResponse);

        } catch (AuthenticationException authenticationException) {
            JwtResponse<String> jwtResponse = createErrorJwtResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials !!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jwtResponse);
        }

    }


    @GetMapping("/welcome")
    public String welcome() {
        return "Hello World";
    }

    private <T> CustomResponse<T> createSuccessCustomResponse(T data, HttpStatus status, String message) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setResponseCode(status.value());
        customResponse.setSuccess(true);
        customResponse.setMessage(message);
        customResponse.setData(data);
        return customResponse;
    }

    private <T> CustomResponse<T> createErrorCustomResponse(HttpStatus status, String message) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setResponseCode(status.value());
        customResponse.setSuccess(false);
        customResponse.setMessage(message);


        if (status != HttpStatus.OK) {
            customResponse.setData(null);
        }

        return customResponse;
    }

    private <T> JwtResponse<T> createSuccessJwtResponse(T token, HttpStatus status, String message) {
        JwtResponse<T> jwtResponse = new JwtResponse<>();
        jwtResponse.setResponseCode(status.value());
        jwtResponse.setSuccess(true);
        jwtResponse.setMessage(message);
        jwtResponse.setToken(token);
        return jwtResponse;
    }

    private <T> JwtResponse<T> createErrorJwtResponse(HttpStatus status, String message) {
        JwtResponse<T> jwtResponse = new JwtResponse<>();
        jwtResponse.setResponseCode(status.value());
        jwtResponse.setSuccess(true);
        jwtResponse.setMessage(message);
        jwtResponse.setToken(null);
        return jwtResponse;

    }


}
