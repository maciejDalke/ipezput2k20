package pl.meklad.ipezput2k20.login.service;

import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.login.dto.UsersDTO;

import java.util.Optional;

/**
 * Create by dev on 13.10.2020
 */
@Service
public interface UsersService {

    UsersDTO addUser(UsersDTO userDTO);
    UsersDTO addTeacher(String email);
    UsersDTO addStudent(String email, Long studentId);

    Iterable<UsersDTO> findAllUsers();
    Optional<UsersDTO> findByUserName (String userName);
    Optional<UsersDTO> findByUserId (Long userId);
    UsersDTO updateUser(UsersDTO userDTO, Long userId);

    boolean deleteByUserId(Long userId);


}
