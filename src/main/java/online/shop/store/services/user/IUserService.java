package online.shop.store.services.user;

import java.util.List;

import org.springframework.data.domain.Page;

import online.shop.store.dto.entity.user.User;
import online.shop.store.dto.entity.user.UserRegister;

public interface IUserService {
    User save(UserRegister userRegister,User user);
    Boolean checkExistByEmailOrPhone(String email,String phone);
    Page<User> allUsers(int page,int total);
}
