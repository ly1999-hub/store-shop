package online.shop.store.controllers.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import online.shop.store.dto.RegisterLocation;
import online.shop.store.dto.entity.location.Location;
import online.shop.store.dto.entity.location.Province;
import online.shop.store.services.location.LocationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping(value="/register")
    public ResponseEntity<?> register(@RequestBody RegisterLocation registerLocation) {
        Location location=locationService.save(registerLocation);
        return new ResponseEntity<Location>(location,HttpStatus.CREATED);
    }

    @GetMapping("/all-province")
    public ResponseEntity<?> allDistric(@RequestParam int page,@RequestParam int total){
        Page<Province> pageProvince=locationService.allProvince(page, total);
        if(page>pageProvince.getTotalPages()-1){
            return new ResponseEntity<String>("not found",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Province>>(pageProvince, HttpStatus.OK);
    }

}
