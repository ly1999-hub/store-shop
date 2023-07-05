package online.shop.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.models.Role;
import online.shop.store.models.RoleName;
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleName roleName);
}
