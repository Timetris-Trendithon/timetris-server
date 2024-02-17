package com.trendithon.timetris.domain.mainpage.repository;

import com.trendithon.timetris.domain.login.domain.User;
import com.trendithon.timetris.domain.mainpage.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUser(User user);
}
