package ReBack.core.data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinancialSupport {
    @SequenceGenerator(name = "financial_support_seq_generator",
            sequenceName = "financial_support_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financial_support_seq_generator")
    private Long financialSupportCode;

    @Column(nullable = false, name="financial_support_date")
    private LocalDateTime financialDate;

    @Column(nullable = false, name="financial_support_amount")
    private int financialAmount;

    @Column(nullable = false, length=1, name="financial_support_type")
    private String financialType;

    @Column(name="issue_receipt_status", length=15)
    private String statusIssue;

    @Column(name="receipt_request_status", length=15)
    private String statusApp;

    @OneToMany(mappedBy = "financialSupportCode",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private Set<DonationFacility> donationFacilities = new HashSet<>();
//    private Set<SponsorDetails> sponsorDetails = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="member_code")
    private Member memberCode;

    @ManyToOne
    @JoinColumn(name="member_name")
    private Member memberName;
}
