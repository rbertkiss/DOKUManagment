package com.rkdev.dkr.crm.repository;

import com.rkdev.dkr.crm.model.Working;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingRepository extends JpaRepository<Working, Long> {
}
