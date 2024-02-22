package com.trendithon.timetris.domain.mainpage.dto;

import com.trendithon.timetris.domain.mainpage.domain.Do;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class DoTimeDTO {

    private int hour;
    private int minute;

    public static DoTimeDTO of(LocalTime localTime){
        return DoTimeDTO.builder()
                .hour(localTime.getHour())
                .minute(localTime.getMinute())
                .build();
    }

}
