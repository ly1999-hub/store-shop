package online.shop.store.dto.entity.products;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import online.shop.store.dto.entity.Role;
import online.shop.store.dto.entity.user.User;

@Entity
public class Bill {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference // chiều con
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private User user;
}
