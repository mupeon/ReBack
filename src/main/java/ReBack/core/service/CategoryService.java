package ReBack.core.service;

import ReBack.core.data.Category;
import ReBack.core.dto.CategoryDTO;
import ReBack.core.dto.PageResultDTO;

public interface CategoryService {
    default Category dtoToEntity(CategoryDTO dto) {
        Category entity = Category.builder()
                .categoryCode(dto.getCategoryCode())
                .categoryName(dto.getCategoryName())
                .build();
        return entity;
    }

    default CategoryDTO entityToDto(Category entity) {
        CategoryDTO dto = CategoryDTO.builder()
                .categoryCode(entity.getCategoryCode())
                .categoryName(entity.getCategoryName())
                .build();
        return dto;
    }
}
