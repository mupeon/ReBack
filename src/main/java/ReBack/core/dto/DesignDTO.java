package ReBack.core.dto;

import ReBack.core.data.Category;
import ReBack.core.data.Design;
import ReBack.core.data.DesignFile;
import ReBack.core.data.Material;
import jdk.jfr.DataAmount;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DataAmount
@Getter
@Setter
public class DesignDTO {
    private Long designCode;
    private String designName;
    private String designInfo;
    private String designFileName;
    private String designFilePath;
    private int designPw;
    private LocalDateTime regDate, modDate;
    private CategoryDTO categoryCode;
    private MaterialDTO materialCode;

    @Builder.Default
    private List<DesignFileDTO> FileDTOList = new ArrayList<>();

    // 댓글 수 jpa의 count()
    private int replyCnt;

    public Design toDesign() {
        return Design.builder()
                .designCode(this.designCode)
                .designName(this.designName)
                .designInfo(this.designInfo)
                .designFileName(this.designFileName)
                .designFilePath(this.designFilePath)
                .designPw(this.designPw)
                .category(this.categoryCode.toCategoryDTO())
                .material(this.materialCode.toMaterialDTO())
                .build();
    }
}
