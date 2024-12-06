package com.cybersoft.osahaneat.Service.imp;

import com.cybersoft.osahaneat.Dto.UserDTO;
import com.cybersoft.osahaneat.Payload.Request.SignUpRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUser();
    boolean checkLogin(String username, String password);
    boolean addUser(SignUpRequest signUpRequest);
}
