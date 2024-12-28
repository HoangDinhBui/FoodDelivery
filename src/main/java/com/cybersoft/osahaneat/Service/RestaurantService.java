package com.cybersoft.osahaneat.Service;

import com.cybersoft.osahaneat.Entity.Restaurant;
import com.cybersoft.osahaneat.Repository.RestaurantRepository;
import com.cybersoft.osahaneat.Service.imp.FileServiceImp;
import com.cybersoft.osahaneat.Service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RestaurantService implements RestaurantServiceImp {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FileServiceImp fileServiceImp;

    @Override
    public boolean insertRestaurant(MultipartFile file,
                                    String title,
                                    String subtitle,
                                    String description,
                                    boolean is_freeship,
                                    String address,
                                    String open_date) {
        boolean isInserSuccess = false;
        try {
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if(isSaveFileSuccess) {
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubtitle((subtitle));
                restaurant.setDesc(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setFreeship(is_freeship);
                restaurant.setAddress(address);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date openDate = simpleDateFormat.parse(open_date);
                restaurant.setOpenDate(openDate);

                restaurantRepository.save(restaurant);
                isInserSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("Error insert restaurant " + e.getMessage());
        }
        return isInserSuccess;
    }
}
