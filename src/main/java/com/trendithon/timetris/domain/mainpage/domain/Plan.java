package com.trendithon.timetris.domain.mainpage.domain;

import com.trendithon.timetris.domain.mainpage.dto.PlanCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.PlanViewDTO;
import com.trendithon.timetris.domain.mainpage.repository.PlanRepository;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Plan {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean status = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userDateId")
    private UserDate userDate;

    public Plan(PlanCreateDTO planCreateDTO, UserDate userDate){
        this.title = planCreateDTO.getTitle();
        this.startTime = planCreateDTO.getStartTime();
        this.endTime = planCreateDTO.getEndTime();
        this.status = planCreateDTO.isStatus();
        this.category = planCreateDTO.getCategory();
        this.userDate = userDate;
    }

    public void updatePlan(String title, LocalTime startTime, LocalTime endTime, Category category){
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
    }

    public void donePlan(){
        this.status = true;
    }

}
