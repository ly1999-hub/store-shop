package online.shop.store.dto.entity.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.SerializableString;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Admin implements Serializable,UserDetails{
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
    @Column(name = "`lock`")
    private Boolean lock;

    // @JsonIgnore
    // @ManyToMany(mappedBy = "admins")
    // @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    // @ToString.Exclude // Khoonhg sử dụng trong toString()
    @ManyToMany(fetch = FetchType.EAGER  , cascade = CascadeType.PERSIST)
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonBackReference // chiều con
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Location location;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<GrantedAuthority> authority=new ArrayList<>();
       this.roles.forEach(role->authority.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authority;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
