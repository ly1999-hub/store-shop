package online.shop.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterLocation {
    private String locationDetail;
    private Integer idWard;
    public RegisterLocation(String locationDetail,Integer idWard){
        this.locationDetail=locationDetail;
        this.idWard=idWard;
    }
}
