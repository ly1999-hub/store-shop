package online.shop.store.dto.entity.products;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WareHouseRegister {
    @NotEmpty(message = "name khong duoc bo trong")
    private String wareHouseName;
    @NotEmpty(message = "vị trí cụ thể khong duoc bo trong")
    private String locationDetail;
    @NotEmpty(message = "chọn địa điểm")
    private Integer idWard;
}
