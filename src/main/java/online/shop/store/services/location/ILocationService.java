package online.shop.store.services.location;

import org.springframework.data.domain.Page;

import online.shop.store.dto.RegisterLocation;
import online.shop.store.dto.entity.location.Distric;
import online.shop.store.dto.entity.location.Location;
import online.shop.store.dto.entity.location.Province;
import online.shop.store.dto.entity.location.Ward;

public interface ILocationService {
    Ward findWardById(Integer id);
    Location save(RegisterLocation registerLocation);
    Page<Province> allProvince(int page,int total);
    Page<Distric> allDistric(int page,int total);
    Page<Ward> allWard(int page,int total);
}
