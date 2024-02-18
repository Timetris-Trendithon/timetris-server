package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.domain.Category;
import com.trendithon.timetris.domain.mainpage.dto.CategoryCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.CategoryRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.CategoryViewDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<CategoryViewDTO> readCategoryAll(long userId);
    Category createCategory(long userId, CategoryRequestDTO categoryRequestDTO);
    void updateCategory(long userId, long categoryId, CategoryViewDTO categoryViewDTO);
    void deleteCategory(long userId, long categoryId);

}
