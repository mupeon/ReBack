package ReBack.core.data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @SequenceGenerator(name = "schedule_seq_generator",
            sequenceName = "schedule_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_seq_generator")
    private Long scheduleCode;

    @Column(nullable = false)
    private LocalDateTime scheduleStartTime;

    @Column(nullable = false)
    private LocalDateTime scheduleEndTime;

    @Column(nullable = false, length=1)
    private int scheduleType;

    @ManyToOne
    @JoinColumn(name="member_code")
    private Member member;

    @ManyToOne
    @JoinColumn(name="lecture_code")
    private Lecture lecture;
}
