package online.shop.store.dto.entity.location;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import online.shop.store.dto.entity.Admin;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "tên địa điểm không được bỏ trống")
    private String locationDetail;
    private String nameWard;
    private String nameDistrict;
    private String nameProvince;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @JsonManagedReference // chiều chủ
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<Admin> admin;
    
    public Location(String locationDetail,String nameWard,String nameDistric,String nameProvince){
        this.locationDetail=locationDetail;
        this.nameWard=nameWard;
        this.nameDistrict=nameDistric;
        this.nameProvince=nameProvince;
    }
}
