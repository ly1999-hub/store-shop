package online.shop.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.shop.store.dto.entity.user.User;
import online.shop.store.dto.entity.user.UserRegister;
import online.shop.store.services.user.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegister userRegister){
        User user=userService.save(userRegister,new User());
        return new ResponseEntity<User>(user,HttpStatus.CREATED);
    }
    
}
