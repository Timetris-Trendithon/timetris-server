package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.domain.See;
import com.trendithon.timetris.domain.mainpage.dto.SeeCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.SeeRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.SeeViewDTO;
import org.springframework.stereotype.Service;

@Service
public interface SeeService {
    See createSee(long userId, SeeRequestDTO seeRequestDTO);
    void updateSee(long userId, long seeId, SeeViewDTO seeViewDTO);
    void deleteSee(long userId, long seeId);
}
