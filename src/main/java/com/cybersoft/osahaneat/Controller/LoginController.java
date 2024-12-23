package com.cybersoft.osahaneat.Controller;

import com.cybersoft.osahaneat.Payload.Request.SignUpRequest;
import com.cybersoft.osahaneat.Payload.ResponseData;
import com.cybersoft.osahaneat.Service.LoginService;
import com.cybersoft.osahaneat.Service.imp.LoginServiceImp;
import com.cybersoft.osahaneat.Utils.JwtUtilsHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceImp loginServiceImp;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password) {
        ResponseData responseData = new ResponseData();

        if(loginServiceImp.checkLogin(username, password)) {
            String token = jwtUtilsHelper.generateToken(username); //Don't use password to encode
            responseData.setData(token);
        } else {
            responseData.setData("");
            responseData.setSuccess(false);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        ResponseData responseData = new ResponseData();

        responseData.setData(loginServiceImp.addUser(signUpRequest));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
