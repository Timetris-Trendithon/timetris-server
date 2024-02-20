package com.trendithon.timetris.domain.mainpage.domain;

import com.trendithon.timetris.domain.mainpage.dto.CycleCreateDTO;
import com.trendithon.timetris.domain.member.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cycle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Week cycling;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planId")
    private Plan plan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public Cycle(CycleCreateDTO cycleCreateDTO){
        this.cycling = cycleCreateDTO.getCycling();
        this.plan = cycleCreateDTO.getPlan();
        this.user = cycleCreateDTO.getPlan().getUserDate().getUser();
    }

    public Cycle(Week cycling, Plan plan){
        this.cycling = cycling;
        this.plan = plan;
        this.user = plan.getUserDate().getUser();
    }

    public void updateCycle(Week cycling){
        this.cycling = cycling;
    }

}
