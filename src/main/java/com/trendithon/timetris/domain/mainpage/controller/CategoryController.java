package com.trendithon.timetris.domain.mainpage.controller;

import com.trendithon.timetris.domain.mainpage.domain.Category;
import com.trendithon.timetris.domain.mainpage.dto.CategoryCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.CategoryRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.CategoryViewDTO;
import com.trendithon.timetris.domain.mainpage.service.CategoryService;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{userId}")
    public ApiResponse<List<Category>> readCategoryAll(Authentication authentication,
                                                       @PathVariable long userId)
    {
        List<Category> categoryList = categoryService.readCategoryAll(userId);
        return ApiResponse.success(SuccessStatus.OK, categoryList);
    }

    @PostMapping("")
    public ApiResponse createCategory(Authentication authentication,
                                      @RequestBody CategoryRequestDTO categoryRequestDTO)
    {
        long userId = 1;
        Category category = categoryService.createCategory(userId, categoryRequestDTO);
        if (category == null){
            throw new CustomException(ErrorStatus.INVALID_FORMAT_ERROR);
        }
        return ApiResponse.success(SuccessStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ApiResponse updateCategory(Authentication authentication,
                                      @PathVariable long categoryId,
                                      @RequestBody CategoryViewDTO categoryViewDTO)
    {
        long userId = 1;
        categoryService.updateCategory(userId, categoryId, categoryViewDTO);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ApiResponse deleteCategory(Authentication authentication,
                                      @PathVariable long categoryId)
    {
        long userId = 1;
        categoryService.deleteCategory(userId, categoryId);
        return ApiResponse.success(SuccessStatus.OK);
    }

}
