package pl.meklad.ipezput2k20.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.GroupDTO;
import pl.meklad.ipezput2k20.model.domain.Group;
import pl.meklad.ipezput2k20.repo.GroupRepo;
import pl.meklad.ipezput2k20.service.GroupService;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create by dev on 06.11.2020
 */
@Service
@Slf4j
public class GroupServiceImpl implements GroupService {

    private final GroupRepo groupRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public GroupServiceImpl(GroupRepo groupRepo, ModelMapper modelMapper) {
        this.groupRepo = groupRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public GroupDTO createGroup(GroupDTO groupDTO) {
        Group group = convertToEntity(groupDTO);
        return convertToDto(groupRepo.save(group));
    }

    @Override
    public Iterable<GroupDTO> findAllGroups() {
        return groupRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<GroupDTO> findGroupByGroupId(Long groupId) {
        return Optional.of(convertToDto(groupRepo.findById(groupId).orElseThrow()));
    }

    @Override
    public Iterator findGroupByName(String name) {
        Iterable iterable = groupRepo.findAllByName(name);
        return iterable.iterator();
    }

    @Override
    public GroupDTO updateGroup(GroupDTO groupDTO, Long groupId) {
        if (!groupRepo.existsById(groupId))
        return null;
        else {
            Group group = convertToEntity(groupDTO);
            group.setGroupId(groupId);
            return convertToDto(groupRepo.save(group));
        }
    }

    @Override
    public boolean deleteGroupByGroupId(Long groupId) {
        if (!groupRepo.existsById(groupId))
        return false;
        else {
            groupRepo.deleteById(groupId);
            return true;
        }
    }
    //======================================================================================================================
    private Group convertToEntity(GroupDTO groupDTO) {
        return modelMapper.map(groupDTO, Group.class);
    }

    private GroupDTO convertToDto(Group group) {
        return modelMapper.map(group, GroupDTO.class);
    }
}
