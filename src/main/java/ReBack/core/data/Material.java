package ReBack.core.data;

import ReBack.core.dto.MaterialDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @SequenceGenerator(name = "material_seq_generator",
            sequenceName = "material_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq_generator")
    private Long materialCode;

    @Column(length=30, nullable = false)
    private String materialName;

//    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL) // (1)
//    @JoinColumn(name="material_code")
//    private List<Product> products = new ArrayList<>();

    public MaterialDTO toMaterial() {
        return MaterialDTO.builder()
                .materialCode(this.materialCode)
                .materialName(this.materialName)
                .build();
    }
}

