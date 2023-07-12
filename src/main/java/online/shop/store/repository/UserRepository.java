package online.shop.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.user.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByEmailOrPhone(String email,String phone);
}
