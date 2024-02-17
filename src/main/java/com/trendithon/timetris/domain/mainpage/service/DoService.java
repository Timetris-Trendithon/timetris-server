package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.domain.Do;
import com.trendithon.timetris.domain.mainpage.dto.DoCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.DoRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.DoViewDTO;
import org.springframework.stereotype.Service;

@Service
public interface DoService {
    Do createDo(long userId, DoRequestDTO doRequestDTO);
    void updateDo(long userId, long doId, DoViewDTO doViewDTO);
    void deleteDo(long userId, long doId);
}
