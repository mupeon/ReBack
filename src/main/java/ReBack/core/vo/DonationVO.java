package ReBack.core.vo;

import ReBack.core.data.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationVO {
    private Long clothingSponsorCode;
    private int amount;
    private LocalDateTime pickupDate;
    private String pickupArea;
    private String issueStatus;
    private String appStatus;
    private Member memberCode;
}
