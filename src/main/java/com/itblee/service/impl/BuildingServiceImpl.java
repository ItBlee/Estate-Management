package com.itblee.service.impl;

import com.itblee.converter.BuildingConverter;
import com.itblee.dto.BuildingDTO;
import com.itblee.dto.BuildingListDTO;
import com.itblee.dto.BuildingSearchDTO;
import com.itblee.dto.PagedListHolder;
import com.itblee.repository.BuildingRepository;
import com.itblee.repository.entity.Building;
import com.itblee.repository.entity.User;
import com.itblee.repository.enums.District;
import com.itblee.repository.enums.RentType;
import com.itblee.repository.sqlbuilder.SqlMap;
import com.itblee.repository.sqlbuilder.impl.LinkedSqlMap;
import com.itblee.repository.sqlbuilder.key.BuildingKey;
import com.itblee.security.utils.SecurityUtils;
import com.itblee.service.BuildingService;
import com.itblee.utils.FileUtils;
import com.itblee.utils.MapUtils;
import com.itblee.utils.StringUtils;
import com.itblee.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.itblee.constant.SystemConstant.*;

@Service
public class BuildingServiceImpl implements BuildingService {

    private static final Map<String, String> DISTRICTS = MapUtils.of(District.values());
    private static final Map<String, String> RENT_TYPES = MapUtils.of(RentType.values());

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Override
    public Optional<BuildingDTO> findById(Long id) {
        Optional<Building> building;
        if (!SecurityUtils.hasRole(MANAGER)) {
            Long authId = SecurityUtils.getPrincipal().getId();
            building = buildingRepository.findByIdAndAssignUsers_Id(id, authId);
        } else building = buildingRepository.findById(id);
        return building.map(buildingConverter::toDto);
    }

    @SuppressWarnings("unchecked")
    private SqlMap<BuildingKey> mapKey(Map<?, ?> params) {
        ValidateUtils.requireValidParams(params);
        SqlMap<BuildingKey> statements = new LinkedSqlMap<>();
        statements.putAll(params, BuildingKey.class);

        if (!SecurityUtils.hasRole(MANAGER)) {
            Long authId = SecurityUtils.getPrincipal().getId();
            Object staffVal = statements.get(BuildingKey.STAFF);
            if (staffVal instanceof Collection) {
                ((Collection<Long>) staffVal).add(authId);
            } else {
                statements.put(BuildingKey.STAFF, new ArrayList<Long>() {{
                    add((Long) staffVal);
                    add(authId);
                }});
            }
        }
        return statements;
    }

    @Override
    public List<BuildingListDTO> findAll(Map<?, ?> params, Pageable pageable) {
        SqlMap<BuildingKey> statements = mapKey(params);
        statements.addScope(BuildingKey.ALL);
        List<Building> results = buildingRepository.findByCondition(statements, pageable);
        return buildingConverter.toListDto(results);
    }

    @Override
    public int countAll(Map<?, ?> params) {
        SqlMap<BuildingKey> statements = mapKey(params);
        statements.addScope(BuildingKey.COUNT);
        return buildingRepository.countByCondition(statements);
    }

    @Override
    @Transactional
    public void assignBuilding(Long buildingId, Long[] staffIds) {
        Building building = buildingRepository.findById(buildingId).get();
        List<User> assignUsers = Arrays.stream(staffIds)
                .map(User::newInstance)
                .collect(Collectors.toList());
        building.getAssignUsers().retainAll(assignUsers);
        building.getAssignUsers().addAll(assignUsers);
    }

    @Override
    public Map<String, String> getDistricts() {
        return DISTRICTS;
    }

    @Override
    public Map<String, String> getRentTypes() {
        return RENT_TYPES;
    }

    @Override
    public void fillSource(PagedListHolder<BuildingListDTO> pages, final BuildingSearchDTO dto) {
        Map<?, ?> params = MapUtils.from(dto, true);
        pages.setTotalItems(countAll(params));
        while (true) {
            Pageable pageable = PageRequest.of(pages.getPage() - 1, pages.getPageSize());
            List<BuildingListDTO> buildings = findAll(params, pageable);
            if (buildings.isEmpty() && pages.isNotFirstPage()) {
                pages.toLastPage();
                continue;
            }
            pages.setSource(buildings);
            break;
        }
    }

    @Override
    @Transactional
    public BuildingDTO save(BuildingDTO dto) {
        Building building = buildingConverter.toEntity(dto);
        if (building.getId() != null) {
            buildingRepository.findById(building.getId()).ifPresent(oldBuilding -> {
                if (!SecurityUtils.hasRole(MANAGER))
                    building.setName(oldBuilding.getName());
                if (!Objects.equals(building.getAvatarName(), oldBuilding.getAvatarName()))
                    FileUtils.deleteIfExists(BUILDING_AVATAR_PATH + oldBuilding.getAvatarName());
                building.mergeRentAreas(oldBuilding.getRentAreas());
                building.setAssignUsers(oldBuilding.getAssignUsers());
            });
        }
        FileUtils.save(BUILDING_AVATAR_PATH, dto.getAvatarName(), dto.getAvatar());
        return buildingConverter.toDto(buildingRepository.save(building));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        List<Long> buildingIds = Arrays.stream(ids).boxed().collect(Collectors.toList());
        Set<Building> buildings = buildingRepository.findByIdIn(buildingIds);
        for (Building building : buildings) {
            if (StringUtils.isNotBlank(building.getAvatarName()))
                FileUtils.deleteIfExists(BUILDING_AVATAR_PATH + building.getAvatarName());
            building.getRentAreas().clear();
            buildingRepository.delete(building);
        }
    }

}
