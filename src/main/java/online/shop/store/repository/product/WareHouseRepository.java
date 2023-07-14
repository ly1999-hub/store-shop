package online.shop.store.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.products.WareHouse;

public interface WareHouseRepository extends JpaRepository<WareHouse,Integer> {
    
}
