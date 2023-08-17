package com.itblee.api.admin;

import com.itblee.dto.BuildingDTO;
import com.itblee.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;

    @PutMapping("/assign/{buildingId}")
    public ResponseEntity<Void> assignStaffs(
            @PathVariable("buildingId") long id,
            @RequestBody Long[] staffIds) {
        buildingService.assignBuilding(id, staffIds);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingDTO buildingDTO) {
        return ResponseEntity.ok(buildingService.save(buildingDTO));
    }

    @PutMapping("/{buildingId}")
    public ResponseEntity<BuildingDTO> updateBuilding(
            @PathVariable("buildingId") long id,
            @RequestBody BuildingDTO buildingDTO) {
        buildingDTO.setId(id);
        return ResponseEntity.ok(buildingService.save(buildingDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBuildings(@RequestBody long[] ids) {
        if (ids.length > 0)
            buildingService.delete(ids);
        return ResponseEntity.noContent().build();
    }

}
