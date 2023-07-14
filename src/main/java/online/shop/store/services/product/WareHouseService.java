package online.shop.store.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import online.shop.store.dto.entity.products.WareHouse;
import online.shop.store.dto.entity.products.WareHouseRegister;
import online.shop.store.repository.product.WareHouseRepository;
import online.shop.store.services.location.LocationService;
import online.shop.store.utils.time.TimeNow;

@Service
@RequiredArgsConstructor
public class WareHouseService implements IWareHouseService{

    @Autowired
    private LocationService locationService;
    @Autowired
    private WareHouseRepository wareHouseRepository;
    private final TimeNow timeNow;
    @Override
    public WareHouse save(WareHouseRegister wareHouseRegister,WareHouse warehouse) {
        warehouse.setWareHouseName(wareHouseRegister.getWareHouseName());
        String locationToString= locationService.getLocationToString(wareHouseRegister.getIdWard());
        warehouse.setLocation(wareHouseRegister.getLocationDetail()+","+locationToString);
        warehouse.setCreatedAt(timeNow.getTimeNow());
        warehouse.setUpdatedAt(timeNow.getTimeNow());
        WareHouse newWareHouse=wareHouseRepository.save(warehouse);
        return newWareHouse;
    }
    
}
