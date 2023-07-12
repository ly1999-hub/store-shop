package online.shop.store.services.user;

import online.shop.store.dto.entity.user.User;
import online.shop.store.dto.entity.user.UserRegister;

public interface IUserService {
    User save(UserRegister userRegister,User user);
}
