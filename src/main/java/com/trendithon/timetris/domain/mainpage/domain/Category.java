package com.trendithon.timetris.domain.mainpage.domain;

import com.trendithon.timetris.domain.login.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String colorCode;
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;

}
