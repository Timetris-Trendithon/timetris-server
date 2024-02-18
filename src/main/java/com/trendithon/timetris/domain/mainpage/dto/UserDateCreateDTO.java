package com.trendithon.timetris.domain.mainpage.dto;

import com.trendithon.timetris.domain.login.domain.User;
import com.trendithon.timetris.domain.mainpage.domain.Date;
import com.trendithon.timetris.domain.mainpage.domain.UserDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class UserDateCreateDTO {

    private User user;
    private Date date;

}
