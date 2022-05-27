package ReBack.core.data;


import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayMeans {
    @SequenceGenerator(name = "pay_means_seq_generator",
            sequenceName = "pay_means_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pay_means_seq_generator")
    private Long payMeansCode;

    @Column(length=10, nullable = false, name="pay_means_type")
    private String payType;

    @Column(length=4, name="pay_means_validity")
    private int payValidity;

    @Column(length=15, name="pay_means_company")
    private String payCompany;

    @Column(name="pay_means_password")
    private int payPasswd;

    @Column(name="pay_means_bank_number")
    private int payBankNum;

    @Column(name="pay_means_card_number")
    private int payCardNum;

    @ManyToOne
    @JoinColumn(name="member_code")
    private Member member;

    @ManyToOne
    @JoinColumn(name="pay_code")
    private Pay pay;

}
