package online.shop.store.dto.entity.location;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "districs")
public class Distric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nameDistric;
    @ManyToOne
    @JoinColumn(name="province_id")
    @JsonBackReference // chiều con
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Province province;


    @OneToMany(mappedBy = "distric", cascade = CascadeType.ALL)
    @JsonManagedReference // chiều chủ
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Set<Ward> wards;

    public Distric(String nameDistric,Province province){
        this.nameDistric=nameDistric;
        this.province=province;
    }
    
}
