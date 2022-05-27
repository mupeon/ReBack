package ReBack.core.dto;

import ReBack.core.data.Category;
import jdk.jfr.DataAmount;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@DataAmount
@Getter
@Setter
public class CategoryDTO {
    private Long categoryCode;
    private String categoryName;

    public Category toCategoryDTO() {
        return Category.builder()
                .categoryCode(this.categoryCode)
                .categoryName(this.categoryName)
                .build();
    }
}
