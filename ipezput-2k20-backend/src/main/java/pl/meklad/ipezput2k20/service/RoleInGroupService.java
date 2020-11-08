package pl.meklad.ipezput2k20.service;


import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.RoleInGroupDTO;

import java.util.Optional;

/**
 * Create by dev on 06.11.2020
 */
@Service
public interface RoleInGroupService {
    RoleInGroupDTO createRole(RoleInGroupDTO roleInGroupDTO);

    Iterable<RoleInGroupDTO> findAllRoleInGroup();

    Optional<RoleInGroupDTO> findRoleByRoleInGroupId(Long roleInGroupId);

    Optional<RoleInGroupDTO> findRoleByUserIdAndGroupId(Long userId, Long groupId);

    RoleInGroupDTO updateRole(RoleInGroupDTO roleInGroupDTO, Long roleInGroupId);

    boolean deleteRoleInGroupByRoleId(Long roleInGroupId);
}
