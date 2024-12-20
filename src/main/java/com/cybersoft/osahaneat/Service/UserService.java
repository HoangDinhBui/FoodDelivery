package com.cybersoft.osahaneat.Service;

import com.cybersoft.osahaneat.Dto.UserDTO;
import com.cybersoft.osahaneat.Entity.Users;
import com.cybersoft.osahaneat.Repository.UserRepository;
import com.cybersoft.osahaneat.Service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> listUser = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for(Users users : listUser) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(users.getId());
            userDTO.setUsername(users.getUsername());
            userDTO.setPassword(users.getPassword());
            userDTO.setFullname(users.getFullname());
            userDTO.setCreateDate(users.getCreateDate());

            userDTOList.add(userDTO);
        }
        return userDTOList;
    }
}
