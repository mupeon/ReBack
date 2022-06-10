package ReBack.core.data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
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

    @Column(nullable = false)
    private RefundState refundStatus;

    @Column(nullable = false)
    private String refundReason;

    @Column(nullable = false)
    private int refundPoint;

    @Column(nullable = false)
    private LocalDateTime refundTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orders_code")
    private Orders ordersCode;
}
