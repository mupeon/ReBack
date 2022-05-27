package ReBack.core.data;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationFacility {
    @SequenceGenerator(name = "donation_facility_seq_generator",
            sequenceName = "donation_facility_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donation_facility_seq_generator")
    private Long donationFacilityCode;

    @Column(name="donation_facility_name", nullable = false, length=30)
    private String facilityName;

    @Column(name="donation_facility_address", nullable = false)
    private String facilityAddress;

    @Column(name="donation_facility_description", nullable = false)
    private String facilityDescription;

    @Column(name="donation_facility_category", nullable = false, length=30)
    private String facilityCategory;

    @Column(nullable = false)
    private int monthlyDonation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="financial_support_code")
    private FinancialSupport financialSupportCode;
}
