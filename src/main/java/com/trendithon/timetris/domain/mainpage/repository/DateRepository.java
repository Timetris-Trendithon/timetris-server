package com.trendithon.timetris.domain.mainpage.repository;

import com.trendithon.timetris.domain.mainpage.domain.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

public interface DateRepository extends JpaRepository<Date, Long> {
    Date findByDate(LocalDate localDate);
    @Query("SELECT d FROM Date d WHERE YEAR(d.date) = :year AND MONTH(d.date) = :month ORDER BY d.date DESC")
    List<Date> findAllByYearAndMonth(int year, int month);
}
