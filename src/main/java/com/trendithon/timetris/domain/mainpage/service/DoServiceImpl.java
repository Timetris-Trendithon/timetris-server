package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.domain.Category;
import com.trendithon.timetris.domain.mainpage.domain.Date;
import com.trendithon.timetris.domain.mainpage.domain.Do;
import com.trendithon.timetris.domain.mainpage.domain.UserDate;
import com.trendithon.timetris.domain.mainpage.dto.DoCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.DoRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.DoViewDTO;
import com.trendithon.timetris.domain.mainpage.repository.CategoryRepository;
import com.trendithon.timetris.domain.mainpage.repository.DateRepository;
import com.trendithon.timetris.domain.mainpage.repository.DoRepository;
import com.trendithon.timetris.domain.mainpage.repository.UserDateRepository;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DoServiceImpl implements DoService{

    private final DoRepository doRepository;
    private final DateRepository dateRepository;
    private final UserDateRepository userDateRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Do createDo(long userId, DoRequestDTO doRequestDTO) {
        LocalDate localDate = LocalDate.now();
        Date date = dateRepository.findByDate(localDate);
        UserDate userDate = userDateRepository.findByUser_IdAndDate_Id(userId, date.getId());
        Category category = categoryRepository.findById(doRequestDTO.getCategoryId())
                .orElseThrow(() -> new CustomException(ErrorStatus.CATEGORY_NOT_FOUND_ERROR));
        DoCreateDTO doCreateDTO = new DoCreateDTO(doRequestDTO.getTitle(), doRequestDTO.getStartTime(), doRequestDTO.getEndTime(), category, userDate);
        Do done = new Do(doCreateDTO, userDate);
        return doRepository.save(done);
    }

    @Override
    public void updateDo(long userId, long doId, DoViewDTO doViewDTO) {
        Do done = doRepository.findById(doId)
                .orElseThrow(() -> new CustomException(ErrorStatus.DO_NOT_FOUND_ERROR));
        if (done.getUserDate().getUser().getId() != userId){
            throw new CustomException(ErrorStatus.NO_PERMISSION_ERROR);
        }
        done.updateDo(doViewDTO.getTitle(), doViewDTO.getStartTime(), doViewDTO.getEndTime());
    }

    @Override
    public void deleteDo(long userId, long doId) {
        Do done = doRepository.findById(doId)
                .orElseThrow(() -> new CustomException(ErrorStatus.DO_NOT_FOUND_ERROR));
        if (done.getUserDate().getUser().getId() != userId){
            throw new CustomException(ErrorStatus.NO_PERMISSION_ERROR);
        }
        doRepository.delete(done);
    }
}
