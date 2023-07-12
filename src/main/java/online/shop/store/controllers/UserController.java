package online.shop.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import online.shop.store.dto.entity.admin.Admin;
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
        if(userService.checkExistByEmailOrPhone(userRegister.getEmail(), userRegister.getPhone())){
            return new ResponseEntity<String>("ton tai tai khoan voi email hoac phone",HttpStatus.CONFLICT);
        }
        User user=userService.save(userRegister,new User());
        return new ResponseEntity<User>(user,HttpStatus.CREATED);
    }

    @GetMapping("/manage-user")
    public ResponseEntity<?> getAll(@RequestParam("page") int page, @RequestParam("total") int total){
        Page<User> pageUsers= userService.allUsers(page,total);
        if(page>pageUsers.getSize()-1){
            return new ResponseEntity<String>("not found",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<User>>(pageUsers, HttpStatus.OK);
    }
  
    
}
