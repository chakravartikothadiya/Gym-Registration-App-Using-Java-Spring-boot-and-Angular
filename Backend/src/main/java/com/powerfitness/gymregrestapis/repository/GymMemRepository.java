package com.powerfitness.gymregrestapis.repository;

import com.powerfitness.gymregrestapis.model.GymMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymMemRepository extends JpaRepository<GymMember, Long> {
}
