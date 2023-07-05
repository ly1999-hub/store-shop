package online.shop.store.controllers.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.shop.store.dto.RegisterLocation;
import online.shop.store.models.location.Location;
import online.shop.store.services.location.LocationService;
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
}
