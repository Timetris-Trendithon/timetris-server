package com.trendithon.timetris.domain.past.service;

import com.trendithon.timetris.domain.past.dto.PastViewDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PastService {
    List<PastViewDTO> readPastsAll(long userId);
    PastViewDTO readPast(long userId, String date);
    List<PastViewDTO> readPathsMonthAll(long userId, String month);
}
