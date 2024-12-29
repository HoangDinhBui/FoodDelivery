package com.cybersoft.osahaneat.Service.imp;

import com.cybersoft.osahaneat.Dto.RestaurantDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantServiceImp {
    boolean insertRestaurant( MultipartFile file,
                             String title,
                             String subtitle,
                             String description,
                             boolean is_freeship,
                             String address,
                             String open_date);
    public List<RestaurantDTO> getHomePageRestaurant();
}
