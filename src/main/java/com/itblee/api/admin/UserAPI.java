package com.itblee.api.admin;

import com.itblee.dto.PasswordDTO;
import com.itblee.dto.UserDTO;
import com.itblee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUsers(@RequestBody UserDTO newUser) {
        return ResponseEntity.ok(userService.insert(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUsers(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.update(id, userDTO));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<Void> changePasswordUser(
            @PathVariable("id") long id,
            @RequestBody PasswordDTO passwordDTO) {
        userService.updatePassword(id, passwordDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/password/{id}/reset")
    public ResponseEntity<UserDTO> resetPassword(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.resetPassword(id));
    }

    @PutMapping("/profile/{username}")
    public ResponseEntity<UserDTO> updateProfileOfUser(
            @PathVariable("username") String username,
            @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateProfileOfUser(username, userDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUsers(@RequestBody long[] ids) {
        if (ids.length > 0)
            userService.delete(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/staffs")
    public ResponseEntity<List<?>> getStaffs(
            @RequestParam(value = "buildingId", required = false) Long buildingId,
            @RequestParam(value = "customerId", required = false) Long customerId) {
        if (buildingId != null && customerId == null)
            return ResponseEntity.ok(userService.findStaffsByAssignBuilding(buildingId));
        if (customerId != null && buildingId == null)
            return ResponseEntity.ok(userService.findStaffsByAssignCustomer(customerId));
        return ResponseEntity.ok(userService.getStaffs());
    }
}
