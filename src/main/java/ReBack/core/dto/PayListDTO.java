package ReBack.core.dto;

import lombok.*;

import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayListDTO {
    private LocalDateTime start;
    private LocalDateTime end;
    private String place;
    private String memberName;

}
