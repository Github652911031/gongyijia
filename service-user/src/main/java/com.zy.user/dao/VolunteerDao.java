package com.zy.user.dao;

import com.zy.user.reposity.Volunteer_GY;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerDao extends JpaRepository<Volunteer_GY, Long> {
}

