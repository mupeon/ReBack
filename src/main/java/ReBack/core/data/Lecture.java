package ReBack.core.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
    @ToString
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
@JsonAutoDetect
public class Lecture {
    @SequenceGenerator(name = "lecture_generator",
            sequenceName = "lecture_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecture_generator")
    private Long lectureCode;

    @Column(length=50, nullable = false)
    private String lectureName;

    @Column(length=10, nullable = false)
    private int lecturePersonnel;

    @Column(length=20, nullable = false)
    private LectureAvailability lectureAvailability;

    @Column(length=5, nullable = false)
    private int minimumNumber;

    @Column(length=5, nullable = false)
    private int applicationNumber;

    @Column(nullable = false)
    private String lecturePlace;

    @Column(nullable = false)
    private Date lectureDate;

    @Column(nullable = false)
    private String lectureFileName;

    @Column(nullable = false)
    private String lectureFilePath;

    @ManyToOne
    @JoinColumn(name="member_code")
    private Member member;

    @ManyToOne
    @JoinColumn(name="category_code")
    private Category category;

    @ManyToOne
    @JoinColumn(name="material_code")
    private Material material;

}
