package com.itblee.repository;

import com.itblee.repository.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByUserNameAndStatus(String name, int status);
    Page<User> findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatus(String userName, String fullName, int status, Pageable pageable);
    Page<User> findByStatus(int status, Pageable pageable);
    int countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatus(String userName, String fullName, int status);
    int countByStatus(int status);
    Optional<User> findOneByUserName(String userName);

    List<User> findByRoles_codeAndStatus(String code, int status);
    Set<User> findByAssignBuildings_idAndStatus(Long buildingId, int status);
    Set<User> findByAssignCustomers_idAndStatus(Long customerId, int status);
}
