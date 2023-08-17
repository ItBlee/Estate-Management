package com.itblee.converter;

import com.itblee.dto.RoleDTO;
import com.itblee.repository.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter extends AbstractConverter {
	
	public RoleDTO toDto(Role entity) {
        return convert(entity, RoleDTO.class);
    }

    public Role toEntity(RoleDTO dto) {
        return convert(dto, Role.class);
    }

}
