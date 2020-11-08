package pl.meklad.ipezput2k20.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.meklad.ipezput2k20.dto.LoginRequest;
import pl.meklad.ipezput2k20.dto.TokenResponse;
import pl.meklad.ipezput2k20.model.domain.User;
import pl.meklad.ipezput2k20.repo.UserRepo;
import pl.meklad.ipezput2k20.security.JwtTokenUtil;
import pl.meklad.ipezput2k20.service.UserService;
import pl.meklad.ipezput2k20.util.ApiPaths;

/**
 * Create by dev on 06.11.2020
 */

@RestController
@RequestMapping(ApiPaths.MainCtrl.CTRL)
@CrossOrigin
public class MainController {

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public MainController(AuthenticationManager authenticationManager,
                          UserRepo userRepo,
                          UserService userService,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          JwtTokenUtil jwtTokenUtil,
                          ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) throws AuthenticationException {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final User user = userRepo.findByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new TokenResponse(user.getUsername(), token));
    }

//    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
//    public ResponseEntity<Boolean> signUp(@RequestBody RegistrationRequest registirationRequest) throws Exception {
//
//        Boolean result = userService.register(registirationRequest);
//        return ResponseEntity.ok(result);
//    }

}
