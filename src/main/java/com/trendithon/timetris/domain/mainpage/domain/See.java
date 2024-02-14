package com.trendithon.timetris.domain.mainpage.domain;

import com.trendithon.timetris.domain.mainpage.dto.SeeCreateDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class See {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userDateId")
    private UserDate userDate;

    public void updateSee(String content){
        this.content = content;
    }

    public See(SeeCreateDTO seeCreateDTO, UserDate userDate){
        this.content = seeCreateDTO.getContent();
        this.userDate = userDate;
    }

}
