package online.shop.store.dto.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Enumerated(EnumType.STRING)
    RoleName roleName ;
    
    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
        name = "admin_role",
        joinColumns=@JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name="admin_id")
    )
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Set<Admin> admins;

    public Role(RoleName roleName){
        this.roleName=roleName;
    }
}
