package com.trendithon.timetris.domain.mainpage.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Week {

    Sun("일요일"),
    Mon("월요일"),
    Tue("화요일"),
    Wed("수요일"),
    Thu("목요일"),
    Fri("금요일"),
    Sat("토요일");

    private String name;

}
