package pl.meklad.ipezput2k20.service.impl;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.UserDTO;
import pl.meklad.ipezput2k20.model.domain.User;
import pl.meklad.ipezput2k20.repo.UserRepo;
import pl.meklad.ipezput2k20.service.UserService;
import pl.meklad.ipezput2k20.util.TPage;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.meklad.ipezput2k20.model.enums.UserRole.*;

/**
 * Create by dev on 13.10.2020
 */
@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper,
                           UserRepo userRepo,
                           AuthenticationManager authenticationManager,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Iterable<UserDTO> findAllUsers() {
        return userRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TPage<UserDTO> getAllPageable(Pageable pageable) throws NotFoundException {
        try {
            Page<User> page = userRepo.findAll(PageRequest.of(pageable.getPageNumber(),
                    pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "userId")));
            TPage<UserDTO> tPage = new TPage<UserDTO>();
            UserDTO[] studentDtos = modelMapper.map(page.getContent(), UserDTO[].class);

            tPage.setStat(page, Arrays.asList(studentDtos));
            return tPage;
        } catch (Exception e) {
            throw new NotFoundException("User email dosen't exist : " + e);
        }
    }

    @Override
    public Optional<UserDTO> findByUserId(Long userId) {
        return Optional.of(convertToDto(userRepo.findById(userId).orElseThrow(IllegalArgumentException::new)));
    }

    @Override
    public UserDTO findByUsername(String username) throws NotFoundException {
        try {
            User user = userRepo.findByUsername(username);
            UserDTO userDto = convertToDto(user);
            return userDto;
        } catch (Exception e) {
            throw new NotFoundException("User dosen't exist with this name called : " + username);
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        return convertToDto(userRepo.save(user));
    }

    @Override
    public UserDTO createTeacher(String email) {
        String temporaryPassword = generateTemporaryPassword(12);
        UserDTO userDTO = new UserDTO(getFirstOrLastOrUserName(email, 'f'),
                getFirstOrLastOrUserName(email, 'l'),
                email,
                bCryptPasswordEncoder.encode(temporaryPassword),
                null,
                true,
                getFirstOrLastOrUserName(email, 'u'),
                LECTURER,
                temporaryPassword);
        User user = convertToEntity(userDTO);
        return convertToDto(userRepo.save(user));
    }

    @Override
    public UserDTO createStudent(String email, Long studentId) {
        String temporaryPassword = generateTemporaryPassword(10);
        UserDTO userDTO;
        switch (email.split("@")[1].split("\\.")[0]) {
            case "student":
                userDTO = new UserDTO(
                        getFirstOrLastOrUserName(email, 'n'),
                        getFirstOrLastOrUserName(email, 's'),
                        email,
                        bCryptPasswordEncoder.encode(temporaryPassword),
                        studentId,
                        true,
                        getFirstOrLastOrUserName(email, 'u'),
                        STUDENT,
                        temporaryPassword);
                break;
            case "doctorate":
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
                break;
            default:
                userDTO = new UserDTO(
                        getFirstOrLastOrUserName(email, 'n'),
                        getFirstOrLastOrUserName(email, 's'),
                        email,
                        bCryptPasswordEncoder.encode(temporaryPassword),
                        studentId,
                        true,
                        getFirstOrLastOrUserName(email, 'u'),
                        GUEST,
                        temporaryPassword);
                break;
        }
        return convertToDto(userRepo.save(convertToEntity(userDTO)));
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long userId) throws NotFoundException {
        try {
            User user = convertToEntity(userDTO);
            user.setUserId(userId);
            return convertToDto(userRepo.save(user));
        } catch (Exception e) {
            throw new NotFoundException("User dosen't exist with this id : " + userId);
        }
    }

    @Override
    public boolean deleteUserByUserId(Long userId) {
        if (!userRepo.existsById(userId))
            return false;
        else userRepo.deleteById(userId);
        return true;
    }

//    @Transactional
//    public Boolean register(RegistrationRequest request) throws Exception {
//
//        List<User> userList = userRepo.findByEmail(request.getUsername());
//        if (userList.size() > 0) {
//            throw new Exception("User exist with this : " + request.getEmail());
//        }
//        if (userRepo.getByUsername(request.getUsername()).size() > 0) {
//            throw new Exception("User exist with this name called : " + request.getUsername());
//        }
//        User user = new User();
//        user.setRealPassword(request.getPassword());
//        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
//        // user = modelMapper.map(registirationRequest, User.class);
//        user.setUsername(request.getUsername());
//        user.setEmail(request.getEmail());
//        user.setPassword(request.getPassword());
//        userRepo.save(user);
//        return true;
//    }
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
