package online.shop.store.controllers.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.shop.store.dto.entity.products.WareHouse;
import online.shop.store.dto.entity.products.WareHouseRegister;
import online.shop.store.services.product.WareHouseService;

@RestController
@RequestMapping("api/v1/ware-house/no-authen")
public class WareHouseController {
    @Autowired
    private WareHouseService wareHouseService;

    @PostMapping("/create")
    public ResponseEntity<?> createWareHouse(@RequestBody WareHouseRegister wareHouseRegister){
        WareHouse newWareHouse=wareHouseService.save(wareHouseRegister, new WareHouse());
        return new ResponseEntity<WareHouse>(newWareHouse,HttpStatus.CREATED);
    }

    
}
