package com.qrmenu.mapper.Category;


import com.qrmenu.dto.Category.requests.CategoryRequestDto;
import com.qrmenu.dto.Category.responses.CategoryResponseDto;
import com.qrmenu.entity.Category;

public interface CategoryMapper {
    CategoryResponseDto toDto(Category entity);
    Category toEntity(CategoryRequestDto dto);
    void updateEntityFromDto(CategoryResponseDto dto, Category entity);
}
