package online.shop.store.dto.entity.user;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import online.shop.store.dto.entity.Role;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    private String userName;
    private String email;
    private String phone;
    private String password;
    private String avatar;
    @Column(name = "`lock`")
    private Boolean lock;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Role role;

    private String location;
    private Date createdAt;
    private Date updatedAt;
}
