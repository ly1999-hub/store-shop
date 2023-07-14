package online.shop.store.dto.entity.products;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductType {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productTypeName;

    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
    @JsonManagedReference // chiều chủ
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<ProductDetail> productsDetails;
    private Date createdAt;
    private Date updatedAt;
}
