package ReBack.core.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public class Product {
    @SequenceGenerator(name = "product_seq_generator",
            sequenceName = "product_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_generator")
    private Long productCode;

    @Column(nullable = false, name = "product_information")
    private String productInfo;

    @Column(nullable = false, length = 50)
    private String productName;

    @Column(nullable = false, length = 8)
    private int productPrice;

    @Column(nullable = false, length = 8)
    private int productStock;

    @Column(nullable = false)
    private String productFileName;

    @Column(nullable = false)
    private String productFilePath;

    @OneToMany(mappedBy = "productCode",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private Set<Orders> orders = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code")
    private Member memberCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_code")
    private Category categoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_code")
    private Material materialCode;



}
