package online.shop.store.dto.entity.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegister {
    @NotEmpty(message = "ten khong duoc bo trong")
    private String userName;
    private String email;
    @NotEmpty(message = "xa khong duoc bo trong")
    @NotEmpty(message = "mat khau khong duoc bo trong")
    private String password;
    @NotEmpty(message = "so dien thoai name khong duoc bo trong")
    private String phone;
    @NotEmpty(message = "email khong duoc bo trong")
    @Email(message="dinh dang la email")
    
    private Integer idWard;
    private String locationDetail;
}
