package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.dto.UserLoginDTO;
import a0120i1.codegym.cinema_management.dto.login.AuthenticationRequest;
import a0120i1.codegym.cinema_management.dto.login.AuthenticationResponse;
import a0120i1.codegym.cinema_management.dto.login.TokenDTO;
import a0120i1.codegym.cinema_management.model.user.Account;
import a0120i1.codegym.cinema_management.model.user.ERole;
import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.security.service.MyUserDetailsService;
import a0120i1.codegym.cinema_management.security.util.JwtUtil;
import a0120i1.codegym.cinema_management.service.IAccountService;
import a0120i1.codegym.cinema_management.service.IUserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;

@RestController
@CrossOrigin
@RequestMapping("api")
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    private final String passwordSocial = "social_facebook_&_gooogle_u093840932%@*&^#@!*kjewhj,ncoiweq}kqw'''2132132132";

    private final String googleClientId = "1046534921769-0ce6sb6v97gen0mbqpgc3ct9vil3h078.apps.googleusercontent.com";


    @GetMapping("home")
    public String hello() {
        return "Welcome to the Project Cinema-Management of class A0120I1";
    }

    @GetMapping("user")
    public String user() {
        return ("Welcome USER");
    }

    @GetMapping("admin")
    public String admin() {
        return ("Welcome ADMIN");
    }

    @GetMapping("employee")
    public String management() {
        return ("Welcome EMPLOYEE");
    }

    private AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        String jwt = null;
        UserLoginDTO userLoginDto = null;
        String status;
        try {
            // Check username & password exists in database?
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            jwt = jwtUtil.generateToken(userDetails);
            User user = this.userService.getByUsername(authenticationRequest.getUsername());
            userLoginDto = this.modelMapper.map(user, UserLoginDTO.class);
            status = "Success";
        } catch (DisabledException disabledException) {
            // Catch Var enable = false
            status = "Account locked";
        } catch (BadCredentialsException badCredentialsException) {
            // Catch username & password exists in database
            status = "Wrong password or username";
        } catch (Exception exception) {
            status = "Error server";
        }
        return new AuthenticationResponse(jwt, userLoginDto, status);
    }

    private ResponseEntity<AuthenticationResponse> loginSocial(String email, String fullName, String image) {
        User user = new User();
        Account account = new Account();

        account.setUsername(email);
        account.setPassword(this.passwordEncoder.encode(this.passwordSocial));
        account.setRole(ERole.ROLE_USER);
        account.setEnable(true);

        user.setAccount(account);
        user.setEmail(email);
        user.setFullName(fullName);
        user.setImage(image);

        if (this.accountService.isUsernameExists(email)) {
            user = this.userService.getByUsername(email);
        } else {
            user = this.userService.save(user);
        }

        AuthenticationResponse authenticationResponse = login(new AuthenticationRequest(user.getAccount().getUsername(), this.passwordSocial));

        if (authenticationResponse.getStatus().equals("Success")) {
            return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(authenticationResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> loginLocal(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = login(authenticationRequest);
        if (authenticationResponse.getStatus().equals("Success")) {
            return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(authenticationResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/google")
    public ResponseEntity<AuthenticationResponse> LoginGoogle(@RequestBody TokenDTO tokenDto) throws IOException {
        try {
            final NetHttpTransport transport = new NetHttpTransport();
            final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
            GoogleIdTokenVerifier.Builder verifier =
                    new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                            .setAudience(Collections.singletonList(this.googleClientId));
            final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getToken());
            final GoogleIdToken.Payload payload = googleIdToken.getPayload(); // data user

            String email = payload.getEmail();
            String fullName = payload.get("name").toString();
            String image = payload.get("picture").toString();

            return loginSocial(email, fullName, image);
        } catch (ExpiredAuthorizationException expiredAuthorizationException) {
            return new ResponseEntity<>(new AuthenticationResponse(null, null, "TOKEN EXPIRES"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/facebook")
    public ResponseEntity<AuthenticationResponse> loginFacebook(@RequestBody TokenDTO tokenDto) throws IOException {
        try {
            Facebook facebook = new FacebookTemplate(tokenDto.getToken());
            final String[] data = {"email", "name", "picture"};
            org.springframework.social.facebook.api.User userFacebook = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, data);

            String email = userFacebook.getEmail();
            String fullName = userFacebook.getName();
            String image = ((LinkedHashMap) ((LinkedHashMap) userFacebook.getExtraData().get("picture")).get("data")).get("url").toString();

            return loginSocial(email, fullName, image);
        } catch (ExpiredAuthorizationException expiredAuthorizationException) { // token_time_facebook = 1-2 hour
            return new ResponseEntity<>(new AuthenticationResponse(null, null, "TOKEN EXPIRES"), HttpStatus.BAD_REQUEST);
        }
    }

}