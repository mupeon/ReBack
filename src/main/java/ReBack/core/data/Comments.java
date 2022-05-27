package ReBack.core.data;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    @SequenceGenerator(name = "comments_seq_generator",
            sequenceName = "comments_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_seq_generator")
    private Long commentsCode;

    @Column(nullable = false, length=300)
    private String commentsContent;

    @Column(nullable = false, length = 100)
    private String commentsPassword;

    @ManyToOne
    @JoinColumn(name="comment_code")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name="member_code")
    private Member member;
}
