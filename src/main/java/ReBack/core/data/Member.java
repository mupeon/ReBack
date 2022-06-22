package ReBack.core.data;

import ReBack.core.dto.MemberDTO;
import ReBack.core.dto.MemberNameDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @SequenceGenerator(name = "member_seq_generator",
            sequenceName = "member_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long memberCode;

    @Column(length=30)
    private String memberId;

    @Column(length=10)
    private String memberName;

    @Column(length=60)
    private String password;

    @Column(length=50)
    private String memberEmail;

    @Column(length=11)
    private String memberPhoneNumber;

    @Column(length=10)
    private int memberPostalCode;

    @Column
    private String memberAddress;

    @Column(length=10)
    private int memberPoint;

    @Column(length=20)
    private MemberHowJoin memberHowJoin;

    @Column(length=30)
    private MemberWithdrawal memberWithdrawal;

    @Column(length=10)
    private int memberBusinessNumber;

    @Column(length=20)
    private  MemberType memberType;

    @Column
    private LocalDate memberJoinDate;

    @Column
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String memberId, String password, String memberName, boolean enabled, Role role,
                  String memberPhoneNumber,int memberPostalCode,String memberAddress,int memberPoint,
                  MemberHowJoin memberHowJoin,MemberWithdrawal memberWithdrawal,
                  int memberBusinessNumber,String memberEmail, Long memberCode) {
//        ,LocalDateTime memberJoinDate
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
        this.role = role;
        this.memberEmail = memberEmail;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberPostalCode = memberPostalCode;
        this.memberAddress = memberAddress;
        this.memberPoint = memberPoint;
        this.memberHowJoin = memberHowJoin;
        this.memberWithdrawal = memberWithdrawal;
        this.memberBusinessNumber = memberBusinessNumber;
        this.enabled = true;

    }

    @OneToMany(mappedBy = "memberCode",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private Set<Product> Products = new HashSet<>();

    @PrePersist
    public void memberJoinDate(){
        this.memberJoinDate=LocalDate.now();
    }

    public MemberDTO toWriterDTO() {
        return MemberDTO.builder()
                .memberCode(this.memberCode)
                .memberName(this.memberName)
                .build();
    }

    public MemberNameDTO toNameDTO() {
        return MemberNameDTO.builder()
                .memberName(this.memberName)
                .memberPhoneNumber(this.memberPhoneNumber)
                .build();
    }
}