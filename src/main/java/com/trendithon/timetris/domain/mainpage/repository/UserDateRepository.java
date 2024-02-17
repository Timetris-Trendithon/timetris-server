package com.trendithon.timetris.domain.mainpage.repository;

import com.trendithon.timetris.domain.mainpage.domain.UserDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDateRepository extends JpaRepository<UserDate, Long> {
    UserDate findByUser_IdAndDate_Id(long userId, long dateId);
}
