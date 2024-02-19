package com.trendithon.timetris.domain.mainpage.controller;

import com.trendithon.timetris.domain.mainpage.domain.Category;
import com.trendithon.timetris.domain.mainpage.dto.CategoryCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.CategoryRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.CategoryViewDTO;
import com.trendithon.timetris.domain.mainpage.service.CategoryService;
import com.trendithon.timetris.global.auth.jwt.TokenProvider;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final TokenProvider tokenProvider;

    public long getUserId(HttpServletRequest request){
        String accessToken = tokenProvider.extractAccessToken(request).orElse(null);

        Long userId = null;

        if (accessToken != null) {
            userId = tokenProvider.extractId(accessToken).orElse(null);
        } else {
            throw new CustomException(ErrorStatus.NOT_LOGIN_ERROR);

        }
        if(userId == null) {
            throw new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR);
        }
        return userId;
    }

    @GetMapping("")
    public ApiResponse<List<CategoryViewDTO>> readCategoryAll(HttpServletRequest request)
    {
        long userId = getUserId(request);

        List<CategoryViewDTO> categoryList = categoryService.readCategoryAll(userId);
        return ApiResponse.success(SuccessStatus.OK, categoryList);
    }

    @PostMapping("")
    public ApiResponse<CategoryRequestDTO> createCategory(HttpServletRequest request,
                                      @RequestBody CategoryRequestDTO categoryRequestDTO)
    {
        long userId = getUserId(request);

        Category category = categoryService.createCategory(userId, categoryRequestDTO);
        if (category == null){
            throw new CustomException(ErrorStatus.INVALID_FORMAT_ERROR);
        }
        return ApiResponse.success(SuccessStatus.OK, categoryRequestDTO);
    }

    @PutMapping("/{categoryId}")
    public ApiResponse<CategoryRequestDTO> updateCategory(HttpServletRequest request,
                                      @PathVariable long categoryId,
                                      @RequestBody CategoryRequestDTO categoryRequestDTO)
    {
        long userId = getUserId(request);

        categoryService.updateCategory(userId, categoryId, categoryRequestDTO);
        return ApiResponse.success(SuccessStatus.OK, categoryRequestDTO);
    }

    @DeleteMapping("/{categoryId}")
    public ApiResponse deleteCategory(HttpServletRequest request,
                                      @PathVariable long categoryId)
    {
        long userId = getUserId(request);

        categoryService.deleteCategory(userId, categoryId);
        return ApiResponse.success(SuccessStatus.OK);
    }

}
