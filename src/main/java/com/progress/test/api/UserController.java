package com.progress.test.api;

//import com.progress.test.dto.LoginRequestDto;
import com.progress.test.dto.UserDto;
import com.progress.test.dto.response.custom.CustomResponse;
import com.progress.test.dto.response.jwt.JwtResponse;
import com.progress.test.entity.User;
//import com.progress.test.service.process.AuthService;
import com.progress.test.service.process.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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

    /*
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        String token = authService.authenticateUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        JwtResponse jwtResponse = new JwtResponse(token);
        return ResponseEntity.ok(jwtResponse);
    }

     */

    @GetMapping("/welcome")
    public String welcome() {
        return "Hello World";
    }

}
