package online.shop.store.dto.entity.admin;

import lombok.Getter;
import lombok.Setter;
import online.shop.store.dto.entity.location.Location;

@Getter
@Setter
public class AdminResponse {
    private Integer id;
    private String nameAdmin;
    private String email;
    private String avatar;
    private Location location;
}
