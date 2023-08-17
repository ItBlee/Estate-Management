package com.itblee.converter;

import com.itblee.constant.SystemConstant;
import com.itblee.dto.BuildingDTO;
import com.itblee.dto.BuildingListDTO;
import com.itblee.repository.entity.Building;
import com.itblee.repository.entity.RentArea;
import com.itblee.repository.enums.District;
import com.itblee.repository.enums.RentType;
import com.itblee.utils.StringUtils;
import com.itblee.utils.ValidateUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter extends AbstractConverter {

    public BuildingDTO toDto(Building entity) {
        ValidateUtils.requireNonNull(entity);
        BuildingDTO dto = convert(entity, BuildingDTO.class);
        String rentAreas = convertRentAreas(entity.getRentAreas());
        String rentTypeCodes = entity.getRentTypeCodes().stream()
                .map(RentType::getCode)
                .collect(Collectors.joining(","));
        if (entity.getAvatarName() != null) {
            dto.setAvatarPath(SystemConstant.BUILDING_AVATAR_PATH + entity.getAvatarName());
            dto.setAvatarName(entity.getAvatarName());
        }
        dto.setRentAreas(rentAreas);
        dto.setRentTypeCodes(rentTypeCodes);
        return dto;
    }

    public List<BuildingDTO> toDto(Collection<Building> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public BuildingListDTO toListDto(Building entity) {
        ValidateUtils.requireNonNull(entity);
        BuildingListDTO dto = convert(entity, BuildingListDTO.class);
        List<String> address = new ArrayList<>();
        if (StringUtils.isNotBlank(entity.getStreet()))
            address.add(entity.getStreet());
        if (StringUtils.isNotBlank(entity.getWard()))
            address.add(entity.getWard());
        if (entity.getDistrictCode() != null)
            address.add(entity.getDistrictCode().getName());
        String rentAreas = convertRentAreas(entity.getRentAreas());
        String addressStr = String.join(",", address);
        dto.setAddress(addressStr);
        dto.setRentAreas(rentAreas);
        return dto;
    }

    private String convertRentAreas(List<RentArea> rentAreas) {
        return rentAreas.stream()
                .map(RentArea::getValue)
                .distinct()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public List<BuildingListDTO> toListDto(Collection<Building> entities) {
        return entities.stream()
                .map(this::toListDto)
                .collect(Collectors.toList());
    }


    public Building toEntity(BuildingDTO dto) {
        ValidateUtils.requireNonNull(dto);
        Building entity = convert(dto, Building.class);
        if (StringUtils.isNotBlank(dto.getDistrictCode()))
            entity.setDistrictCode(District.valueOf(dto.getDistrictCode()));
        if (StringUtils.isNotBlank(dto.getRentTypeCodes())) {
            String[] rentTypeCodes = dto.getRentTypeCodes().split(",");
            entity.setRentTypeCodes(Arrays.stream(rentTypeCodes)
                    .filter(StringUtils::isNotBlank)
                    .map(RentType::valueOf)
                    .collect(Collectors.toList()));
        }
        if (StringUtils.isNotBlank(dto.getRentAreas())) {
            String[] rentAreas = dto.getRentAreas().split(",");
            Arrays.stream(rentAreas)
                    .filter(StringUtils::isNotBlank)
                    .mapToInt(Integer::valueOf)
                    .distinct()
                    .forEach(value -> {
                        RentArea rentArea = new RentArea();
                        rentArea.setValue(value);
                        entity.addRentAreas(rentArea);
                    });
        }
        entity.setAvatarName(dto.getAvatarName());
        return entity;
    }

    public List<Building> toEntity(Collection<BuildingDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
