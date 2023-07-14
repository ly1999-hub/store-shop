package online.shop.store.services.product;

import online.shop.store.dto.entity.products.WareHouse;
import online.shop.store.dto.entity.products.WareHouseRegister;

public interface IWareHouseService {
    WareHouse save(WareHouseRegister wareHouse,WareHouse warehouse);
}
