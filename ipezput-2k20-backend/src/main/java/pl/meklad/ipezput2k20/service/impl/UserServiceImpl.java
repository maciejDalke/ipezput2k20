package pl.meklad.ipezput2k20.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.domain.User;
import pl.meklad.ipezput2k20.dto.UserDTO;
import pl.meklad.ipezput2k20.repo.UserRepo;
import pl.meklad.ipezput2k20.service.UserService;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.meklad.ipezput2k20.domain.UserRole.*;

/**
 * Create by dev on 13.10.2020
 */
@Service
public class UserServiceImpl implements UserService {

    final private ModelMapper modelMapper;
    final private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepo userRepo) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
    }


    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        return convertToDto(userRepo.save(user));
    }

    @Override
    public UserDTO addTeacher(String email) {
        String temporaryPassword = generateTemporaryPassword(12);
        UserDTO userDTO = new UserDTO(getFirstOrLastOrUserName(email, 'f'),
                getFirstOrLastOrUserName(email, 'l'),
                email,
                temporaryPassword,
                null,
                true,
                getFirstOrLastOrUserName(email, 'u'),
                LECTURER,
                temporaryPassword);
        User user = convertToEntity(userDTO);
        return convertToDto(userRepo.save(user));
    }

    @Override
    public UserDTO addStudent(String email, Long studentId) {
        String temporaryPassword = generateTemporaryPassword(10);
        UserDTO userDTO;
        if (Objects.equals(email.split("@")[1].split("\\.")[0], "student")) {
            userDTO = new UserDTO(
                    getFirstOrLastOrUserName(email, 'n'),
                    getFirstOrLastOrUserName(email, 's'),
                    email,
                    temporaryPassword,
                    studentId,
                    true,
                    getFirstOrLastOrUserName(email, 'u'),
                    STUDENT,
                    temporaryPassword);
        } else if (Objects.equals(email.split("@")[1].split("\\.")[0], "doctorate")) {
            userDTO = new UserDTO(
                    getFirstOrLastOrUserName(email, 'n'),
                    getFirstOrLastOrUserName(email, 's'),
                    email,
                    temporaryPassword,
                    studentId,
                    true,
                    getFirstOrLastOrUserName(email, 'u'),
                    PHD_STUDENT,
                    temporaryPassword);
        } else {
            userDTO = new UserDTO(
                    getFirstOrLastOrUserName(email, 'n'),
                    getFirstOrLastOrUserName(email, 's'),
                    email,
                    temporaryPassword,
                    studentId,
                    true,
                    getFirstOrLastOrUserName(email, 'u'),
                    GUEST,
                    temporaryPassword);
        }
        return convertToDto(userRepo.save(convertToEntity(userDTO)));
    }

    @Override
    public Iterable<UserDTO> findAllUsers() {
        return userRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return Optional.of(convertToDto(userRepo.findByUsername(username).orElseThrow()));
    }

    @Override
    public Optional<UserDTO> findByUserId(Long userId) {
        return Optional.of(convertToDto(userRepo.findById(userId).orElseThrow()));
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long userId) {
        User user = convertToEntity(userDTO);
        user.setUserId(userId);
        return convertToDto(userRepo.save(user));
    }

    @Override
    public boolean deleteByUserId(Long userId) {
        if (!userRepo.existsById(userId))
            return false;
        else userRepo.deleteById(userId);
        return true;
    }

    //======================================================================================================================
    private String getFirstOrLastOrUserName(String email, char letter) { // FIXME: 24.10.2020 dodać lepszą logikę na sprawdzenie email
        String[] userName = email.split("@")[0].split("\\.");
        if (letter == 'u')
            return email.split("@")[0];
        else if (letter == 'f')
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

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
