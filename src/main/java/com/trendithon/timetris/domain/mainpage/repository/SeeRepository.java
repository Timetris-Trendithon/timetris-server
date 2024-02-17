package com.trendithon.timetris.domain.mainpage.repository;

import com.trendithon.timetris.domain.mainpage.domain.See;
import com.trendithon.timetris.domain.mainpage.domain.UserDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeeRepository extends JpaRepository<See, Long> {
    List<See> findByUserDate(UserDate userDate);
}
