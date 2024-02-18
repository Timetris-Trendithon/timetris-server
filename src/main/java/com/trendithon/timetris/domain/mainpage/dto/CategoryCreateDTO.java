package com.trendithon.timetris.domain.mainpage.dto;

import com.trendithon.timetris.domain.member.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class CategoryCreateDTO {

    private String name;
    private String colorCode;
    private User user;

}
