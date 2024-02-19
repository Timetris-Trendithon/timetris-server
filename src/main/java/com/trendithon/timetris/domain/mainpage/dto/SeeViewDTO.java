package com.trendithon.timetris.domain.mainpage.dto;

import com.trendithon.timetris.domain.mainpage.domain.Date;
import com.trendithon.timetris.domain.mainpage.domain.See;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class SeeViewDTO {

    private long id;
    private String content;

    public static SeeViewDTO of(See see){
        return SeeViewDTO.builder()
                .id(see.getId())
                .content(see.getContent())
                .build();
    }

}
