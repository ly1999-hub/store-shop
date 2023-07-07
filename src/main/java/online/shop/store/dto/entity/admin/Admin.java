package online.shop.store.dto.entity.admin;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import online.shop.store.dto.entity.Role;
import online.shop.store.dto.entity.location.Location;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name khong duoc bo trong")
    private String nameAdmin;
    @NotEmpty(message="email khong duoc bo trong")
    private String email;
    @NotEmpty(message = "mat khau khong duoc de trong")
    private String password;
    private String avatar;
    @Column(name = "lock", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean lock;

    @JsonIgnore
    @ManyToMany(mappedBy = "admins")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonBackReference // chiều con
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Location location;
}
