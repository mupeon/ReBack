package ReBack.core.data;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @SequenceGenerator(name = "cart_seq_generator",
            sequenceName = "cart_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq_generator")
    private Long cartCode;

    @Column(length=3, nullable = false)
    private int cartCount;

    @Column(nullable = true)
    private CheckState checkState;

    @Column(nullable = false)
    private OrdersState ordersState;

    @ManyToOne
    @JoinColumn(name="member_code")
    private Member memberCode;

    @ManyToOne
    @JoinColumn(name="product_code")
    private Product productCode;

}
