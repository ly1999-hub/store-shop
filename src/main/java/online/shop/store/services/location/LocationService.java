package online.shop.store.services.location;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import online.shop.store.dto.RegisterLocation;
import online.shop.store.dto.entity.location.Distric;
import online.shop.store.dto.entity.location.Location;
import online.shop.store.dto.entity.location.Province;
import online.shop.store.dto.entity.location.Ward;
import online.shop.store.repository.location.DistrictRepository;
import online.shop.store.repository.location.LocationRepository;
import online.shop.store.repository.location.ProvinceRepository;
import online.shop.store.repository.location.WardRepository;

@Service
public class LocationService implements ILocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    
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
    public Page<Province> allProvince(int page,int total){
        PageRequest pageDetail=PageRequest.of(page, total, Sort.by("nameProvince"));
        Page<Province> allprovince=provinceRepository.findAll(pageDetail);
        return allprovince;
    }

    @Override
    public Page<Distric> allDistric(int page, int total) {
        PageRequest pageDetail=PageRequest.of(page,total,Sort.by("province"));
        Page<Distric> allDistrict= districtRepository.findAll(pageDetail);
        return allDistrict;
    }

    @Override
    public Page<Ward> allWard(int page, int total) {
        PageRequest pageDetail=PageRequest.of(page,total,Sort.by("district"));
        Page<Ward> allWard= wardRepository.findAll(pageDetail);
        return allWard;
    }
    
}
