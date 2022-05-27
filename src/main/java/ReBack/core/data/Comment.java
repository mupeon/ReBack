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
@Table(name="tbl_comment")
public class Comment {
    @SequenceGenerator(name = "comment_seq_generator",
            sequenceName = "comment_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq_generator")
    private Long commentCode;

    @Column(length=100, nullable = false)
    private String commentTitle;

    @Column(length=500, nullable = false)
    private String commentContent;

    @Column(nullable = false, name="comment_writing_time")
    private LocalDateTime writingTime;

    @Column(nullable = false, length=5)
    private int commentViews;

    @Column(nullable = false, length=5, name="comment_recommended_number")
    private int commentRecommend;

    @Column(nullable = false, length=4)
    private int commentPassword;

    @Column(nullable = false)
    private int commentHoroscope;

    @ManyToOne
    @JoinColumn(name="member_code")
    private Member member;

    @ManyToOne
    @JoinColumn(name="order_list_code")
    private OrderList orderList;

    @ManyToOne
    @JoinColumn(name="bulletin_board_code")
    private BulletinBoard bulletinBoard;
}
