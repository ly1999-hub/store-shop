package online.shop.store.dto.entity.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
public class RegisterAdmin {
    @NotEmpty(message = "name khong duoc bo trong")
    private String nameAdmin;
    @NotEmpty(message = "email khong duoc bo trong")
    @Email(message = "Email không hợp lệ")
    private String email;
    @NotEmpty(message = "mật khẩu khong duoc bo trong")
    private String password;
    @NotEmpty(message = "tạo quyền")
    private String[] listRole;
    @NotEmpty(message = "địa chỉ khong duoc bo trong")
    private String locationDetail;
    private Integer idWard;
}
