package ReBack.core.dto;

import ReBack.core.data.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDTO {
    private Long memberCode;
    private String memberName;

    public Member toMemberDTO() {
        return Member.builder()
                .memberCode(this.memberCode)
                .memberName(this.memberName)
                .build();
    }
}
