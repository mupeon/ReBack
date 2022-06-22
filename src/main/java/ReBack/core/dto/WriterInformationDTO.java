package ReBack.core.dto;

import ReBack.core.data.WriterInformation;
import jdk.jfr.DataAmount;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DataAmount
@Getter
@Setter
public class WriterInformationDTO {
    private Long writerInformationCode;
    private String writerLecturePlace; // 출강장소
    private LocalDateTime availableStartTime;
    private LocalDateTime availableFinishTime;
    private String availableDay;

    private CategoryDTO categoryCode;
    private MaterialDTO materialCode;

    private MemberDTO writer;

    public WriterInformation toWriterInformation() {
        return WriterInformation.builder()
                .writerInformationCode(this.writerInformationCode)
                .writerLecturePlace(this.writerLecturePlace)
                .availableStartTime(this.availableStartTime)
                .availableFinishTime(this.availableFinishTime)
                .availableDay(this.availableDay)
                .writer(this.writer.toWriterDTO())
                .build();
    }
}


