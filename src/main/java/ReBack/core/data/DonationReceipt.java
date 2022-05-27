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
public class DonationReceipt {
    @SequenceGenerator(name = "donation_receipt_seq_generator",
            sequenceName = "donation_receipt_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donation_receipt_seq_generator")
    private Long donationReceiptCode;

    @Column(name="donation_receipt_issue_date", nullable = false)
    private LocalDateTime donationIssueDate;

    @Column(name="donation_receipt_amout", nullable = false)
    private int donationAmount;

    @ManyToOne
    @JoinColumn(name="clothing_sponsor_code")
    private ClothingSponsor clothingSponsorCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sponsor_details_code")
    private SponsorDetails sponsorDetailsCode;

}
