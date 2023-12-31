package com.nikhil.pricetracker.Controllers;

import com.nikhil.pricetracker.Dto.LoginDto;
import com.nikhil.pricetracker.Dto.UserDto;
import com.nikhil.pricetracker.Exceptions.InvalidCustomerException;
import com.nikhil.pricetracker.Models.User;
import com.nikhil.pricetracker.Service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping("/listAllUsers")
    public List<User> getUsers(){
        return userService.getAllUser();
    }

    @PostMapping("/create")
    public User registerUser(@RequestBody UserDto request){
        validate(request);
        return userService.createUser(request);
    }
    private void validate(UserDto userDto) {
        if(userDto.getEmail() == null){
            throw new InvalidCustomerException();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginDto loginDto){
        if(userService.doLogin(loginDto)){
            return ResponseEntity.status(HttpServletResponse.SC_OK).body("Authentication successful");
        }
        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Unauthorized");
    }
}
