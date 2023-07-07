package online.shop.store.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.location.Distric;

public interface DistrictRepository extends JpaRepository<Distric,Integer> {
    
}
