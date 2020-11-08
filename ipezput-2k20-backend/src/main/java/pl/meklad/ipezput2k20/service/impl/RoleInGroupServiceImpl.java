package pl.meklad.ipezput2k20.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.RoleInGroupDTO;
import pl.meklad.ipezput2k20.model.domain.RoleInGroup;
import pl.meklad.ipezput2k20.repo.RoleInGroupRepo;
import pl.meklad.ipezput2k20.service.RoleInGroupService;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create by dev on 06.11.2020
 */
@Service
public class RoleInGroupServiceImpl implements RoleInGroupService {

    private final RoleInGroupRepo roleInGroupRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleInGroupServiceImpl(RoleInGroupRepo roleInGroupRepo, ModelMapper modelMapper) {
        this.roleInGroupRepo = roleInGroupRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleInGroupDTO createRole(RoleInGroupDTO roleInGroupDTO) {
        RoleInGroup role = convertToEntity(roleInGroupDTO);
        return convertToDto(roleInGroupRepo.save(role));
    }

    @Override
    public Iterable<RoleInGroupDTO> findAllRoleInGroup() {
        return roleInGroupRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<RoleInGroupDTO> findRoleByRoleInGroupId(Long roleInGroupId) {
        return Optional.of(convertToDto(roleInGroupRepo.findById(roleInGroupId).orElseThrow()));
    }

    @Override
    public Optional<RoleInGroupDTO> findRoleByUserIdAndGroupId(Long userId, Long groupId) {
        return Optional.of(convertToDto(roleInGroupRepo.findByGroupIdAndUserId(groupId, userId).orElseThrow()));
    }

    @Override
    public RoleInGroupDTO updateRole(RoleInGroupDTO roleInGroupDTO, Long roleInGroupId) {
        if (!roleInGroupRepo.existsById(roleInGroupId))
            return null;
        else {
            RoleInGroup role = convertToEntity(roleInGroupDTO);
            role.setRoleInGroupId(roleInGroupId);
            return convertToDto(roleInGroupRepo.save(role));
        }
    }

    @Override
    public boolean deleteRoleInGroupByRoleId(Long roleInGroupId) {
        if (!roleInGroupRepo.existsById(roleInGroupId))
            return false;
        else {
            roleInGroupRepo.deleteById(roleInGroupId);
            return true;
        }
    }

    //======================================================================================================================
    private RoleInGroup convertToEntity(RoleInGroupDTO roleInGroupDTO) {
        return modelMapper.map(roleInGroupDTO, RoleInGroup.class);
    }

    private RoleInGroupDTO convertToDto(RoleInGroup roleInGroup) {
        return modelMapper.map(roleInGroup, RoleInGroupDTO.class);
    }
}
