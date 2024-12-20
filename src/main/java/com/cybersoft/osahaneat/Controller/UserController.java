package com.cybersoft.osahaneat.Controller;

import com.cybersoft.osahaneat.Service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/getAllUsers")
    ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userServiceImp.getAllUsers(), HttpStatus.OK);
    }
}
