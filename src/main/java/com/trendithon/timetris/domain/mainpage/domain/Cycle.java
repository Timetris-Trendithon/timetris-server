package com.trendithon.timetris.domain.mainpage.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cycle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Week cycling;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planId")
    private Plan plan;

}
