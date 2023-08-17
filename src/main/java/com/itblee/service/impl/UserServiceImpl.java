package com.itblee.service.impl;

import com.itblee.converter.UserConverter;
import com.itblee.dto.*;
import com.itblee.repository.RoleRepository;
import com.itblee.repository.UserRepository;
import com.itblee.repository.entity.Role;
import com.itblee.repository.entity.User;
import com.itblee.security.utils.SecurityUtils;
import com.itblee.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.itblee.constant.SystemConstant.*;

@Service
public class UserServiceImpl implements UserService {

    private static final int ACTIVE = 1;
    private static final int DISABLE = 0;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserConverter userConverter;

    @Override
    public List<StaffDTO> getStaffs() {
        List<StaffDTO> staffs = userRepository.findByRoles_codeAndStatus(STAFF, ACTIVE)
                .stream()
                .map(userConverter::toStaffDto)
                .collect(Collectors.toList());
        if (!SecurityUtils.hasRole(MANAGER)) {
            Long authId = SecurityUtils.getPrincipal().getId();
            staffs.removeIf(staff -> staff.getId().equals(authId));
        }
        return staffs;
    }

    @Override
    public List<StaffDTO> findStaffsByAssignBuilding(long buildingId) {
        Set<User> staffsOfBuilding = userRepository.findByAssignBuildings_idAndStatus(buildingId, ACTIVE);
        return convertAndCheckIfStaffAssigned(staffsOfBuilding);
    }

    @Override
    public List<StaffDTO> findStaffsByAssignCustomer(long customerId) {
        Set<User> staffsOfCustomer = userRepository.findByAssignCustomers_idAndStatus(customerId, ACTIVE);
        return convertAndCheckIfStaffAssigned(staffsOfCustomer);
    }

    private List<StaffDTO> convertAndCheckIfStaffAssigned(Set<User> users) {
        return userRepository.findByRoles_codeAndStatus(STAFF, ACTIVE).stream()
                .map(user -> {
                    StaffDTO staff = userConverter.toStaffDto(user);
                    if (users.contains(user)) //or use existBy spring data
                        staff.check();
                    return staff;
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findOneByUserNameAndStatus(String username, int status) {
        User user = userRepository.findOneByUserNameAndStatus(username, status).get();
        return Optional.ofNullable(userConverter.toDto(user));
    }

    @Override
    public List<UserListDTO> getUsers(String name, Pageable pageable) {
        Page<User> users;
        if (StringUtils.isNotBlank(name))
            users = userRepository.findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatus(name, name, ACTIVE, pageable);
        else users = userRepository.findByStatus(ACTIVE, pageable);
        return userConverter.toListDto(users.getContent());
    }

    @Override
    public int countAll(String searchValue) {
        if (StringUtils.isNotBlank(searchValue))
            return userRepository.countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatus(searchValue, searchValue, ACTIVE);
        return userRepository.countByStatus(ACTIVE);
    }

    @Override
    public Optional<UserDTO> findOneByUserName(String userName) {
        User user = userRepository.findOneByUserName(userName).get();
        return Optional.of(userConverter.toDto(user));
    }

    @Override
    public Optional<UserDTO> findUserById(long id) {
        Optional<User> found = userRepository.findById(id);
        if (!found.isPresent())
            return Optional.empty();
        User user = found.get();
        UserDTO dto = userConverter.toDto(user);
        user.getRoles().forEach(item -> dto.setRoleCode(item.getCode()));
        return Optional.of(dto);
    }

    @Override
    @Transactional
    public UserDTO insert(UserDTO newUser) {
        Role role = roleRepository.findOneByCode(newUser.getRoleCode()).get();
        String encodePwd = passwordEncoder.encode(passwordEncoder.encode(PASSWORD_DEFAULT));
        User userEntity = userConverter.toEntity(newUser);
        userEntity.getRoles().add(role);
        userEntity.setStatus(ACTIVE);
        userEntity.setPassword(encodePwd);
        return userConverter.toDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO updateUser) {
        Role role = roleRepository.findOneByCode(updateUser.getRoleCode()).get();
        User oldUser = userRepository.findById(id).get();
        User userEntity = userConverter.toEntity(updateUser);
        userEntity.setUserName(oldUser.getUserName());
        userEntity.setStatus(oldUser.getStatus());
        userEntity.getRoles().add(role);
        userEntity.setPassword(oldUser.getPassword());
        return userConverter.toDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public void updatePassword(long id, PasswordDTO passwordDTO) {
        User user = userRepository.findById(id).get();
        if (passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())
                && passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
            String encodePwd = passwordEncoder.encode(passwordDTO.getNewPassword());
            user.setPassword(encodePwd);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    @Transactional
    public UserDTO resetPassword(long id) {
        User userEntity = userRepository.findById(id).get();
        userEntity.setPassword(passwordEncoder.encode(PASSWORD_DEFAULT));
        return userConverter.toDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO updateProfileOfUser(String username, UserDTO updateUser) {
        User oldUser = userRepository.findOneByUserName(username).get();
        oldUser.setFullName(updateUser.getFullName());
        return userConverter.toDto(userRepository.save(oldUser));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (Long item : ids) {
            User userEntity = userRepository.findById(item).get();
            userEntity.setStatus(DISABLE);
            userRepository.save(userEntity);
        }
    }

    @Override
    public void fillSource(PagedListHolder<UserListDTO> pages, UserSearchDTO dto) {
        String searchValue = dto.getName();
        pages.setTotalItems(countAll(searchValue));
        while (true) {
            Pageable pageable = PageRequest.of(pages.getPage() - 1, pages.getPageSize());
            List<UserListDTO> users = getUsers(searchValue, pageable);
            if (users.isEmpty() && pages.isNotFirstPage()) {
                pages.toLastPage();
                continue;
            }
            pages.setSource(users);
            break;
        }
    }
}
