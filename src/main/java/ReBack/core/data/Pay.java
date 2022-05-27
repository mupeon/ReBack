package ReBack.core.data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pay {
    @SequenceGenerator(name = "pay_seq_generator",
            sequenceName = "pay_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pay_seq_generator")
    private Long payCode;

    @Column(nullable = false)
    private LocalDateTime payTime;

    @Column
    private int payPoint;

    @Column(nullable = false)
    private int payAmount;

    @Column(nullable = false, length = 4)
    private String payValidity;

    @Column(nullable = false, length = 1, name = "pay_payment_method")
    private int payMethod;

    @ManyToOne
    @JoinColumn(name="sponsor_details_code")
    private SponsorDetails sponsorDetails;
}
