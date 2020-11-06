package pl.meklad.ipezput2k20.service;

import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.UserDTO;

import java.util.Optional;

/**
 * Create by dev on 13.10.2020
 */
@Service
public interface UserService {

    UserDTO addUser(UserDTO userDTO);
    UserDTO addTeacher(String email);
    UserDTO addStudent(String email, Long studentId);

    Iterable<UserDTO> findAllUsers();
    Optional<UserDTO> findByUsername (String userName);
    Optional<UserDTO> findByUserId (Long userId);
    UserDTO updateUser(UserDTO userDTO, Long userId);

    boolean deleteByUserId(Long userId);


}
