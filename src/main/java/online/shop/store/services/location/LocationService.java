package online.shop.store.services.location;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.shop.store.dto.RegisterLocation;
import online.shop.store.models.location.Distric;
import online.shop.store.models.location.Location;
import online.shop.store.models.location.Province;
import online.shop.store.models.location.Ward;
import online.shop.store.repository.location.LocationRepository;
import online.shop.store.repository.location.WardRepository;

@Service
public class LocationService implements ILocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private WardRepository wardRepository;
    @Override
    public Location save(RegisterLocation registerLocation) {
        Ward ward=findWardById(registerLocation.getIdWard());
        Location location=new Location();
        if(ward!=null){
            location.setLocationDetail(registerLocation.getLocationDetail());
            location.setNameWard(ward.getNameWard());
            Distric distric=ward.getDistric();
            location.setNameDistrict(distric.getNameDistric());
            Province province=distric.getProvince();
            location.setNameProvince(province.getNameProvince());
        }
        Location newLocation=locationRepository.save(location);
        return newLocation;
    }

    @Override
    public Ward findWardById(Integer id) {
        Optional<Ward> ward=wardRepository.findById(id);
        if(ward.isEmpty()){
            new NullPointerException("không tìm thấy Ward by id");
            return null;
        }else{
            return ward.orElse(null);
        }
    }

    @Override
    public Distric finDistricById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finDistricById'");
    }

    @Override
    public Province findProvinceById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findProvinceById'");
    }
    
}
