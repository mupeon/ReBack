package ReBack.core.data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    private String writerLecturePlace;

    @Column
    private String availableStartTime;

    @Column
    private String availableFinishTime;

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
    private Member memberCode;


}
