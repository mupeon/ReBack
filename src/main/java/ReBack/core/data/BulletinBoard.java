package ReBack.core.data;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BulletinBoard {
    @SequenceGenerator(name = "bulletin_board_seq_generator",
            sequenceName = "bulletin_board_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bulletin_board_seq_generator")
    private Long bulletinBoardCode;

    @Column(nullable = false, length=20)
    private String bulletinBoardType;

    @ManyToOne
    @JoinColumn(name="member_code")
    private Member member;

    @ManyToOne
    @JoinColumn(name="lecture_code")
    private Lecture lecture;
}
