package com.cybersoft.osahaneat.Service.imp;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface RestaurantServiceImp {
    boolean insertRestaurant( MultipartFile file,
                             String title,
                             String subtitle,
                             String description,
                             boolean is_freeship,
                             String address,
                             String open_date);
}
