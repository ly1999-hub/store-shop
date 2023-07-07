package online.shop.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer>{
    Optional<Admin> findById(Integer id);

    Boolean existsByEmail(String email);
    
}
