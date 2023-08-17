package com.itblee.converter;

import com.itblee.dto.RoleDTO;
import com.itblee.dto.StaffDTO;
import com.itblee.dto.UserDTO;
import com.itblee.dto.UserListDTO;
import com.itblee.repository.entity.Role;
import com.itblee.repository.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter extends AbstractConverter {

    public StaffDTO toStaffDto(User entity) {
        StaffDTO dto = new StaffDTO();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setChecked("");
        return dto;
    }

    public UserDTO toDto(User entity){
        UserDTO dto = convert(entity, UserDTO.class);
        dto.setRoles(entity.getRoles().stream()
                .map(role -> convert(role, RoleDTO.class))
                .collect(Collectors.toList()));
        return dto;
    }

    public List<UserDTO> toDto(Collection<User> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UserListDTO toListDto(User entity){
        return convert(entity, UserListDTO.class);
    }

    public List<UserListDTO> toListDto(Collection<User> entities) {
        return entities.stream()
                .map(this::toListDto)
                .collect(Collectors.toList());
    }

    public User toEntity(UserDTO dto){
        User user = convert(dto, User.class);
        dto.getRoles().forEach(roleDTO -> user.addRole(convert(roleDTO, Role.class)));
        return user;
    }

    public List<User> toEntity(Collection<UserDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
