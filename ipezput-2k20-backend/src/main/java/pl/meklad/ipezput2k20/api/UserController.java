package pl.meklad.ipezput2k20.api;

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
//@EnableWebSecurity
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@CrossOrigin
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/hello")
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

    @PostMapping(value = "addStudent")
    public ResponseEntity<UserDTO> addStudent(@RequestParam String email,
                                              @RequestParam Long studentId) {
        return ResponseEntity.ok(userService.addStudent(email, studentId));
    }

    @PostMapping(value = "addTeacher")
    public ResponseEntity<UserDTO> addTeacher(@RequestParam String email) {
        return ResponseEntity.ok(userService.addTeacher(email));
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") Long userId,
                                              @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userDTO, userId));
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") Long userId) {
        boolean isRemoved = userService.deleteByUserId(userId);
        if (!isRemoved)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
