package com.rkdev.dkr.crm.repository;

import com.rkdev.dkr.crm.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepositroy extends JpaRepository<Skills, Long> {
}
