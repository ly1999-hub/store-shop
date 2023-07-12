package online.shop.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.user.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    
}
