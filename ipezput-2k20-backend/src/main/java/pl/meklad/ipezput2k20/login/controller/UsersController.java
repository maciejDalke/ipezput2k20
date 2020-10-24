package pl.meklad.ipezput2k20.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meklad.ipezput2k20.login.dto.UsersDTO;
import pl.meklad.ipezput2k20.login.service.UsersService;

/**
 * Create by dev on 13.10.2020
 */
@RestController
//@EnableWebSecurity
@RequestMapping("/api/user")
public class UsersController {

    final private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(path = "/hello")
    public String hello() {
        System.out.println("wypisz na konsoli???");
        return "Działa";
    }

    @GetMapping
    public ResponseEntity<Iterable<UsersDTO>> showAllUsers() {
        return ResponseEntity.ok(usersService.findAllUsers());
    } //ok

    @GetMapping(path = "{userId}")
    public ResponseEntity<UsersDTO> showByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(usersService.findByUserId(userId).orElseThrow());
    } //ok

    @PostMapping(value = "addStudent")
    public ResponseEntity<UsersDTO> addStudent(@RequestParam String email,
                                               @RequestParam Long studentId) {
        return ResponseEntity.ok(usersService.addStudent(email, studentId));
    }

    @PostMapping(value = "addTeacher")
    public ResponseEntity<UsersDTO> addTeacher(@RequestParam String email) {
        return ResponseEntity.ok(usersService.addTeacher(email));
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable("userId") Long userId,
                                               @RequestBody UsersDTO userDTO) {
        return ResponseEntity.ok(usersService.updateUser(userDTO, userId));
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") Long userId) {
        boolean isRemoved = usersService.deleteByUserId(userId);
        if (!isRemoved)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
