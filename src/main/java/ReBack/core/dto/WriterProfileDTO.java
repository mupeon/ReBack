package ReBack.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class WriterProfileDTO {
    private String writerLecturePlace; // 출강장소
    private LocalDateTime availableStartTime;
    private LocalDateTime availableFinishTime;

    private MemberNameDTO memberName; // 작가 이름, 전화번호 들고있음.


}
