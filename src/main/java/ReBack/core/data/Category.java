package ReBack.core.data;

import ReBack.core.dto.CategoryDTO;
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
public class Category {
    @SequenceGenerator(name = "category_seq_generator",
            sequenceName = "category_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_generator")
    private Long categoryCode;

    @Column(length=30, nullable = false)
    private String categoryName;

//    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL) // (1)
//    @JoinColumn(name="category_code")
//    private List<Product> products = new ArrayList<>();

    public CategoryDTO toCategoryDTO() {
        return CategoryDTO.builder()
                .categoryCode(this.categoryCode)
                .categoryName(this.categoryName)
                .build();
    }
}
