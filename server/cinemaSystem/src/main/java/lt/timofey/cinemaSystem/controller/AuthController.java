package lt.timofey.cinemaSystem.controller;

import jakarta.validation.Valid;
import lt.timofey.cinemaSystem.payload.LoginRequest;
import lt.timofey.cinemaSystem.payload.SignupRequest;
import lt.timofey.cinemaSystem.service.UserService;
import lt.timofey.cinemaSystem.valid.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Autowired
    private ResponseErrorValidation responseErrorValidation;


    /*@PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(signupRequest);
        return ResponseEntity.ok("User registered successfully!");
    }*/
    @PostMapping("/api/signup")
    public ResponseEntity<Object> registerUserByApi(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(signupRequest);
        return ResponseEntity.ok("User registered successfully!");
    }
    @GetMapping("/signup")
    public String registrationPage(@ModelAttribute("person") SignupRequest signupRequest) {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String registerUser(@Valid @RequestBody @ModelAttribute("person") SignupRequest signupRequest, BindingResult bindingResult) {
        /*ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;*/
        if (bindingResult.hasErrors()) return "auth/signup";
        userService.createUser(signupRequest);
        return "redirect:/";
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("login - " + loginRequest.getUsername());
    }

    @GetMapping("/signin")
    public String authenticateUser() {
        return "auth/signin";
    }



}
