package online.shop.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.admin.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer>{
    Optional<Admin> findById(Integer id);
    Optional<Admin> findByEmail(String email);
    Boolean existsByEmail(String email);
    List<Admin> findByNameAdminContaining(String adminName);
}
