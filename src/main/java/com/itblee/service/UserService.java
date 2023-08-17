package com.itblee.service;

import com.itblee.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<StaffDTO> getStaffs();
    List<StaffDTO> findStaffsByAssignBuilding(long buildingId);
    List<StaffDTO> findStaffsByAssignCustomer(long customerId);

    Optional<UserDTO> findOneByUserNameAndStatus(String name, int status);
    List<UserListDTO> getUsers(String name, Pageable pageable);
    int countAll(String searchValue);
    Optional<UserDTO> findOneByUserName(String userName);
    Optional<UserDTO> findUserById(long id);
    UserDTO insert(UserDTO userDTO);
    UserDTO update(Long id, UserDTO userDTO);
    void updatePassword(long id, PasswordDTO userDTO);
    UserDTO resetPassword(long id);
    UserDTO updateProfileOfUser(String id, UserDTO userDTO);
    void delete(long[] ids);
    void fillSource(PagedListHolder<UserListDTO> pages, final UserSearchDTO dto);
}
