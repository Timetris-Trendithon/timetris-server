package com.trendithon.timetris.domain.login.repository;

import com.trendithon.timetris.domain.login.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
