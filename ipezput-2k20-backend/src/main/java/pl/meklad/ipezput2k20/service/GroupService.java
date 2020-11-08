package pl.meklad.ipezput2k20.service;

import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.GroupDTO;

import java.util.Iterator;
import java.util.Optional;

/**
 * Create by dev on 13.10.2020
 */
@Service
public interface GroupService {

    GroupDTO createGroup(GroupDTO groupDTO);

    Iterable<GroupDTO> findAllGroups();
    Optional<GroupDTO> findGroupByGroupId(Long groupId);
    Iterator findGroupByName(String name);

    GroupDTO updateGroup(GroupDTO groupDTO, Long groupId);

    boolean deleteGroupByGroupId(Long groupId);
}
