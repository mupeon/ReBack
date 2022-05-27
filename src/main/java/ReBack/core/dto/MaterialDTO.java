package ReBack.core.dto;

import ReBack.core.data.Category;
import ReBack.core.data.Material;
import jdk.jfr.DataAmount;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@DataAmount
@Getter
@Setter
public class MaterialDTO {
    private Long materialCode;
    private String materialName;

    public Material toMaterialDTO() {
        return Material.builder()
                .materialCode(this.materialCode)
                .materialName(this.materialName)
                .build();
    }
}
