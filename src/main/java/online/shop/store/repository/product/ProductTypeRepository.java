package online.shop.store.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.products.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType,Integer> {
    
}
