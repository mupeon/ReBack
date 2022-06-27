package ReBack.core.dto;

import ReBack.core.data.Consulting;
import jdk.jfr.DataAmount;
import lombok.*;

import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsultingDTO {
    private Long consultingCode;
    private String consultingName;
    private String consultingInfo;

    @Transient
    private String startingTimeString;
    @Transient
    private String endTimeString;

    private LocalDateTime startingTime;
    private LocalDateTime endTime;
    private String consultingPlace;

    @Transient
    private String consultingDate;

    private MemberDTO memberCode; // 작성자 pk
//    private String writerName;

    private String fileName;
    private String filePath;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private CategoryDTO categoryCode;
    private MaterialDTO materialCode;

    public Consulting toConsultingDTO() {
        return Consulting.builder()
                .consultingCode(this.consultingCode)
                .consultingName(this.consultingName)
                .startingTime(this.startingTime)
                .endTime(this.endTime)
                .consultingPlace(this.consultingPlace)
                .consultingInfo(this.consultingInfo)
//                .consultingDate(this.consultingDate.toString())
                .fileName(this.fileName)
                .filePath(this.filePath)
//                .member(this.memberCode.toMemberDTO())
                .category(this.categoryCode.toCategoryDTO())
                .material(this.materialCode.toMaterialDTO())
                .build();
    }

}
