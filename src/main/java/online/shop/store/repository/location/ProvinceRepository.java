package online.shop.store.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.location.Province;

public interface ProvinceRepository  extends JpaRepository<Province,Integer>{
    
}
