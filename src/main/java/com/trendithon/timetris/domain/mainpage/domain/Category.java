package com.trendithon.timetris.domain.mainpage.domain;

import com.trendithon.timetris.domain.login.domain.User;
import com.trendithon.timetris.domain.mainpage.dto.CategoryCreateDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String colorCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public Category(CategoryCreateDTO categoryCreateDTO, User user){
        this.name = categoryCreateDTO.getName();
        this.colorCode = categoryCreateDTO.getColorCode();
        this.user = user;
    }

    public void updateCategory(String name, String colorCode){
        this.name = name;
        this.colorCode = colorCode;
    }

}
