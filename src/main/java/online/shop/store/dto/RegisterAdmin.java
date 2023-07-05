package online.shop.store.dto;

import lombok.*;

@Getter
@Setter
public class RegisterAdmin {
    private String nameAdmin;
    private String email;
    private String password;
    private String[] listRole;
    private String locationDetail;
    private Integer idWard;
}
