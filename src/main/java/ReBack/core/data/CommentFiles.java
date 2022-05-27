package ReBack.core.data;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_File")
public class CommentFiles {
    @SequenceGenerator(name = "file_seq_generator",
            sequenceName = "file_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_seq_generator")
    private Long fileCode;

    @Column(length=100)
    private String fileName;

    @Column
    private String filePath;

    @Column
    private String fileImgPath;

    @ManyToOne
    @JoinColumn(name="comment_code")
    private Comment comment;

}
