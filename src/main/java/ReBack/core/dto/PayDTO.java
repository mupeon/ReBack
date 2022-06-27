package ReBack.core.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayDTO {
    private LocalDateTime start;
    private LocalDateTime end;
    private String place;
    private String memberName;
    private int time;
    private int pay;

}
