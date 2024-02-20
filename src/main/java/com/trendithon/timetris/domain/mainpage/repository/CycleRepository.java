package com.trendithon.timetris.domain.mainpage.repository;

import com.trendithon.timetris.domain.mainpage.domain.Cycle;
import com.trendithon.timetris.domain.mainpage.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CycleRepository extends JpaRepository<Cycle, Long> {
    List<Cycle> findAllByPlan_Id(long planId);
    List<Cycle> findAllByUser_IdAndCyclingIs(long userId, Week week);
    void deleteByPlan_IdAndCyclingIs(long planId, Week week);

}
