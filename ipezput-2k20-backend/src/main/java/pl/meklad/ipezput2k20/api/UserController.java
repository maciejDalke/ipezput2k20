package pl.meklad.ipezput2k20.api;

import javassist.NotFoundException;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meklad.ipezput2k20.dto.UserDTO;
import pl.meklad.ipezput2k20.service.UserService;
import pl.meklad.ipezput2k20.util.ApiPaths;

/**
 * Create by dev on 13.10.2020
 */
@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@CrossOrigin
@Log4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/hello")
    public String hello() {
        System.out.println("wypisz na konsoli???");
        return "Działa";
    }

    @GetMapping
    public ResponseEntity<Iterable<UserDTO>> showAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    } //ok

    @GetMapping(path = "{userId}")
    public ResponseEntity<UserDTO> showByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.findByUserId(userId).orElseThrow());
    } //ok

    @PostMapping(value = "createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PostMapping(value = "createStudent")
    public ResponseEntity<UserDTO> createStudent(@RequestParam String email,
                                                 @RequestParam Long studentId) {
        return ResponseEntity.ok(userService.createStudent(email, studentId));
    }

    @PostMapping(value = "createTeacher")
    public ResponseEntity<UserDTO> createTeacher(@RequestParam String email) {
        return ResponseEntity.ok(userService.createTeacher(email));
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") Long userId,
                                              @RequestBody UserDTO userDTO) throws NotFoundException {
        return ResponseEntity.ok(userService.updateUser(userDTO, userId));
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") Long userId) {
        boolean isRemoved = userService.deleteUserByUserId(userId);
        if (!isRemoved)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
