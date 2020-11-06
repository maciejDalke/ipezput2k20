package pl.meklad.ipezput2k20.service;

import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.UserDTO;
import pl.meklad.ipezput2k20.util.TPage;

import java.util.Optional;

/**
 * Create by dev on 13.10.2020
 */
@Service
public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO createTeacher(String email);
    UserDTO createStudent(String email, Long studentId);

    Iterable<UserDTO> findAllUsers();
    TPage<UserDTO> getAllPageable(Pageable pageable) throws NotFoundException;
    Optional<UserDTO> findByUserId (Long userId);
    UserDTO findByUsername (String username) throws NotFoundException;

    UserDTO updateUser(UserDTO userDTO, Long userId) throws NotFoundException;

    boolean deleteUserByUserId(Long userId);
}
