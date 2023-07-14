package online.shop.store.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import online.shop.store.dto.entity.products.Bill;

public interface BillRepository extends JpaRepository<Bill,Integer>{
    
}
