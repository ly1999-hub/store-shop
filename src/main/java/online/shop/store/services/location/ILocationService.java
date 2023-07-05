package online.shop.store.services.location;

import online.shop.store.dto.RegisterLocation;
import online.shop.store.models.location.Distric;
import online.shop.store.models.location.Location;
import online.shop.store.models.location.Province;
import online.shop.store.models.location.Ward;

public interface ILocationService {
    Ward findWardById(Integer id);
    Distric finDistricById(Integer id);
    Province findProvinceById(Integer id);
    Location save(RegisterLocation registerLocation);
}
