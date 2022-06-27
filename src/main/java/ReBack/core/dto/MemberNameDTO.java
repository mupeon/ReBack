package ReBack.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberNameDTO {
    private String memberName;
    private String memberPhoneNumber;
}
