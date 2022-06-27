package ReBack.core.dto;

import ReBack.core.data.Consulting;
import ReBack.core.data.WriterInformation;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsultingOrderDTO {
    // 출강장소, 시작시간, 종료시간, 작가이름, 카테고리, 소재, 시간(int), 총금액
    private Long writerInformationCode;
    private String writerLecturePlace;
    private LocalDateTime availableStartTime;
    private LocalDateTime availableFinishTime;

    private MemberDTO writer; // 작성자 pk

    private CategoryDTO categoryCode;
    private MaterialDTO materialCode;
    private ConsultingDTO consultingPrice;

    WriterInformation toConsultingOrderDTO() {
        return WriterInformation.builder()
                .writerInformationCode(this.writerInformationCode)
                .writerLecturePlace(this.writerLecturePlace)
                .availableStartTime(this.availableStartTime)
                .availableFinishTime(this.availableFinishTime)
//                .consultingPrice(this.consultingPrice.toConsultingDTO())
                .writer(this.writer.toWriterDTO())
                .build();    }
}
