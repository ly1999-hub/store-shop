package online.shop.store.dto.entity.products;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private String productDescription;
    private Integer codeSale;
    private Integer price;
    private Integer total;
    private List<String> images;
    
    @ManyToOne
    @JoinColumn(name="productType_id")
    private ProductType productType;
    
    private Date createdAt;
    private Date updatedAt;
}
