package com.nikhil.pricetracker.Service;

import com.nikhil.pricetracker.Dto.UserDto;
import com.nikhil.pricetracker.Exceptions.EmailAlreadyExistsException;
import com.nikhil.pricetracker.Models.User;
import com.nikhil.pricetracker.Repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.security.PrivateKey;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    public User createUser(UserDto userDto) {
        String email = userDto.getEmail();
        Optional<User> existingUser = userRepo.findByEmail(email);
        if(existingUser.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        String hashedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = User.builder()
                    .name(userDto.getName())
                    .email(userDto.getEmail())
                    .password(hashedPassword)
                    .build();

        return userRepo.save(user);
    }

    public List<User> getAllUser(){
        return userRepo.findAll();
    }
}
