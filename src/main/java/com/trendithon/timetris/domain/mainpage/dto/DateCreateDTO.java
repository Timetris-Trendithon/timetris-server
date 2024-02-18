package com.trendithon.timetris.domain.mainpage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class DateCreateDTO {

    private LocalDate localDate;

}
