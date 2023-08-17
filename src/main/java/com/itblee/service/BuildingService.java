package com.itblee.service;

import com.itblee.dto.BuildingDTO;
import com.itblee.dto.BuildingListDTO;
import com.itblee.dto.BuildingSearchDTO;
import com.itblee.dto.PagedListHolder;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BuildingService {
    Optional<BuildingDTO> findById(Long id);
    List<BuildingListDTO> findAll(Map<?, ?> params, Pageable pageable);
    int countAll(Map<?, ?> params);
    BuildingDTO save(BuildingDTO updateDto);
    void delete(long[] ids);
    void assignBuilding(Long buildingId, Long[] staffIds);
    Map<String, String> getDistricts();
    Map<String, String> getRentTypes();
    void fillSource(PagedListHolder<BuildingListDTO> pages, final BuildingSearchDTO dto);
}
