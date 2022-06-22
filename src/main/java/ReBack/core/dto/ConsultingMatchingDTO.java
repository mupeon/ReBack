package ReBack.core.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultingMatchingDTO {
    private String consultingPlace;
    private String consultingDate;
    private String consultingTimeString; // 시작시간
    private String endTimeString;



}
