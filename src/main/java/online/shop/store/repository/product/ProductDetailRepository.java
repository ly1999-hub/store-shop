package online.shop.store.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.products.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail,Integer>{
    
}
