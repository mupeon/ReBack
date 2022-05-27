package ReBack.core.controller.request;

import ReBack.core.data.MemberHowJoin;
import ReBack.core.data.MemberWithdrawal;
import ReBack.core.data.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class RegistryRequest {

        private Long memberCode;
        private String memberId;
        private String password;
        private String memberName;
        private String memberEmail;
        private String memberPhoneNumber;
        private int memberPostalCode;
        private String memberAddress;
        private int memberPoint;
        private MemberHowJoin memberHowJoin;
        private MemberWithdrawal memberWithdrawal;
        private int memberBusinessNumber;
        private LocalDateTime memberJoinDate;
        private boolean enabled;

        private Role role = Role.ROLE_AUTHOR;
    }
