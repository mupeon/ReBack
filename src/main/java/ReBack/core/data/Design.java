package ReBack.core.data;

import ReBack.core.dto.DesignDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Design extends BaseEntity {
    @SequenceGenerator(name = "design_seq_generator",
            sequenceName = "design_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "design_seq_generator")
    private Long designCode;

    @Column(length=50, nullable = false) // 디자인 이름
    private String designName;

    @Column(length=500, name="design_information") // 디자인 설명
    private String designInfo;

    @Column(length = 4)
    private int designPw;

    @Column(nullable = false) // 파일이름
    private String designFileName;

    @Column(nullable = false) // 파일경로
    private String designFilePath;

    @ManyToOne
    @JoinColumn(name="category_code")
    private Category category;

    @ManyToOne
    @JoinColumn(name="material_code")
    private Material material;

    @ManyToOne
    @JoinColumn(name="member_code")
    private Member member;

    public DesignDTO toDesign() {
        return DesignDTO.builder()
                .designName(this.designName)
                .designInfo(this.designInfo)
                .designFileName(this.designFileName)
                .designFilePath(this.designFilePath)
                .designPw(this.designPw)
                .categoryCode(this.getCategory().toCategoryDTO())
                .materialCode(this.getMaterial().toMaterial())
                .build();
    }

    public void changeDesignName(String designName) {
        this.designName = designName;
    }

    public void changeDesignInfo(String designInfo) {
        this.designInfo = designInfo;
    }
}
