package online.shop.store.dto.entity.admin;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DtoLoginAdmin {
    private String email;
    private String password;
}
