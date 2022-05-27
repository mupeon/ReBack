package ReBack.core.data;

import lombok.*;

import javax.persistence.*;

@Entity
//@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SponsorDetails {
    @SequenceGenerator(name = "sponsor_details_seq_generator",
            sequenceName = "sponsor_details_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sponsor_details_seq_generator")
    private Long SponsorDetailsCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="financial_support_code")
    private FinancialSupport financialSupportCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clothing_sponsor_code")
    private ClothingSponsor clothingSponsor;
}