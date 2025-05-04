package com.qrmenu.mapper.Category;

import com.qrmenu.dto.Category.requests.CategoryRequestDto;
import com.qrmenu.dto.Category.responses.CategoryResponseDto;
import com.qrmenu.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public CategoryResponseDto toDto(Category entity) {
        return null;
    }

    @Override
    public Category toEntity(CategoryRequestDto dto) {
        return null;
    }

    @Override
    public void updateEntityFromDto(CategoryResponseDto dto, Category entity) {

    }
}
