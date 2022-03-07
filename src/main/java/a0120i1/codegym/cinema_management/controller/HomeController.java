package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.dto.login.UserLoginDTO;
import a0120i1.codegym.cinema_management.dto.login.AuthenticationRequest;
import a0120i1.codegym.cinema_management.dto.login.AuthenticationResponse;
import a0120i1.codegym.cinema_management.dto.login.TokenDTO;
import a0120i1.codegym.cinema_management.model.user.Account;
import a0120i1.codegym.cinema_management.model.user.ERole;
import a0120i1.codegym.cinema_management.model.user.Provider;
import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.security.service.MyUserDetailsService;
import a0120i1.codegym.cinema_management.security.util.JwtUtil;
import a0120i1.codegym.cinema_management.service.IAccountService;
import a0120i1.codegym.cinema_management.service.IUserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.common.io.BaseEncoding;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.RevokedAuthorizationException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

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

    // password user login with Facebook & Google = passwordSocial
    private final String passwordSocial = "social_facebook_&_gooogle_u093840932%@*&^#@!*kjewhj,ncoiweq}kqw'''2132132132";

    // My client ID Google in website: https://console.developers.google.com/
    private final String googleClientId = "1046534921769-0ce6sb6v97gen0mbqpgc3ct9vil3h078.apps.googleusercontent.com";

    // Test
    @GetMapping("home")
    public String hello() {
        return "Welcome to the Project Cinema-Management of class A0120I1";
    }

    // Test
    @GetMapping("user")
    public String user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); // get username logged
        return ("Welcome " + name + " : role user");
    }

    // Test
    @GetMapping("admin")
    public String admin() {
        return ("Welcome ADMIN");
    }

    // Test
    @GetMapping("employee")
    public String management() {
        return ("Welcome EMPLOYEE");
    }

    // parameter : username+password
    // true -> OK -> return: token + User + status
    // false -> username+password ( fail or account locked ) -> return : ERROR
    private ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest) {
        String jwt = null;
        UserLoginDTO userLoginDto = null;
        String status;
        HttpStatus httpStatus;
        try {
            // Check username & password exists in database?
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            jwt = jwtUtil.generateToken(userDetails);
            User user = this.userService.getByUsername(authenticationRequest.getUsername());
            userLoginDto = this.modelMapper.map(user, UserLoginDTO.class);
            status = "Success";
            httpStatus = HttpStatus.OK;
        } catch (DisabledException disabledException) {
            status = "Account locked";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (BadCredentialsException badCredentialsException) {
            status = "Wrong password";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            status = "Username not exists";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception exception) {
            // Cat error xxx ->
            status = "Error server";
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new AuthenticationResponse(jwt, userLoginDto, status), httpStatus);
    }

    private ResponseEntity<AuthenticationResponse> loginSocial(String username, String email, String fullName, String image, Provider provider) {
        User user = new User();
        Account account = new Account();

        account.setUsername(username);
        account.setPassword(this.passwordEncoder.encode(this.passwordSocial));
        account.setRole(ERole.ROLE_USER);
        account.setEnable(true);

        user.setAccount(account);
        user.setEmail(email);
        user.setFullName(fullName);
        user.setImage(image);
        user.setProvider(provider);

        if (this.accountService.existsByUsername(username)) {
            user = this.userService.getByUsername(username);
        } else {
            user = this.userService.save(user);
        }

        return login(new AuthenticationRequest(user.getAccount().getUsername(), this.passwordSocial));
        // call method login(AuthenticationRequest(username, passwordSocial) )
    }


    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> loginLocal(@RequestBody AuthenticationRequest authenticationRequest) {
        return login(authenticationRequest);
    }

    @PostMapping("login/google")
    public ResponseEntity<AuthenticationResponse> LoginGoogle(@RequestBody TokenDTO tokenDto) {
        String status;
        try {
            final NetHttpTransport transport = new NetHttpTransport();
            final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
            GoogleIdTokenVerifier.Builder verifier =
                    new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                            .setAudience(Collections.singletonList(this.googleClientId));
            final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getToken());
            final GoogleIdToken.Payload payload = googleIdToken.getPayload(); // data user

            String email = payload.getEmail();
            String username = "GOOGLE" + email;
            String fullName = payload.get("name").toString();
            String image = payload.get("picture").toString();
            Provider provider = Provider.GOOGLE;

            return loginSocial(username, email, fullName, image, provider);
        } catch (JsonParseException | BaseEncoding.DecodingException | IllegalArgumentException invalid) {
            status = "Token invalid";
        } catch (ExpiredAuthorizationException expiredAuthorizationException) {
            status = "Token expires";
        } catch (Exception e) {
            status = "Error server";
        }
        return new ResponseEntity<>(new AuthenticationResponse(null, null, status), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("login/facebook")
    public ResponseEntity<AuthenticationResponse> loginFacebook(@RequestBody TokenDTO tokenDto) {
        String status;
        try {
            Facebook facebook = new FacebookTemplate(tokenDto.getToken());
            final String[] data = {"email", "name", "picture"};
            // get data for parameters
            org.springframework.social.facebook.api.User userFacebook = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, data);

            String email = userFacebook.getEmail();
            String username = "FACEBOOK" + email;
            String fullName = userFacebook.getName();
            // get url image for Object LinkedHashmap (extra,picture,data)
            String image = ((LinkedHashMap) ((LinkedHashMap) userFacebook.getExtraData().get("picture")).get("data")).get("url").toString();
            Provider provider = Provider.FACEBOOK;

            return loginSocial(username, email, fullName, image, provider);
        } catch (InvalidAuthorizationException invalid) {
            status = "Token invalid";
        } catch (ExpiredAuthorizationException expiredAuthorizationException) {
            status = "Token expires";
        } catch (RevokedAuthorizationException revoked) {
            status = "Token revoked authorization"; // error: logout account Facebook
        } catch (Exception e) {
            status = "Error server";
        }
        return new ResponseEntity<>(new AuthenticationResponse(null, null, status), HttpStatus.BAD_REQUEST);
    }
}