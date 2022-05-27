package ReBack.core.data;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Refund {
    @SequenceGenerator(name = "refund_seq_generator",
            sequenceName = "refund_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refund_seq_generator")
    private Long refundCode;

    @Column(nullable = false)
    private int refundCount;

    @Column(nullable = false)
    private int refundAmount;

    @Column(nullable = false, length=1)
    private int refundStatus;

    @Column(nullable = false)
    private int refundPoint;

    @ManyToOne
    @JoinColumn(name="order_list_code")
    private OrderList orderList;
}
