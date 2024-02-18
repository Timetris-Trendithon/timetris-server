package com.trendithon.timetris.domain.mainpage.dto;

import com.trendithon.timetris.domain.mainpage.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class CategoryViewDTO {

    private String name;
    private String colorCode;

    public static CategoryViewDTO of(Category category){
        return CategoryViewDTO.builder()
                .name(category.getName())
                .colorCode(category.getColorCode())
                .build();
    }

    public static List<CategoryViewDTO> from(List<Category> categoryList){
        return categoryList.stream()
                .map(CategoryViewDTO::of)
                .toList();
    }

}
