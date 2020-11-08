package pl.meklad.ipezput2k20.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meklad.ipezput2k20.dto.GroupDTO;
import pl.meklad.ipezput2k20.dto.RoleInGroupDTO;
import pl.meklad.ipezput2k20.service.GroupService;
import pl.meklad.ipezput2k20.service.RoleInGroupService;
import pl.meklad.ipezput2k20.util.ApiPaths;

import java.util.Iterator;

/**
 * Create by dev on 06.11.2020
 */
@RestController
@RequestMapping(ApiPaths.GroupCtrl.CTRL)
@CrossOrigin
public class GroupController {

    private final GroupService groupService;
    private final RoleInGroupService roleInGroupService;

    @Autowired
    public GroupController(GroupService groupService,
                           RoleInGroupService roleInGroupService) {
        this.groupService = groupService;
        this.roleInGroupService = roleInGroupService;
    }

    @GetMapping(value = "all")
    public ResponseEntity<Iterable<GroupDTO>> showAllGroups() {
        return ResponseEntity.ok(groupService.findAllGroups());
    } //ok

    @GetMapping(path = "{id}")
    public ResponseEntity<GroupDTO> showByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(groupService.findGroupByGroupId(id).orElseThrow());
    } //ok

    @GetMapping
    public ResponseEntity<Iterator> showAllByThisName(@RequestParam String name) {
        return ResponseEntity.ok(groupService.findGroupByName(name));
    }

    @PostMapping(value = "createGroup")
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
        return ResponseEntity.ok(groupService.createGroup(groupDTO));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable("id") Long id,
                                                @RequestBody GroupDTO groupDTO) {
        return ResponseEntity.ok(groupService.updateGroup(groupDTO, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteGroup(@PathVariable("id") Long id) {
        boolean isRemoved = groupService.deleteGroupByGroupId(id);
        if (!isRemoved)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "role/all")
    public ResponseEntity<Iterable<RoleInGroupDTO>> showAllRoleInGroups() {
        return ResponseEntity.ok(roleInGroupService.findAllRoleInGroup());
    }

    @GetMapping(path = "role/{id}")
    public ResponseEntity<RoleInGroupDTO> showRoleInGroupById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roleInGroupService.findRoleByRoleInGroupId(id).orElseThrow());
    }

    @GetMapping(path = "role")
    public ResponseEntity<RoleInGroupDTO> showRoleByUserIdAndGroupId(@RequestBody RoleInGroupDTO roleInGroupDTO) {
        return ResponseEntity.ok(roleInGroupService.findRoleByUserIdAndGroupId(roleInGroupDTO.getUserId(),roleInGroupDTO.getGroupId()).orElseThrow());
    }

    @PostMapping(path = "role/createRole")
    public ResponseEntity<RoleInGroupDTO> createRoleInGroup(@RequestBody RoleInGroupDTO roleInGroupDTO) {
        return ResponseEntity.ok(roleInGroupService.createRole(roleInGroupDTO));
    }

    @PutMapping(path = "role/{id}")
    public ResponseEntity<RoleInGroupDTO> updateRoleInGroup(@PathVariable("id") Long id,
                                                @RequestBody RoleInGroupDTO roleInGroupDTO) {
        return ResponseEntity.ok(roleInGroupService.updateRole(roleInGroupDTO, id));
    }

    @DeleteMapping(path = "role/{id}")
    public ResponseEntity<HttpStatus> deleteRoleInGroup(@PathVariable("id") Long id) {
        boolean isRemoved = roleInGroupService.deleteRoleInGroupByRoleId(id);
        if (!isRemoved)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
