package a0120i1.codegym.cinema_management.controller;



import a0120i1.codegym.cinema_management.dto.login.AuthenticationRequest;
import a0120i1.codegym.cinema_management.dto.login.AuthenticationResponse;
import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.security.service.MyUserDetailsService;
import a0120i1.codegym.cinema_management.security.util.JwtUtil;
import a0120i1.codegym.cinema_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
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

    @GetMapping("management")
    public String management() {
        return ("Welcome MANAGEMENT");
    }

    @GetMapping("sale")
    public String sale() {
        return ("Welcome SALE");
    }


    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        String jwt = null;
        User user = null;
        String status;
        HttpStatus httpStatus;
        try {
            // Check username & password exists in database?
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            jwt = jwtUtil.generateToken(userDetails);
            user = this.userService.getByUsername(authenticationRequest.getUsername());
            status = "Success";
            httpStatus = HttpStatus.OK;
        } catch (BadCredentialsException badCredentialsException) {
            // Catch username & password exists in database
            status = "Wrong password or username";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (DisabledException disabledException) {
            // Catch Var enable = false
            status = "Account locked";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception exception) {
            status = "Error server";
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new AuthenticationResponse(jwt, user, status), httpStatus);
    }




}