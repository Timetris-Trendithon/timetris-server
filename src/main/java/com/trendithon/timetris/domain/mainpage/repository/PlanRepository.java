package com.trendithon.timetris.domain.mainpage.repository;

import com.trendithon.timetris.domain.mainpage.domain.Plan;
import com.trendithon.timetris.domain.mainpage.domain.UserDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByUserDate(UserDate userDate);
}
