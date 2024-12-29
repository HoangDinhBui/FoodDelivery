package com.cybersoft.osahaneat.Service;

import com.cybersoft.osahaneat.Dto.RestaurantDTO;
import com.cybersoft.osahaneat.Entity.RatingRestaurant;
import com.cybersoft.osahaneat.Entity.Restaurant;
import com.cybersoft.osahaneat.Repository.RestaurantRepository;
import com.cybersoft.osahaneat.Service.imp.FileServiceImp;
import com.cybersoft.osahaneat.Service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<RestaurantDTO> getHomePageRestaurant() {
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0,6);//You can call sort function after pageSize
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);

        for(Restaurant data : listData) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setSubtitle(data.getSubtitle());
            restaurantDTO.setFreeship(data.getFreeship());
            restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurant()));

            restaurantDTOS.add(restaurantDTO);
        }
        return restaurantDTOS;
    }

    private double calculatorRating(Set<RatingRestaurant> listRating) {
        double totalPoint = 0;
        for(RatingRestaurant data : listRating) {
            totalPoint += data.getRatePoint();
        }

        return totalPoint/listRating.size();
    }
}
