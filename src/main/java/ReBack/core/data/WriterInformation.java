package ReBack.core.data;

import ReBack.core.dto.WriterInformationDTO;
import ReBack.core.dto.WriterProfileDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WriterInformation {
    @SequenceGenerator(name = "writer_information_seq_generator",
            sequenceName = "writer_information_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "writer_information_seq_generator")
    private Long writerInformationCode;

    @Column(length=200, name="writer_lecture_place")
    private String writerLecturePlace; // 출강장소

    @Column
    private LocalDateTime availableStartTime;

    @Column
    private LocalDateTime availableFinishTime;

    @Column(length=30)
    private String availableDay;

    @Column(length=100)
    private String trainingTopic;

    @Column
    private int trainingStatus;

    @Column
    private int feedbackStatus;

    @ManyToOne
    @JoinColumn(name="member_code")
    private Member writer;

    @ManyToOne
    @JoinColumn(name="category_code")
    private Category category;

    @ManyToOne
    @JoinColumn(name="material_code")
    private Material material;


    public WriterInformationDTO toWriterInformation() {
        return WriterInformationDTO.builder()
                .writerLecturePlace(this.writerLecturePlace)
                .availableStartTime(this.availableStartTime)
                .availableFinishTime(this.availableFinishTime)
                .availableDay(this.availableDay)
                .writer(this.getWriter().toWriterDTO())
                .categoryCode(this.getCategory().toCategoryDTO())
                .materialCode(this.getMaterial().toMaterial())
                .build();
    }

    public WriterProfileDTO toWriterProfileDTO(){
        return WriterProfileDTO.builder()
                .memberName(this.writer.toNameDTO())
                .availableFinishTime(this.availableFinishTime)
                .availableStartTime(this.availableStartTime)
                .writerLecturePlace(this.writerLecturePlace)
                .build();
    }
}
