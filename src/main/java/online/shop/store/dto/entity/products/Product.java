package online.shop.store.dto.entity.products;

import java.sql.Date;

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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="productDetail_id")
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name="warehouse_id")
    private WareHouse wareHouse;
    
    @ManyToOne
    @JoinColumn(name="bill_id")
    private Bill bill;

    private Date createdAt;
    private Date updatedAt;
}
