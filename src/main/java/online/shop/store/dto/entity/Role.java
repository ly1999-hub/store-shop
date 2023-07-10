package online.shop.store.dto.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Enumerated(EnumType.STRING)
    private RoleName roleName ;
    
    // ----postgresql----
    // @JsonIgnore
    // @ManyToMany(cascade=CascadeType.ALL)
    // @JoinTable(
    //     name = "admin_role",
    //     joinColumns=@JoinColumn(name = "role_id"),
    //     inverseJoinColumns = @JoinColumn(name="admin_id")
    // )
    // @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    // @ToString.Exclude // Khoonhg sử dụng trong toString()
    // private Set<Admin> admins;

    public Role(RoleName roleName){
        this.roleName=roleName;
    }

    public String getRoleName(){
        return this.roleName.toString();
    }
}
