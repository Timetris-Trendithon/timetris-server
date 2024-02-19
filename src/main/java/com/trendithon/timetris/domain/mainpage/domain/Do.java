package com.trendithon.timetris.domain.mainpage.domain;

import com.trendithon.timetris.domain.mainpage.dto.DoCreateDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Do {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userDateId")
    private UserDate userDate;

    public Do(DoCreateDTO doCreateDTO, UserDate userDate){
        this.title = doCreateDTO.getTitle();
        this.startTime = doCreateDTO.getStartTime();
        this.endTime = doCreateDTO.getEndTime();
        this.category = doCreateDTO.getCategory();
        this.userDate = userDate;
    }

    public void updateDo(String title, LocalTime startTime, LocalTime endTime, Category category){
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
    }

}
