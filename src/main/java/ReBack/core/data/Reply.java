package ReBack.core.data;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude={"design","member"})
public class Reply extends BaseEntity{
        @SequenceGenerator(name = "reply_seq_generator",
            sequenceName = "reply_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "reply_seq_generator")
    private Long replyCode;

    private String text;

    @ManyToOne(fetch=FetchType.LAZY)
    private Design design;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
