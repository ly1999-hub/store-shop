package online.shop.store.repository.location;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.location.Ward;

public interface WardRepository extends  JpaRepository<Ward,Integer>{
    Optional<Ward> findById(Integer id);
}
