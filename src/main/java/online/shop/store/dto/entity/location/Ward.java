package online.shop.store.dto.entity.location;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "wards")
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameWard;

    @ManyToOne
    @JoinColumn(name = "distric_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Distric distric;

    public Ward(String nameWard,Distric distric){
        this.nameWard=nameWard;
        this.distric=distric;
    }
}
