package com.trendithon.timetris.domain.mainpage.service;


import com.trendithon.timetris.domain.member.domain.User;
import com.trendithon.timetris.domain.member.repository.UserRepository;
import com.trendithon.timetris.domain.mainpage.domain.Category;
import com.trendithon.timetris.domain.mainpage.dto.CategoryCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.CategoryRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.CategoryViewDTO;
import com.trendithon.timetris.domain.mainpage.repository.CategoryRepository;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryViewDTO> readCategoryAll(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR));
        List<Category> result = categoryRepository.findAllByUser(user);
        List<CategoryViewDTO> categoryViewDTOList = CategoryViewDTO.from(result);
        return categoryViewDTOList;
    }

    @Override
    public Category createCategory(long userId, CategoryRequestDTO categoryRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR));
        CategoryCreateDTO categoryCreateDTO = new CategoryCreateDTO(categoryRequestDTO.getName(), categoryRequestDTO.getColorCode());
        Category category = new Category(categoryCreateDTO, user);
        return categoryRepository.save(category);
    }

    @Override
    public void updateCategory(long userId, long categoryId, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException(ErrorStatus.CATEGORY_NOT_FOUND_ERROR));
        findUser(userId);
        if (category.getUser().getId() != userId){
            throw new CustomException(ErrorStatus.NO_PERMISSION_ERROR);
        }
        category.updateCategory(categoryRequestDTO.getName(), categoryRequestDTO.getColorCode());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long userId, long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException(ErrorStatus.CATEGORY_NOT_FOUND_ERROR));
        findUser(userId);
        if (category.getUser().getId() != userId){
            throw new CustomException(ErrorStatus.NO_PERMISSION_ERROR);
        }
        categoryRepository.delete(category);
    }

    public void findUser(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR));
    }
}
