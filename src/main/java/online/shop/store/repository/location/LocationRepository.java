package online.shop.store.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.location.Location;

public interface LocationRepository extends JpaRepository<Location,Integer>{
}
