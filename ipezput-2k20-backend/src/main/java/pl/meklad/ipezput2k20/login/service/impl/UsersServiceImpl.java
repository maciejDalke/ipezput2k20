package pl.meklad.ipezput2k20.login.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.login.domain.Users;
import pl.meklad.ipezput2k20.login.dto.UsersDTO;
import pl.meklad.ipezput2k20.login.enums.UserRole;
import pl.meklad.ipezput2k20.login.repo.UsersRepo;
import pl.meklad.ipezput2k20.login.service.UsersService;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create by dev on 13.10.2020
 */
@Service
public class UsersServiceImpl implements UsersService {

    final private ModelMapper modelMapper;
    final private UsersRepo usersRepo;

    @Autowired
    public UsersServiceImpl(ModelMapper modelMapper, UsersRepo usersRepo) {
        this.modelMapper = modelMapper;
        this.usersRepo = usersRepo;
    }


    @Override
    public UsersDTO addUser(UsersDTO userDTO) {
        Users user = convertToEntity(userDTO);
        return convertToDto(usersRepo.save(user));
    }

    @Override
    public UsersDTO addTeacher(String email) {
        String temporaryPassword = generateTemporaryPassword(12);
        UsersDTO userDTO = new UsersDTO(
                getNameOrSurname(email, 'n'),
                getNameOrSurname(email, 's'),
                email,
                temporaryPassword,
                null,
                true,
                getNameOrSurname(email, 'u'),
                UserRole.LECTURER);
        Users user = convertToEntity(userDTO);
        return convertToDto(usersRepo.save(user));
    }

    @Override
    public UsersDTO addStudent(String email, Long studentId) {
        String temporaryPassword = generateTemporaryPassword(10);
        UsersDTO userDTO = new UsersDTO(
                getNameOrSurname(email, 'n'),
                getNameOrSurname(email, 's'),
                email,
                temporaryPassword,
                studentId,
                true,
                getNameOrSurname(email, 'u'),
                UserRole.STUDENT);
        Users user = convertToEntity(userDTO);
        return convertToDto(usersRepo.save(user));
    }

    @Override
    public Iterable<UsersDTO> findAllUsers() {
        return usersRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UsersDTO> findByUserName(String username) {
        return Optional.of(convertToDto(usersRepo.findByUserName(username).orElseThrow()));
    } //.orElseThrow(() ->new Exception("User not found - " + username))

    @Override
    public Optional<UsersDTO> findByUserId(Long userId) {
        return Optional.of(convertToDto(usersRepo.findById(userId).orElseThrow()));
    }

    @Override
    public UsersDTO updateUser(UsersDTO userDTO, Long userId) {
        Users user = convertToEntity(userDTO);
        user.setUserId(userId);
        return convertToDto(usersRepo.save(user));
    }

    @Override
    public boolean deleteByUserId(Long userId) {
        if (!usersRepo.existsById(userId))
            return false;
        else usersRepo.deleteById(userId);
        return true;
    }

    //======================================================================================================================
    private String getNameOrSurname(String email, char letter) { // FIXME: 24.10.2020 dodać lepszą logikę na sprawdzenie email
        String[] userName = email.split("@")[0].split("\\.");
        if (letter == 'u')
            return email.split("@")[0];
        else if (letter == 'n')
            return userName[0];
        else
            return userName[userName.length - 1];
    }

    private String generateTemporaryPassword(int count) {
        final String AN_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * AN_STRING.length());
            builder.append(AN_STRING.charAt(character));
        }
        return builder.toString();
    }

    private Users convertToEntity(UsersDTO userDTO) {
        return modelMapper.map(userDTO, Users.class);
    }

    private UsersDTO convertToDto(Users user) {
        return modelMapper.map(user, UsersDTO.class);
    }
}
