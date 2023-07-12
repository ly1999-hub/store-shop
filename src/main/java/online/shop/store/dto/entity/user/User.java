package online.shop.store.dto.entity.user;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import online.shop.store.dto.entity.Role;
import online.shop.store.dto.entity.products.Bill;

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
    private String password;
    private String avatar;
    @Column(name = "`lock`")
    private Boolean lock;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference // chiều con
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Role role;
    
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    @JsonManagedReference // chiều chủ
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<Bill> bills;


    private String location;
    private Date createdAt;
    private Date updatedAt;
}
