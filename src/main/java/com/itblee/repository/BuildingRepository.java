package com.itblee.repository;

import com.itblee.repository.custom.BuildingRepositoryCustom;
import com.itblee.repository.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface BuildingRepository extends JpaRepository<Building, Long>, BuildingRepositoryCustom {
    Set<Building> findByIdIn(Iterable<Long> ids);
    Optional<Building> findByIdAndAssignUsers_Id(Long id, Long staffId);
}
