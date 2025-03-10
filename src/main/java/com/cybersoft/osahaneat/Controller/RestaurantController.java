package com.cybersoft.osahaneat.Controller;

import com.cybersoft.osahaneat.Payload.ResponseData;
import com.cybersoft.osahaneat.Service.FileService;
import com.cybersoft.osahaneat.Service.imp.FileServiceImp;
import com.cybersoft.osahaneat.Service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    RestaurantServiceImp restaurantServiceImp;

    @PostMapping()
    public ResponseEntity<?> createRestarant(@RequestParam MultipartFile file,
                                             @RequestParam String title,
                                             @RequestParam String subtitle,
                                             @RequestParam String description,
                                             @RequestParam boolean is_freeship,
                                             @RequestParam String address,
                                             @RequestParam String open_date) {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = restaurantServiceImp.insertRestaurant(file,
                title, subtitle, description, is_freeship, address, open_date);
        responseData.setData(isSuccess);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getHomeRestarant() {
        ResponseData responseData = new ResponseData();

        responseData.setData(restaurantServiceImp.getHomePageRestaurant());

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileRestarant(@PathVariable String filename) {
        Resource resource = fileServiceImp.loadFile(filename);

        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + resource.getFilename() + "\"").body(resource);
    }
}
