package online.shop.store.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.products.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    
}
