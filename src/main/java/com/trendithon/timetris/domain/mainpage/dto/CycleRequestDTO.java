package com.trendithon.timetris.domain.mainpage.dto;

import com.trendithon.timetris.domain.mainpage.domain.Week;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class CycleRequestDTO {

    private List<String> cycling;

}
