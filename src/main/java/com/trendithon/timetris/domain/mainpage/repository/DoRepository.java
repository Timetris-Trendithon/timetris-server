package com.trendithon.timetris.domain.mainpage.repository;

import com.trendithon.timetris.domain.mainpage.domain.Do;
import com.trendithon.timetris.domain.mainpage.domain.UserDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoRepository extends JpaRepository<Do, Long> {
    List<Do> findAllByUserDate(UserDate userDate);
}
