package com.progress.test.api;

//import com.progress.test.dto.LoginRequestDto;
import com.progress.test.dto.UserDto;
import com.progress.test.dto.response.custom.CustomResponse;
import com.progress.test.dto.response.jwt.JwtResponse;
import com.progress.test.entity.User;
//import com.progress.test.service.process.AuthService;
import com.progress.test.service.process.UserService;
import com.progress.test.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {

        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomResponse<UserDto>> registerUser(@RequestBody UserDto userDto) {

        UserDto createdUser =  userService.registerUser(userDto);

        CustomResponse<UserDto> customResponse = new CustomResponse<>();
        customResponse.setResponseCode(HttpStatus.CREATED.value());
        customResponse.setSuccess(true);
        customResponse.setMessage("User created successfully ...");
        customResponse.setData(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(customResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDto.getEmail());

        return ResponseEntity.ok(token);

    }



    @GetMapping("/welcome")
    public String welcome() {
        return "Hello World";
    }

}
