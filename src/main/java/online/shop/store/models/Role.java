package online.shop.store.models;

import java.util.Set;

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
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
        name = "admin_role",
        joinColumns=@JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name="admin_id")
    )
    private Set<Admin> admins;

    public Role(RoleName roleName){
        this.roleName=roleName;
    }
}
