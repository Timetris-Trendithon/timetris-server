package com.trendithon.timetris.domain.mainpage.domain;

import com.trendithon.timetris.domain.member.domain.User;
import com.trendithon.timetris.domain.mainpage.dto.CategoryCreateDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Do> doList = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Plan> planList = new ArrayList<>();


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
