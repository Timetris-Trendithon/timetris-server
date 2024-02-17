package com.trendithon.timetris.domain.mainpage.repository;

import com.trendithon.timetris.domain.mainpage.domain.Date;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DateRepository extends JpaRepository<Date, Long> {
    Date findByDate(LocalDate localDate);
}
