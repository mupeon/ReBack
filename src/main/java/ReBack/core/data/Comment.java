package ReBack.core.data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
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
    private String commentTitle; //제목

    @Column(length=500, nullable = false)
    private String commentContent; //내용

    @Column(nullable = false, name="comment_writing_date")
    private LocalDate writingDate; //시간

    @Column(nullable = false, length=5)
    private int commentViews; //조회수

    @Column(nullable = false, length=5, name="comment_recommended_number")
    private int commentRecommend; //추천수

    @Column(nullable = false)
    private int commentHoroscope; //별점

    @Column(nullable = true)
    private String reviewFileName;

    @Column(nullable = true)
    private String reviewFilePath;

    @OneToMany(mappedBy = "comment",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private Set<CommentFiles> commentFiles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="member_code")
    private Member member;

    @ManyToOne
    @JoinColumn(name="product_code")
    private Product product;

    @ManyToOne
    @JoinColumn(name="bulletin_board_code")
    private BulletinBoard bulletinBoard;
}
