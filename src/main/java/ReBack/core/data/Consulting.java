package ReBack.core.data;

import ReBack.core.dto.ConsultingDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"writer", "member"})
public class Consulting extends BaseEntity {
    @SequenceGenerator(name = "consulting_seq_generator",
            sequenceName = "consulting_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consulting_seq_generator")
    private Long consultingCode;

    @Column(length=40, nullable = false)
    private String consultingName;

    @Column
    private String consultingInfo; // 컨설팅 설명

    @Column
    private String ConsultingPw; // 암호 설정

    @Column(nullable = false)
    private LocalDateTime startingTime;

    @Column(nullable = false)
    private LocalDateTime endTime;



//    @Column(length = 50)
//    private String consultingType;

    @Column(nullable = false) // 파일이름
    private String fileName;

    @Column(nullable = false) // 파일경로
    private String filePath;

    @Column
    private String consultingState;

    @Column
    private LocalDateTime consultingDate; // 컨설팅 일자

    @Column(length=200)
    private String consultingPlace;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private WriterInformation writer;

    @ManyToOne
    @JoinColumn(name="category_code")
    private Category category;

    @ManyToOne
    @JoinColumn(name="material_code")
    private Material material;

    public ConsultingDTO toConsultingDTO() {
        return ConsultingDTO.builder()
                .consultingCode(this.consultingCode)
                .consultingName(this.consultingName)
                .consultingPlace(this.consultingPlace)
                .build();
    }

    public void changeConsultingName(String consultingName) {
        this.consultingName = consultingName;
    }
}
