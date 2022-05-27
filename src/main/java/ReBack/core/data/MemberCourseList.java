package ReBack.core.data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberCourseList {
    @SequenceGenerator(name = "member_course_list_seq_generator",
            sequenceName = "member_course_list_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_course_list_seq_generator")
    private Long memberCourseListCode;

    @Column(nullable = false, name="member_course_list_course_date")
    private LocalDateTime memberCourseDate;

    @Column(length = 50, nullable = false, name="member_course_list_place")
    private String memberCoursePlace;

    @Column(length=30, nullable = false, name="member_course_list_type")
    private String memberCourseType;

    @Column(name="member_course_list_course_registration")
    private int memberCourseReg;

    @ManyToOne
    @JoinColumn(name="member_code")
    private Member member;

    @ManyToOne
    @JoinColumn(name="lecture_code")
    private Lecture lecture;
}
